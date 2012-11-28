package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;
import pl.touk.gwtaculous.dnd.DropObject;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;


public abstract class DropEvent <H extends EventHandler> extends GwtEvent<H> {
	
	private DragObject dragObject;
	private DropObject dropObject;
	private NativeEvent nativeEvent;

	public DropEvent(DragObject dragObject, DropObject dropObject, NativeEvent nativeEvent){
		this.dragObject = dragObject;
		this.dropObject = dropObject;
		this.nativeEvent = nativeEvent;
	}

	public DragObject getDragObject() {
		return dragObject;
	}
	
	public void setDropObject(DropObject dropObject) {
		this.dropObject = dropObject;
	}

	public DropObject getDropObject() {
		return dropObject;
	}

	public NativeEvent getNativeEvent() {
		return nativeEvent;
	}



}
