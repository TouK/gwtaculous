package pl.touk.gwtaculous.dnd;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;

/**
 * Base NativePreviewHandler used to intercept all drag like mouse events fired after drag process has been initialized.
 *
 */
public class DragAndDropNativePreviewHandler implements NativePreviewHandler {
	
	private DragObject dragObject;
	private DragAndDropController dragAndDropController;
	
	public DragAndDropNativePreviewHandler(DragObject dragElement){
		this.dragObject = dragElement;
		this.dragAndDropController = DragAndDropController.getInstance();
	}
	
	public void onPreviewNativeEvent(NativePreviewEvent event) {
		
		NativeEvent ne = event.getNativeEvent();
		
		int clientX = ne.getClientX();
		int clientY = ne.getClientY();
		
		if (event.getTypeInt() == Event.ONMOUSEMOVE){
			ne.preventDefault();
			dragObject.setMouseClientPosition(ne.getClientX(), ne.getClientY());
			dragAndDropController.dragMove(dragObject, ne);
			event.cancel();
		} else if (event.getTypeInt() == Event.ONMOUSEUP) {
			ne.preventDefault();
			dragObject.setMouseClientPosition(clientX, clientY);
			dragAndDropController.dragStop(dragObject, ne);
			event.cancel();
		} else if (event.getTypeInt() == Event.ONMOUSEDOWN) {
			ne.preventDefault();
			dragObject.setMouseClientPosition(clientX, clientY);
			event.cancel();
		} else if (event.getTypeInt() == Event.ONKEYDOWN) {
			if (ne.getKeyCode() == KeyCodes.KEY_ESCAPE) {
				dragObject.setMouseClientPosition(-1, -1);
				dragAndDropController.dragStop(dragObject, ne);
			}
		}
	}
	
}
