package pl.touk.gwtaculous.dnd.event;

import pl.touk.gwtaculous.dnd.DragObject;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public abstract class DragEvent <H extends EventHandler> extends GwtEvent<H> {
	
	private DragObject dragObject;
	private NativeEvent nativeEvent;

	public DragEvent(DragObject dragObject, NativeEvent nativeEvent){
		this.dragObject = dragObject;
		this.nativeEvent = nativeEvent;
	}

	public DragObject getDragObject() {
		return dragObject;
	}

	public NativeEvent getNativeEvent() {
		return nativeEvent;
	}
	
	
}
