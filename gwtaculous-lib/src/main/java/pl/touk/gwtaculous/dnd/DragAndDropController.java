package pl.touk.gwtaculous.dnd;

import java.util.ArrayList;

import pl.touk.gwtaculous.dnd.event.DragInitEvent;
import pl.touk.gwtaculous.dnd.event.DragMoveEvent;
import pl.touk.gwtaculous.dnd.event.DragMoveHandler;
import pl.touk.gwtaculous.dnd.event.DragOutEvent;
import pl.touk.gwtaculous.dnd.event.DragOverEvent;
import pl.touk.gwtaculous.dnd.event.DragStartEvent;
import pl.touk.gwtaculous.dnd.event.DragStopEvent;
import pl.touk.gwtaculous.dnd.event.DragStopHandler;
import pl.touk.gwtaculous.dnd.event.DropInEvent;
import pl.touk.gwtaculous.dnd.event.DropOutEvent;
import pl.touk.gwtaculous.dnd.utils.DOMUtil;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Panel;

/**
 * Main drag & drop controller. This class is singleton and can be initialized only once. 
 * First initialization determinate use of local EventBus (default) or user EventBus which will be used to send and receive drag/drop specific events.<br>
 * <br>
 * This object is responsible for firing all drag and drop related events. Base event order: <br>
 * 1. Drag process begins <br>
 * - {@link DragInitEvent} <br>
 * - {@link DragStartEvent} <br>
 * 2. Drag process in progress <br>
 * - {@link DragMoveEvent} <br>
 * - {@link DragOverEvent} <br>
 * - {@link DragOutEvent} <br>
 * 3. Drag process ends <br>
 * - {@link DragStopEvent} <br>
 * - {@link DropInEvent} or {@link DropOutEvent} <br>
 */
public class DragAndDropController {
	
	private static DragAndDropController instance = null; 
	
	private EventBus eventBus;
	
	private boolean isDropIn = false;
	private boolean isMouseDown = false;
	private boolean isDragInProgress = false;
	private boolean isMouseMoveAxisX = true;
	private boolean isMouseMoveAxisY = true;
	private boolean isMouseMoveRestricted = false;
	
	private int maxMousePositionX = Integer.MAX_VALUE;
	private int maxMousePositionY = Integer.MAX_VALUE;
	private int minMousePositionX = 0;
	private int minMousePositionY = 0;
	private int mouseRelativePositionX = 0;
	private int mouseRelativePositionY = 0;
	
	private boolean dragInitEventEnabled = true;
	private boolean dragStartEventEnabled = true;
	private boolean dragMoveEventEnabled = true;
	private boolean dragStopEventEnabled = true;
	private boolean dropOutEventEnabled = true;
	
	private ArrayList<DragOption> dragOptionsCache = new ArrayList<DragOption>();
	
	private HandlerRegistration nativePreviewHR;
	
	public static DragAndDropController getInstance() {
		if(instance == null) {
			instance = new DragAndDropController(null);
		}
	    return instance;
	}
	
	public static DragAndDropController initialize(EventBus eventBus){
		if(instance == null) {
			instance = new DragAndDropController(eventBus);
		} else {
			GWT.log("DragAndDropController already initialized with default EventBus ! Skipping custom initialization.");
		}
	    return instance;
	}
	
	private DragAndDropController(EventBus eventBus){
		if (eventBus != null) {
			this.eventBus = eventBus;
			GWT.log("DragAndDropController initialized with custom EventBus");
		} else {
			this.eventBus = new SimpleEventBus();
			GWT.log("DragAndDropController initialized with default EventBus");			
		}
	}
	
	public EventBus getEventBus() {
		return eventBus;
	}
	
	public MouseDownHandler draggableMouseDownHandler(final DragObject dragObject){
		return new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				Element element = event.getNativeEvent().getEventTarget().cast();
				// On some browsers FORM elements operate on "default" onMouseDown mechanism and preventDefault() disable them,
				// we don't wont that to happen
				//TODO event.preventDefault() we need more control on that behavior especially on children nodes
				if (!DOMUtil.isFormElement(element)) {
					event.preventDefault();
				}
				isMouseDown = true;
			}
		};
	}
	
	public MouseMoveHandler draggableMouseMoveHandler(final DragObject dragObject){
		return new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				event.getNativeEvent().preventDefault();
				if (isMouseDown && !isDragInProgress) {
					dragInit(dragObject, event.getNativeEvent());
					dragStart(dragObject, event.getNativeEvent());
				}
			}
		};
	}
	
	public MouseUpHandler draggableMouseUpHandler(final DragObject dragObject){
		return new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				event.getNativeEvent().preventDefault();
				isMouseDown = false;
			}
		};
	}
	
	public DragStopHandler droppableDragStopHandler(final DropObject dropObject){
		return new DragStopHandler() {
			private ArrayList<DropOption> dropOptions = dropObject.getDropOptions();
			private boolean dropInEnabled = dropOptions.contains(DropOption.FIRE_DROP_IN_EVENT) || ! dropOptions.contains(DropOption.SILENT);
			public void onDragStop(DragStopEvent event) {
				DragObject dragObject = event.getDragObject();
				if (DragAndDropUtil.isDropSuccessful(dragObject, dropObject)) {
					isDropIn = true;
					if ((dropObject.getContainerWidget() instanceof Panel) && (dropOptions.contains(DropOption.ADOPT_WIDGET))){
						dragObject.getSourceWidget().removeFromParent();
						((Panel)dropObject.getContainerWidget()).add(dragObject.getSourceWidget());
					}
					if (dropInEnabled) {
						GWT.log("DropInEvent");
						DragAndDrop.getEventBus().fireEventFromSource(new DropInEvent(dragObject, dropObject, event.getNativeEvent()), dropObject.getSourceWidget());
					}
				}
			}
		};
	}
	
	public DragMoveHandler droppableDragMoveHandler(final DropObject dropObject){
		return new DragMoveHandler() {
			private ArrayList<DropOption> dropOptions = dropObject.getDropOptions();
			private boolean dragOverAndOutEventsEnabled = dropOptions.contains(DropOption.FIRE_DRAG_OVER_OUT_EVENT) || ! dropOptions.contains(DropOption.SILENT);
			
			public void onDragMove(DragMoveEvent event) {
				if (dragOverAndOutEventsEnabled) {
					DragObject dragObject = event.getDragObject();
					int mouseClientPositionX = dragObject.getMouseClientPositionX();
					int mouseClientPositionY = dragObject.getMouseClientPositionY();
					
					if (DragAndDropUtil.hasDragWentOver(dropObject, mouseClientPositionX, mouseClientPositionY)) {
						GWT.log("DragOverEvent");
						DragAndDrop.getEventBus().fireEventFromSource(new DragOverEvent(dragObject, dropObject, event.getNativeEvent()), dropObject.getSourceWidget());
					} else if (DragAndDropUtil.hasDragWentOut(dropObject, mouseClientPositionX, mouseClientPositionY)) {
						GWT.log("DragOutEvent");
						DragAndDrop.getEventBus().fireEventFromSource(new DragOutEvent(dragObject, dropObject, event.getNativeEvent()), dropObject.getSourceWidget());
					}
				}
			}
		};
	}
	
	protected void dragInit(DragObject dragObject, NativeEvent ne) {
		isDragInProgress = true;
		isDropIn = false;
		initializeDragOptionsCache(dragObject);
		calculateEventRestrictions(dragObject);
		
		if (dragInitEventEnabled){
			GWT.log("DragInitEvent");
			eventBus.fireEventFromSource(new DragInitEvent(dragObject, ne), dragObject.getSourceWidget());
		}
		
		dragObject.init(ne.getClientX(), ne.getClientY());		
	}
	
	protected void dragStart(DragObject dragObject, NativeEvent ne){
		
		calculateMoveRestriction(dragObject);
		initializeDomChanges(dragObject);
		
		//TODO move resetDragWidgetPositionIfNeeded to dragObject initialization ?
		resetDragWidgetPositionIfNeeded(dragObject, ne.getClientX(), ne.getClientY());
		setMouseRelativePositionCache(dragObject);
		
		if (dragStartEventEnabled) {
			GWT.log("DragStartEvent");
			eventBus.fireEventFromSource(new DragStartEvent(dragObject, ne), dragObject.getSourceWidget());
		}
	}

    protected void dragMove(DragObject dragObject, NativeEvent ne){
		
		int posX = ne.getClientX() - mouseRelativePositionX;
		int posY = ne.getClientY() - mouseRelativePositionY;
		
		if (!isMouseMoveRestricted) {
			DOMUtil.setElementPosition(dragObject.getDragElement(), posX, posY);
		} else {
			if (isMouseMoveAxisY){
				if (posY < minMousePositionY) {
					posY = minMousePositionY;
				} else if (posY > maxMousePositionY) {
					posY = maxMousePositionY;
				}
				DOMUtil.setElementPositionY(dragObject.getDragElement(), posY);
			}
			if (isMouseMoveAxisX){
				if (posX < minMousePositionX) {
					posX = minMousePositionX;
				} else if (posX > maxMousePositionX) {
					posX = maxMousePositionX;
				}
				DOMUtil.setElementPositionX(dragObject.getDragElement(), posX);
			}
		}
		
		if (dragMoveEventEnabled){
			GWT.log("DragMoveEvent");
			eventBus.fireEventFromSource(new DragMoveEvent(dragObject, ne), dragObject.getSourceWidget());
		}
	}
	
	protected void dragStop(DragObject dragObject, NativeEvent ne){
		
		if(isDragInProgress){
			
			if (dragStopEventEnabled) {
				GWT.log("DragStopEvent");
				eventBus.fireEventFromSource(new DragStopEvent(dragObject, ne), dragObject.getSourceWidget());
			}
			if (!isDropIn && dropOutEventEnabled) {
				GWT.log("DropOutEvent");
				eventBus.fireEventFromSource(new DropOutEvent(dragObject, ne), dragObject.getSourceWidget());
			}
			resetAllDragRelatedDomChanges(dragObject);
			resetAllDragRelatedFlags();
			resetAllDragRelatedParameters();
			resetDragOptionsCache();
		}
	}

	private void initializeDomChanges(DragObject dragObject){
		nativePreviewHR = Event.addNativePreviewHandler(new DragAndDropNativePreviewHandler(dragObject));
		if (dragOptionsCache.contains(DragOption.AUTO_MOVE_CURSOR)) {
			dragObject.getDragElement().getStyle().setCursor(Cursor.MOVE);
		}
	}
	
	private void calculateMoveRestriction(DragObject dragObject){
		
		isMouseMoveAxisX = dragOptionsCache.contains(DragOption.MOVE_AXIS_X) && (!dragOptionsCache.contains(DragOption.BLOCK_WIDGET));
		isMouseMoveAxisY = dragOptionsCache.contains(DragOption.MOVE_AXIS_Y) && (!dragOptionsCache.contains(DragOption.BLOCK_WIDGET));;
		
		isMouseMoveRestricted = (isMouseMoveAxisX != isMouseMoveAxisY) || (dragOptionsCache.contains(DragOption.BLOCK_WIDGET));
		
		if (dragObject.getContainerElement()!= null) {
			Element container = dragObject.getContainerElement();
			Element dragged = dragObject.getDragElement();
			
			maxMousePositionX = container.getAbsoluteRight() - dragged.getClientWidth();
			maxMousePositionY = container.getAbsoluteBottom() - dragged.getClientHeight();
			minMousePositionX = container.getAbsoluteLeft();
			minMousePositionY = container.getAbsoluteTop();
			
			isMouseMoveRestricted = true;
		}
	}
	
	private void calculateEventRestrictions(DragObject dragObject){
		
		if (dragOptionsCache.contains(DragOption.SILENT)) {
			setAllDragEventsRestricted();
			dragInitEventEnabled = dragOptionsCache.contains(DragOption.FIRE_DRAG_INIT_EVENT);
			dragStartEventEnabled = dragOptionsCache.contains(DragOption.FIRE_DRAG_START_EVENT);
			dragMoveEventEnabled = dragOptionsCache.contains(DragOption.FIRE_DRAG_MOVE_EVENT);
			dragStopEventEnabled = dragOptionsCache.contains(DragOption.FIRE_DRAG_STOP_EVENT);
			dropOutEventEnabled = dragOptionsCache.contains(DragOption.FIRE_DROP_OUT_EVENT); 
		}

	}
	
	private void initializeDragOptionsCache(DragObject dragObject) {
		dragOptionsCache = dragObject.getDragOptions();	
	}
	
	private void resetDragOptionsCache(){
		dragOptionsCache.clear();
	}

	private void setMouseRelativePositionCache(DragObject dragObject){
		mouseRelativePositionX = dragObject.getMouseRelativePositionX();
		mouseRelativePositionY = dragObject.getMouseRelativePositionY();
	}
	
    private void resetDragWidgetPositionIfNeeded(DragObject dragObject, int mouseClientPositionX, int mouseClientPositionY) {
        if (dragOptionsCache.contains(DragOption.CENTER_WIDGET_ON_CURSOR)) {
            DOMUtil.centerElementOnPosition(dragObject.getDragElement(), mouseClientPositionX, mouseClientPositionY);
            dragObject.reset(mouseClientPositionX, mouseClientPositionY);
            GWT.log("DragWidget reset position: " + dragObject.getDragElement().getAbsoluteTop() + " " + dragObject.getDragElement().getAbsoluteLeft());
        }
    }
	
	private void setAllDragEventsRestricted(){
		dragInitEventEnabled = false;
		dragStartEventEnabled = false;
		dragMoveEventEnabled = false;
		dragStopEventEnabled = false;
		dropOutEventEnabled = false;
	}
	
	private void resetAllDragRelatedFlags(){
		isDragInProgress = false;
		isMouseDown = false;
		isMouseMoveAxisX = true;
		isMouseMoveAxisY = true;
		isMouseMoveRestricted = false;
		
		dragInitEventEnabled = true;
		dragStartEventEnabled = true;
		dragMoveEventEnabled = true;
		dragStopEventEnabled = true;
		dropOutEventEnabled = true;
	}
	
	private void resetAllDragRelatedParameters() {
		maxMousePositionX = Integer.MAX_VALUE;
		maxMousePositionY = Integer.MAX_VALUE;
		minMousePositionX = 0;
		minMousePositionY = 0;
		mouseRelativePositionX = 0;
		mouseRelativePositionY = 0;
	}
	
	private void resetAllDragRelatedDomChanges(DragObject dragObject){
		
		nativePreviewHR.removeHandler();
		DOMUtil.cancelAllDocumentSelections();
		
		if (dragOptionsCache.contains(DragOption.CLONE_WIDGET) && (!dragOptionsCache.contains(DragOption.BLOCK_WIDGET))) {
			DragAndDropUtil.terminateClone(dragObject);
		}
		if (dragOptionsCache.contains(DragOption.AUTO_MOVE_CURSOR)) {
			dragObject.getDragElement().getStyle().clearCursor();
		}
	}
		
	
}
