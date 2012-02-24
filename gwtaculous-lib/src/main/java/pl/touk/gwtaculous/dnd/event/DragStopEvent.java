package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drag stop event. This event is fired just after dragged element is dropped but before checking "successful drop" conditions (drop over valid drop box).
 * This event contains fully initialized {@link DragObject}. Do not use reference for that object for later use, it's data is valid only for event processing.
 */
public class DragStopEvent extends GwtEvent<DragStopHandler> {
	
	public static final GwtEvent.Type<DragStopHandler> TYPE = new GwtEvent.Type<DragStopHandler>();
	
	private DragObject dragObject;
	
	public DragStopEvent(DragObject dragObject){
		this.dragObject = dragObject;
	}
	
	@Override
	protected void dispatch(DragStopHandler handler) {
		handler.onDragStop(this);	
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragStopHandler> getAssociatedType() {
		return TYPE;
	}
	
    public static HandlerRegistration register(EventBus e, DragStopHandler handler) {
        return e.addHandler(TYPE, handler);
    }
  
    public static HandlerRegistration register(EventBus e, DragStopHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
  	}	
	
	public DragObject getDragObject() {
		return dragObject;
	}

}
