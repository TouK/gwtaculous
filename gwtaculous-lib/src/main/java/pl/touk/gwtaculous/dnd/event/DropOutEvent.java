package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;
import pl.touk.gwtaculous.dnd.DropObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drop out event. This event is fired after dragged element is dropped but not on registered drop box (no successful drop).
 * This event contains fully initialized {@link DragObject} and {@link DropObject}.
 * Do not use reference for those objects for later use, their data is valid only for event processing.
 */
public class DropOutEvent extends GwtEvent<DropOutHandler> {
	
	public static final GwtEvent.Type<DropOutHandler> TYPE = new GwtEvent.Type<DropOutHandler>();
	
	private DragObject dragObject;
	
	public DropOutEvent(DragObject dragObject){
		this.dragObject = dragObject;
	}
	
	public static HandlerRegistration register(EventBus e, DropOutHandler handler) {
    	return e.addHandler(TYPE, handler);
    }
  
	public static HandlerRegistration register(EventBus e, DropOutHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
	}
	
	@Override
	protected void dispatch(DropOutHandler handler) {
		handler.onDropOut(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropOutHandler> getAssociatedType() {
		return TYPE;
	}
	
	public DragObject getDragObject() {
		return dragObject;
	}

}
