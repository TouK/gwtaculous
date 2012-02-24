package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;
import pl.touk.gwtaculous.dnd.DropObject;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drag out event. This event is fired when element is dragged out of registered drop box.
 * This event contains fully initialized {@link DragObject} and {@link DropObject}.
 * Do not use reference for those objects for later use, their data is valid only for event processing.
 */
public class DragOutEvent extends GwtEvent<DragOutHandler> {
	
	public static final GwtEvent.Type<DragOutHandler> TYPE = new GwtEvent.Type<DragOutHandler>();
	
	private DragObject dragObject;
	private DropObject dropObject;
	
	public DragOutEvent(DragObject dragObject, DropObject dropObject){
		this.dragObject = dragObject;
		this.dropObject = dropObject;
	}	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragOutHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DragOutHandler handler) {
		handler.onDragOut(this);
	}
	
	public static HandlerRegistration register(EventBus e, DragOutHandler handler) {
    	return e.addHandler(TYPE, handler);
    }
  
	public static HandlerRegistration register(EventBus e, DragOutHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
	}
	
	public DragObject getDragObject() {
		return dragObject;
	}
	
	public DropObject getDropObject(){
		return dropObject;
	}	

}
