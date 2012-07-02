package pl.touk.gwtaculous.dnd;

import java.util.ArrayList;

import pl.touk.gwtaculous.dnd.event.DragInitEvent;
import pl.touk.gwtaculous.dnd.event.DragMoveEvent;
import pl.touk.gwtaculous.dnd.event.DragMoveHandler;
import pl.touk.gwtaculous.dnd.event.DragOutEvent;
import pl.touk.gwtaculous.dnd.event.DragOutHandler;
import pl.touk.gwtaculous.dnd.event.DragOverEvent;
import pl.touk.gwtaculous.dnd.event.DragOverHandler;
import pl.touk.gwtaculous.dnd.event.DragStartEvent;
import pl.touk.gwtaculous.dnd.event.DragStopEvent;
import pl.touk.gwtaculous.dnd.event.DragStopHandler;
import pl.touk.gwtaculous.dnd.event.DropInEvent;
import pl.touk.gwtaculous.dnd.event.DropInHandler;
import pl.touk.gwtaculous.dnd.event.DropOutEvent;
import pl.touk.gwtaculous.dnd.utils.DOMUtil;
import pl.touk.gwtaculous.dnd.utils.MultiHandlerRegistration;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
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
import com.google.gwt.user.client.ui.Widget;

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
	
	private boolean dragInitEventEnabled = true;
	private boolean dragStartEventEnabled = true;
	private boolean dragMoveEventEnabled = true;
	private boolean dragStopEventEnabled = true;
	private boolean dropOutEventEnabled = true;
	
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
			GWT.log("DragAndDropController allready initialized with default EventBus ! Skipping custom initialization.");
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
	
	public HandlerRegistration makeMeDraggable(DragObject dragObject) {
		
		HandlerRegistration mouseDownEventHR = addMouseDownHandlerToDraggable(dragObject);
		HandlerRegistration mouseUpEventHR = addMouseUpHandlerToDraggable(dragObject);
		HandlerRegistration mouseMoveEventHR = addMouseMoveHandlerToDraggable(dragObject);
		
		return new MultiHandlerRegistration(mouseMoveEventHR, mouseUpEventHR, mouseDownEventHR);
	}
	
	public HandlerRegistration makeMeDroppable(final DropObject dropObject) {
		
		Widget sourceWidget =  dropObject.getSourceWidget();
		
		HandlerRegistration dragStopHR = addDragStopHandlerToDroppable(dropObject);
		HandlerRegistration dragMoveHR = addDragMoveHandlerToDroppable(dropObject);
		HandlerRegistration dropInHR = null;
		HandlerRegistration dragOverHR = null;
		HandlerRegistration dragOutHR = null;
		
		if (sourceWidget instanceof DropInHandler) {
			dropInHR = DropInEvent.register(eventBus, (DropInHandler) sourceWidget, sourceWidget);
		}
		
		if (sourceWidget instanceof DragOverHandler) {
			dragOverHR = DragOverEvent.register(eventBus, (DragOverHandler) sourceWidget, sourceWidget);
		}
		
		if (sourceWidget instanceof DragOutHandler) {
			dragOutHR = DragOutEvent.register(eventBus, (DragOutHandler) sourceWidget, sourceWidget);
		}
		

		return new MultiHandlerRegistration(dragStopHR, dragMoveHR, dropInHR, dragOverHR, dragOutHR);
	}
	
	private HandlerRegistration addMouseDownHandlerToDraggable(final DragObject dragObject){
		final DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				Element element = event.getNativeEvent().getEventTarget().cast();
				// On some browsers SELECT element operate on "default" onMouseDown mechanism and preventDefault() disables it,
				// we don't wont that to happen
				if (!element.getNodeName().equalsIgnoreCase("select")) {
					event.preventDefault();
				}
				isMouseDown = true;
			}
		});
	}
	
	private HandlerRegistration addMouseMoveHandlerToDraggable(final DragObject dragObject){
		final DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseMoveHandler(new MouseMoveHandler() {
			public void onMouseMove(MouseMoveEvent event) {
				event.getNativeEvent().preventDefault();
				if (isMouseDown && !isDragInProgress) {
					dragInit(dragObject, event.getClientX(), event.getClientY());
					dragStart(dragObject);
				}
			}
		});
	}
	
	private HandlerRegistration addMouseUpHandlerToDraggable(final DragObject dragObject){
		final DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				event.getNativeEvent().preventDefault();
				isMouseDown = false;
			}
		});
	}
	
	private HandlerRegistration addDragStopHandlerToDroppable(final DropObject dropObject){
		
		final ArrayList<DropOption> dropOptions = dropObject.getDropOptions();
		final boolean dropInEnabled = dropOptions.contains(DropOption.FIRE_DROP_IN_EVENT) || ! dropOptions.contains(DropOption.SILENT);
		
		return DragStopEvent.register(eventBus, new DragStopHandler() {
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
						eventBus.fireEventFromSource(new DropInEvent(dragObject, dropObject), dropObject.getSourceWidget());
					}
				}
			}
		});
	}
	
	private HandlerRegistration addDragMoveHandlerToDroppable(final DropObject dropObject){
		
		final ArrayList<DropOption> dropOptions = dropObject.getDropOptions();
		final boolean dragOverAndOutEventsEnabled = dropOptions.contains(DropOption.FIRE_DRAG_OVER_OUT_EVENT) || ! dropOptions.contains(DropOption.SILENT);
		
		return DragMoveEvent.register(eventBus, new DragMoveHandler() {
			
			public void onDragMove(DragMoveEvent event) {
				GWT.log("DragMoveEvent - DragOverEvent & DragOutEvent processing");
				if (dragOverAndOutEventsEnabled) {
					DragObject dragObject = event.getDragObject();
					int mouseClientPositionX = dragObject.getMouseClientPositionX();
					int mouseClientPositionY = dragObject.getMouseClientPositionY();
					
					if (DragAndDropUtil.hasDragWentOver(dropObject, mouseClientPositionX, mouseClientPositionY)) {
						GWT.log("DragOverEvent");
						eventBus.fireEventFromSource(new DragOverEvent(dragObject, dropObject), dropObject.getSourceWidget());
					} else if (DragAndDropUtil.hasDragWentOut(dropObject, mouseClientPositionX, mouseClientPositionY)) {
						GWT.log("DragOutEvent");
						eventBus.fireEventFromSource(new DragOutEvent(dragObject, dropObject), dropObject.getSourceWidget());
					}
				}
			}
		});
	}
	
	protected void dragInit(DragObject dragObject, int mouseClientPositionX, int mouseClientPositionY) {
		isDragInProgress = true;
		isDropIn = false;
		
		calculateEventRestrictions(dragObject);
		
		if (dragInitEventEnabled){
			GWT.log("DragInitEvent");
			eventBus.fireEventFromSource(new DragInitEvent(dragObject), dragObject.getSourceWidget());
		}
		dragObject.init(mouseClientPositionX, mouseClientPositionY);
	}
	
	protected void dragStart(DragObject dragObject){
		
		calculateMoveRestriction(dragObject);
		initializeDomChanges(dragObject);
		
		if (dragStartEventEnabled) {
			GWT.log("DragStartEvent");
			eventBus.fireEventFromSource(new DragStartEvent(dragObject), dragObject.getSourceWidget());
		}
	}
	
	protected void dragMove(DragObject dragObject, int posX, int posY){
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
			eventBus.fireEventFromSource(new DragMoveEvent(dragObject), dragObject.getSourceWidget());
		}
	}
	
	protected void dragStop(DragObject dragObject){
		
		if(isDragInProgress){
			
			if (dragStopEventEnabled) {
				GWT.log("DragStopEvent");
				eventBus.fireEventFromSource(new DragStopEvent(dragObject), dragObject.getSourceWidget());
			}
			if (!isDropIn && dropOutEventEnabled) {
				GWT.log("DropOutEvent");
				eventBus.fireEventFromSource(new DropOutEvent(dragObject), dragObject.getSourceWidget());
			}
			resetAllDragRelatedDomChanges(dragObject);
			resetAllDragRelatedFlags();
			resetAllDragRelatedParameters();
		}
	}

	private void initializeDomChanges(DragObject dragObject){
		nativePreviewHR = Event.addNativePreviewHandler(new DragAndDropNativePreviewHandler(dragObject));
		if (dragObject.getDragOptions().contains(DragOption.AUTO_MOVE_CURSOR)) {
			dragObject.getDragElement().getStyle().setCursor(Cursor.MOVE);
		}
	}
	
	private void calculateMoveRestriction(DragObject dragObject){
		
		isMouseMoveAxisX = dragObject.getDragOptions().contains(DragOption.MOVE_AXIS_X);
		isMouseMoveAxisY = dragObject.getDragOptions().contains(DragOption.MOVE_AXIS_Y);
		
		isMouseMoveRestricted = (isMouseMoveAxisX != isMouseMoveAxisY);
		
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
		
		ArrayList<DragOption> dragOptions = dragObject.getDragOptions();
		
		if (dragOptions.contains(DragOption.SILENT)) {
			setAllDragEventsRestricted();
		}
		dragInitEventEnabled = dragOptions.contains(DragOption.FIRE_DRAG_INIT_EVENT);
		dragStartEventEnabled = dragOptions.contains(DragOption.FIRE_DRAG_START_EVENT);
		dragMoveEventEnabled = dragOptions.contains(DragOption.FIRE_DRAG_MOVE_EVENT);
		dragStopEventEnabled = dragOptions.contains(DragOption.FIRE_DRAG_STOP_EVENT);
		dropOutEventEnabled = dragOptions.contains(DragOption.FIRE_DROP_OUT_EVENT); 
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
	}
	
	private void resetAllDragRelatedDomChanges(DragObject dragObject){
		nativePreviewHR.removeHandler();
		DOMUtil.cancelAllDocumentSelections();
		
		if (dragObject.getDragOptions().contains(DragOption.CLONE_WIDGET)) {
			DragAndDropUtil.terminateClone(dragObject);
		}
		if (dragObject.getDragOptions().contains(DragOption.AUTO_MOVE_CURSOR)) {
			dragObject.getDragElement().getStyle().clearCursor();
		}
	}
		
	
}
