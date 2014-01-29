package pl.touk.gwtaculous.dnd;

import java.util.ArrayList;

import pl.touk.gwtaculous.dnd.event.DragHandler;
import pl.touk.gwtaculous.dnd.event.DragMoveEvent;
import pl.touk.gwtaculous.dnd.event.DragStopEvent;
import pl.touk.gwtaculous.dnd.event.DropHandler;
import pl.touk.gwtaculous.dnd.utils.EventBusUtil;
import pl.touk.gwtaculous.dnd.utils.MultiHandlerRegistration;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

public class DragAndDropHandlerRegistration {
	
	private static DragAndDropHandlerRegistration instance;

	public static DragAndDropHandlerRegistration getInstance() {
		if(instance == null) {
			instance = new DragAndDropHandlerRegistration();
		}
	    return instance;
	}
	
	public HandlerRegistration makeMeDraggable(DragObject dragObject) {
        return makeMeDraggable(dragObject, null);
    }
	
	public HandlerRegistration makeMeDraggable(DragObject dragObject, DragHandler dragHandler) {
		
		Widget sourceWidget =  dragObject.getSourceWidget();
		ArrayList<DragOption> dragOptions = dragObject.getDragOptions();
		
		HandlerRegistration mouseDownEventHR = addMouseDownHandlerToDraggable(dragObject);
		HandlerRegistration mouseUpEventHR = addMouseUpHandlerToDraggable(dragObject);
		HandlerRegistration mouseMoveEventHR = addMouseMoveHandlerToDraggable(dragObject);

        MultiHandlerRegistration sourceWidgetDragEventsHR = null;
        MultiHandlerRegistration customHandlerDragEventsHR = null;
		
		if (dragOptions.contains(DragOption.AUTO_REGISTER)) {
            if (sourceWidget instanceof DragHandler) {
                sourceWidgetDragEventsHR = EventBusUtil.registerDragHandlers(DragAndDrop.getEventBus(), (DragHandler) sourceWidget, sourceWidget);
            }
		}

        if (dragHandler != null) {
            customHandlerDragEventsHR = EventBusUtil.registerDragHandlers(DragAndDrop.getEventBus(), dragHandler, sourceWidget);
        }
		
		return new MultiHandlerRegistration(
				mouseMoveEventHR, mouseUpEventHR, mouseDownEventHR, sourceWidgetDragEventsHR, customHandlerDragEventsHR);
	}

    public HandlerRegistration makeMeDroppable(DropObject dropObject) {
        return makeMeDroppable(dropObject, null);
    }
	
	public HandlerRegistration makeMeDroppable(DropObject dropObject, DropHandler dropHandler) {
		
		Widget sourceWidget =  dropObject.getSourceWidget();
		ArrayList<DropOption> dropOptions = dropObject.getDropOptions();
		
		HandlerRegistration dragStopHR = addDragStopHandlerToDroppable(dropObject);
		HandlerRegistration dragMoveHR = addDragMoveHandlerToDroppable(dropObject);
        MultiHandlerRegistration sourceWidgetDropEventsHR = null;
        MultiHandlerRegistration customHandlerDropEventsHR = null;
		
		if (dropOptions.contains(DropOption.AUTO_REGISTER)) {
            if (sourceWidget instanceof DropHandler) {
                sourceWidgetDropEventsHR = EventBusUtil.registerDropHandlers(DragAndDrop.getEventBus(), (DropHandler) sourceWidget, sourceWidget);
            }
		}

        if (dropHandler != null) {
            customHandlerDropEventsHR = EventBusUtil.registerDropHandlers(DragAndDrop.getEventBus(), dropHandler, sourceWidget);
        }

		return new MultiHandlerRegistration(dragStopHR, dragMoveHR, sourceWidgetDropEventsHR, customHandlerDropEventsHR);
	}
	
	private HandlerRegistration addMouseDownHandlerToDraggable(final DragObject dragObject){
		DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseDownHandler(DragAndDropController.getInstance().draggableMouseDownHandler(dragObject));
	}
	
	private HandlerRegistration addMouseMoveHandlerToDraggable(final DragObject dragObject){
		DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseMoveHandler(DragAndDropController.getInstance().draggableMouseMoveHandler(dragObject));
	}
	
	private HandlerRegistration addMouseUpHandlerToDraggable(final DragObject dragObject){
		DraggableWidget draggable = dragObject.getLeverWidget();
		return draggable.addMouseUpHandler(DragAndDropController.getInstance().draggableMouseUpHandler(dragObject));
	}
	
	private HandlerRegistration addDragStopHandlerToDroppable(final DropObject dropObject){		
		return DragStopEvent.register(DragAndDrop.getEventBus(), DragAndDropController.getInstance().droppableDragStopHandler(dropObject));
	}
	
	private HandlerRegistration addDragMoveHandlerToDroppable(final DropObject dropObject){
		return DragMoveEvent.register(DragAndDrop.getEventBus(), DragAndDropController.getInstance().droppableDragMoveHandler(dropObject));
	}
	
}
