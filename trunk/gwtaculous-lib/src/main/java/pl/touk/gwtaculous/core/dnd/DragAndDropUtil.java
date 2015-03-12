package pl.touk.gwtaculous.core.dnd;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

import pl.touk.gwtaculous.core.dnd.utils.DOMUtil;

/**
 * Set of various helper functions used in drag & drop process
 *
 */
public class DragAndDropUtil {
	
	public static boolean isDropSuccessful(DragObject dragObject, DropObject dropObject){
		Widget dropzoneWidget = dropObject.getSourceWidget();
		if (	dropzoneWidget.isAttached() &&
				dropzoneWidget.isVisible() &&
				DOMUtil.isMouseCursorWithinElement(dropzoneWidget.getElement(), dragObject.getMouseClientPositionX(), dragObject.getMouseClientPositionY())) {
			return true;
		}
		return false;
	}
	
	public static boolean hasDragWentOver(DropObject dropObject, int mouseClientPositionX, int mouseClientPositionY){
		if (!dropObject.isDragOver()) {	
			Widget dropzoneWidget = dropObject.getSourceWidget();
			if (	dropzoneWidget.isAttached() &&
					dropzoneWidget.isVisible() &&
					DOMUtil.isMouseCursorWithinElement(dropzoneWidget.getElement(), mouseClientPositionX, mouseClientPositionY)) {
				dropObject.setDragOver(true);
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasDragWentOut(DropObject dropObject, int mouseClientPositionX, int mouseClientPositionY){
		if (dropObject.isDragOver()) {
			Widget dropzoneWidget = dropObject.getSourceWidget();
			if (	dropzoneWidget.isAttached() &&
					dropzoneWidget.isVisible() &&
					!DOMUtil.isMouseCursorWithinElement(dropzoneWidget.getElement(), mouseClientPositionX, mouseClientPositionY)) {
				dropObject.setDragOver(false);
				return true;
			}
		}
		return false;
	}
	
	public static Element adaptAndPosition (Element element, int posX, int posY, Position position){
		element.getStyle().setPosition(position);
		element.getStyle().setMargin(0, Unit.PX);
		element.getStyle().setZIndex(10000);
		element.addClassName("dragged");
		DOMUtil.setElementPosition(element, posX, posY);
		return element;
	}
	
	public static Element cloneAndPosition (Element element, int posX, int posY, Position position) {
		//TODO set size of dragElement to size of element to be cloned
		Element dragElement = Document.get().createDivElement();
		dragElement.getStyle().setZIndex(10000);
		dragElement.getStyle().setPosition(position);
		dragElement.getStyle().setOpacity(0.75);
		dragElement.addClassName("dragged clone");
		dragElement.appendChild(element.cloneNode(true));
		dragElement.getFirstChildElement().getStyle().setMargin(0, Unit.PX);
		dragElement.getFirstChildElement().getStyle().setPosition(Position.STATIC);
		DOMUtil.setElementPosition(dragElement, posX, posY);
		RootPanel.get().getElement().appendChild(dragElement);
		return dragElement;
	}
	
	public static void moveElementToElement(Element elementMoved, Element elementTarget, Position position){
		int posX = elementTarget.getAbsoluteLeft();
		int posY = elementTarget.getAbsoluteTop();
		elementMoved.getStyle().setPosition(position);
		elementMoved.getStyle().setMargin(0, Unit.PX);
		elementMoved.getStyle().setZIndex(10000);
		DOMUtil.setElementPosition(elementMoved, posX, posY);
	}
	
	public static void terminateClone(DragObject dragElement){
		if (dragElement.getDragOptions().contains(DragOption.MOVE_TO_CLONE)) {
			moveElementToElement(dragElement.getSourceElement(), dragElement.getDragElement(), Position.FIXED);
		}
		dragElement.getDragElement().removeFromParent();
	}
	
}
