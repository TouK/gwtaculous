package pl.touk.gwtaculous.dnd;

import java.util.ArrayList;

import pl.touk.gwtaculous.dnd.utils.DOMUtil;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * Data aggregate object for drag related objects and properties.
 * Do not use reference for that object for later use, it's data is valid only for event processing.
 *
 */
public class DragObject {
	
	private int mouseClientPositionX;
	private int mouseClientPositionY;
	private int mouseRelativePositionX;
	private int mouseRelativePositionY;
	private int widgetStartPositionX;
	private int widgetStartPositionY;
	
	private Widget sourceWidget;
	private Element sourceElement;
	
	private DraggableWidget leverWidget;
	private Element leverElement;
	
	private Widget containerWidget;
	private Element containerElement;
	
	private Element draggedElement;
	
	private ArrayList<DragOption> dragOptions;
	
	public DragObject(Widget sourceWidget, DraggableWidget leverWidget, ArrayList<DragOption> dragOptions){
		setDragOptions(dragOptions);
		setSourceWidget(sourceWidget);
		setLeverWidget(leverWidget);
	}
	
	public void init(int mouseClientPositionX,int mouseClientPositionY) {
		
		this.mouseClientPositionX = mouseClientPositionX;
		this.mouseClientPositionY = mouseClientPositionY;
		this.mouseRelativePositionX = DOMUtil.getMouseRelativePositionX(sourceElement, mouseClientPositionX);
		this.mouseRelativePositionY = DOMUtil.getMouseRelativePositionY(sourceElement, mouseClientPositionY);
		this.widgetStartPositionX = mouseClientPositionX - mouseRelativePositionX;
		this.widgetStartPositionY = mouseClientPositionY - mouseRelativePositionY;
		
		//TODO implement DragOption.POSITION_ABSOLUTE and DragOption.POSITION_RELATIVE options
		if (dragOptions.contains(DragOption.BLOCK_WIDGET)) {
			draggedElement = sourceElement;
		} else if (dragOptions.contains(DragOption.CLONE_WIDGET)) {
			draggedElement = DragAndDropUtil.cloneAndPosition(sourceElement, widgetStartPositionX, widgetStartPositionY, Position.ABSOLUTE);
		} else {
			draggedElement = DragAndDropUtil.adaptAndPosition(sourceElement, widgetStartPositionX, widgetStartPositionY, Position.FIXED);
		}
	}
	
	public ArrayList<DragOption> getDragOptions() {
		return dragOptions;
	}
	
	public void setDragOptions(ArrayList<DragOption> dragOptions) {
		
//		if (dragOptions.contains(DragOption.SILENT)){
//			dragOptions.remove(DragOption.FIRE_DRAG_INIT_EVENT);
//			dragOptions.remove(DragOption.FIRE_DRAG_START_EVENT);
//			dragOptions.remove(DragOption.FIRE_DRAG_MOVE_EVENT);
//			dragOptions.remove(DragOption.FIRE_DRAG_STOP_EVENT);
//			dragOptions.remove(DragOption.FIRE_DROP_OUT_EVENT);
//		} else if (	!dragOptions.contains(DragOption.FIRE_DRAG_INIT_EVENT) &&
//					!dragOptions.contains(DragOption.FIRE_DRAG_START_EVENT) &&
//					!dragOptions.contains(DragOption.FIRE_DRAG_MOVE_EVENT) &&
//					!dragOptions.contains(DragOption.FIRE_DRAG_STOP_EVENT) &&
//					!dragOptions.contains(DragOption.FIRE_DROP_OUT_EVENT)) {
//			dragOptions.add(DragOption.FIRE_DRAG_INIT_EVENT);
//			dragOptions.add(DragOption.FIRE_DRAG_START_EVENT);
//			dragOptions.add(DragOption.FIRE_DRAG_MOVE_EVENT);
//			dragOptions.add(DragOption.FIRE_DRAG_STOP_EVENT);
//			dragOptions.add(DragOption.FIRE_DROP_OUT_EVENT);
//		}
		
		this.dragOptions = dragOptions;
		
	}
	
	public int getMouseRelativePositionX() {
		return mouseRelativePositionX;
	}
	public int getMouseRelativePositionY() {
		return mouseRelativePositionY;
	}
	public Element getDragElement() {
		return draggedElement;
	}
	public Element getSourceElement() {
		return sourceElement;
	}
	public void setSourceElement(Element sourceElement) {
		this.sourceElement = sourceElement;
	}
	public int getWidgetStartPositionX() {
		return widgetStartPositionX;
	}
	public int getWidgetStartPositionY() {
		return widgetStartPositionY;
	}
	public void setSourceWidget(Widget sourceWidget) {
		this.sourceWidget = sourceWidget;
		if (sourceWidget != null) {
			sourceElement = sourceWidget.getElement();
		} else {
			sourceElement = null;
		}
	}
	public Widget getSourceWidget() {
		return sourceWidget;
	}
	public void setMouseClientPositionX(int mouseCurrentPositionX) {
		this.mouseClientPositionX = mouseCurrentPositionX;
	}
	public int getMouseClientPositionX() {
		return mouseClientPositionX;
	}
	public void setMouseClientPositionY(int mouseCurrentPositionY) {
		this.mouseClientPositionY = mouseCurrentPositionY;
	}
	public int getMouseClientPositionY() {
		return mouseClientPositionY;
	}
	public void setMouseClientPosition(int mouseCurrentPositionX, int mouseCurrentPositionY){
		this.mouseClientPositionX = mouseCurrentPositionX;
		this.mouseClientPositionY = mouseCurrentPositionY;
	}
	public void setContainerWidget(Widget containerWidget){
		this.containerWidget = containerWidget;
		if (containerWidget != null) {
			containerElement = containerWidget.getElement();
		} else {
			containerElement = null;
		}
	}
	public Widget getContainerWidget() {
		return containerWidget;
	}
	public Element getContainerElement() {
		return containerElement;
	}
	public void setLeverWidget(DraggableWidget leverWidget) {
		this.leverWidget = leverWidget;
		if (leverWidget != null) {
			this.leverElement = ((UIObject) leverWidget.asWidget()).getElement();
		} else {
			this.leverElement = null;
		}
	}
	public DraggableWidget getLeverWidget() {
		return leverWidget;
	}
	public Element getLeverElement() {
		return leverElement;
	}
}
