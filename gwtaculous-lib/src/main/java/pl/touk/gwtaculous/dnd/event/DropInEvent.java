package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;
import pl.touk.gwtaculous.dnd.DropObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drop in event. This event is fired after dragged element is dropped over valid drop box.
 * This event contains fully initialized {@link DragObject} and {@link DropObject}.
 * Do not use reference for those objects for later use, their data is valid only for event processing.
 */
public class DropInEvent extends GwtEvent<DropInHandler> {
	
	public static final GwtEvent.Type<DropInHandler> TYPE = new GwtEvent.Type<DropInHandler>();
	
	private DragObject dragObject;
	private DropObject dropObject;
	
	public DropInEvent(DragObject dragObject, DropObject dropObject){
		this.dragObject = dragObject;
		this.dropObject = dropObject;
	}
	
	@Override
	protected void dispatch(DropInHandler handler) {
		handler.onDropIn(this);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DropInHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static HandlerRegistration register(EventBus e, DropInHandler handler) {
    	return e.addHandler(TYPE, handler);
    }
  
	public static HandlerRegistration register(EventBus e, DropInHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
	}
	
	public DragObject getDragObject() {
		return dragObject;
	}
	
	public DropObject getDropObject(){
		return dropObject;
	}

}
