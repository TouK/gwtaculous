package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;
import pl.touk.gwtaculous.dnd.DropObject;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Represents drag over event. This event is fired when element is dragged over registered drop box.
 * This event contains fully initialized {@link DragObject} and {@link DropObject}.
 * Do not use reference for those objects for later use, their data is valid only for event processing.
 */
public class DragOverEvent extends DropEvent<DragOverHandler> {
	
	public static final GwtEvent.Type<DragOverHandler> TYPE = new GwtEvent.Type<DragOverHandler>();
	
	public DragOverEvent(DragObject dragObject, DropObject dropObject, NativeEvent nativeEvent){
		super(dragObject, dropObject, nativeEvent);
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<DragOverHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(DragOverHandler handler) {
		handler.onDragOver(this);
	}
	
	public static HandlerRegistration register(EventBus e, DragOverHandler handler) {
    	return e.addHandler(TYPE, handler);
    }
  
	public static HandlerRegistration register(EventBus e, DragOverHandler handler, Object source) {
    	return e.addHandlerToSource(TYPE, source, handler);
	}
	
}
