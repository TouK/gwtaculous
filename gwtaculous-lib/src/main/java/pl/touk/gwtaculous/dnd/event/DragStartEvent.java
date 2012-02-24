package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drag start event. This event is fired when actual drag begins just after all DOM changes has been made.
 * This event contains fully initialized {@link DragObject}. Do not use reference for that object for later use, it's data is valid only for event processing.
 */
public class DragStartEvent extends GwtEvent<DragStartHandler> {
	
	public static final GwtEvent.Type<DragStartHandler> TYPE = new GwtEvent.Type<DragStartHandler>();
	
	private DragObject dragObject;
	
	public DragStartEvent(DragObject dragObject){
		this.dragObject = dragObject;
	}
	
	@Override
	protected void dispatch(DragStartHandler handler) {
		handler.onDragStart(this);		
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragStartHandler> getAssociatedType() {
		return TYPE;
	}
	
    public static HandlerRegistration register(EventBus e, DragStartHandler handler) {
        return e.addHandler(TYPE, handler);
    }
  
    public static HandlerRegistration register(EventBus e, DragStartHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
  	}

	public DragObject getDragObject() {
		return dragObject;
	}

}
