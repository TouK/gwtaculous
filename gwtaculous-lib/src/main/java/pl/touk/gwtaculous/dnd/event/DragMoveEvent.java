package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drag move event. This event is fired every time mouse position changes during drag process.
 * This event contains fully initialized {@link DragObject}. Do not use reference for that object for later use, it's data is valid only for event processing.
 */
public class DragMoveEvent extends GwtEvent<DragMoveHandler> {
	
	public static final GwtEvent.Type<DragMoveHandler> TYPE = new GwtEvent.Type<DragMoveHandler>();
	
	private DragObject dragObject;
	
	public DragMoveEvent(DragObject dragObject){
		this.dragObject = dragObject;
	}
	
	@Override
	protected void dispatch(DragMoveHandler handler) {
		handler.onDragMove(this);	
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragMoveHandler> getAssociatedType() {
		return TYPE;
	}
	
    public static HandlerRegistration register(EventBus e, DragMoveHandler handler) {
          return e.addHandler(TYPE, handler);
    }
    
    public static HandlerRegistration register(EventBus e, DragMoveHandler handler, Object source) {
        return e.addHandlerToSource(TYPE, source, handler);
    }

	public DragObject getDragObject() {
		return dragObject;
	}

}
