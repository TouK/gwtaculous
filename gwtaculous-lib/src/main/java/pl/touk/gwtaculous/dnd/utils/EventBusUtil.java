package pl.touk.gwtaculous.dnd.utils;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;
import pl.touk.gwtaculous.dnd.event.*;

public class EventBusUtil {

    public static MultiHandlerRegistration registerDragHandlers(EventBus eventBus, DragHandler handler, Widget source){

        HandlerRegistration dragInitHR = null;
        HandlerRegistration dragStartHR = null;
        HandlerRegistration dragMoveHR = null;
        HandlerRegistration dragStopHR = null;
        HandlerRegistration dragDropOutHR = null;

        if (handler instanceof DragInitHandler) {
            dragInitHR = DragInitEvent.register(eventBus, (DragInitHandler) handler, source);
        }
        if (handler instanceof DragStartHandler) {
            dragStartHR = DragStartEvent.register(eventBus, (DragStartHandler) handler, source);
        }
        if (handler instanceof DragMoveHandler) {
            dragMoveHR = DragMoveEvent.register(eventBus, (DragMoveHandler) handler, source);
        }
        if (handler instanceof DragStopHandler) {
            dragStopHR = DragStopEvent.register(eventBus, (DragStopHandler) handler, source);
        }
        if (handler instanceof DropOutHandler) {
            dragDropOutHR = DropOutEvent.register(eventBus, (DropOutHandler) handler, source);
        }

        return new MultiHandlerRegistration(
                dragInitHR, dragStartHR, dragMoveHR, dragStopHR, dragDropOutHR);
    }


    public static MultiHandlerRegistration registerDropHandlers(EventBus eventBus, DropHandler handler, Widget source){

        HandlerRegistration dropInHR = null;
        HandlerRegistration dragOverHR = null;
        HandlerRegistration dragOutHR = null;

        if (handler instanceof DropInHandler) {
            dropInHR = DropInEvent.register(eventBus, (DropInHandler) handler, source);
        }
        if (handler instanceof DragOverHandler) {
            dragOverHR = DragOverEvent.register(eventBus, (DragOverHandler) handler, source);
        }
        if (handler instanceof DragOutHandler) {
            dragOutHR = DragOutEvent.register(eventBus, (DragOutHandler) handler, source);
        }

        return new MultiHandlerRegistration(dropInHR, dragOverHR, dragOutHR);
    }
}
