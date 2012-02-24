package pl.touk.gwtaculous.dnd;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

/**
 * Base NativePreviewHandler used to intercept all drag like mouse events fired after drag process has been initialized.
 *
 */
public class DragAndDropNativePreviewHandler implements NativePreviewHandler {
	
	private int mouseRelativePositionX;
	private int mouseRelativePositionY;
	private DragObject dragObject;
	private DragAndDropController dragAndDropController;
	
	public DragAndDropNativePreviewHandler(DragObject dragElement){
		this.dragObject = dragElement;
		this.mouseRelativePositionX = dragElement.getMouseRelativePositionX();
		this.mouseRelativePositionY = dragElement.getMouseRelativePositionY();
		this.dragAndDropController = DragAndDropController.getInstance();
	}
	
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		
		NativeEvent ne = event.getNativeEvent();
		int clientX = ne.getClientX();
		int clientY = ne.getClientY();
		
		if (event.getTypeInt() == Event.ONMOUSEMOVE){
			ne.preventDefault();
			dragObject.setMouseClientPosition(ne.getClientX(), ne.getClientY());
			dragAndDropController.dragMove(dragObject, clientX-mouseRelativePositionX, clientY-mouseRelativePositionY);
			event.cancel();
		} else if (event.getTypeInt() == Event.ONMOUSEUP) {
			ne.preventDefault();
			dragObject.setMouseClientPosition(clientX, clientY);
			dragAndDropController.dragStop(dragObject);
			event.cancel();
		} else if (event.getTypeInt() == Event.ONMOUSEDOWN) {
			ne.preventDefault();
			event.cancel();
		}
	}
	
}
