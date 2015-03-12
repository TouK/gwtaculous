package pl.touk.gwtaculous.core.dnd;

import java.util.ArrayList;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Data aggregate object for drop related objects and properties.
 * Do not use reference to that object for later use, it's data is valid only for event processing.
 *
 */
public class DropObject {
	
	private boolean dragOver;

	private Widget sourceWidget;
	private Element sourceElement;
	
	private Widget containerWidget;
	private Element containerElement;
	
	private ArrayList<DropOption> dropOptions;
	
	public DropObject(Widget sourceWidget, Widget containerWidget, ArrayList<DropOption> dropOptions) {
		setSourceWidget(sourceWidget);
		setContainerWidget(containerWidget);
		setDropOptions(dropOptions);
		setDragOver(false);
	}
	
	public ArrayList<DropOption> getDropOptions() {
		return dropOptions;
	}
	public void setDropOptions(ArrayList<DropOption> dropOptions) {
		this.dropOptions = dropOptions;
	}
	public Widget getContainerWidget() {
		return containerWidget;
	}
	public void setContainerWidget(Widget containerWidget) {
		this.containerWidget = containerWidget;
		if (containerWidget != null) {
			containerElement = containerWidget.getElement();
		} else {
			containerElement = null;
		}
	}
	public Element getContainerElement() {
		return containerElement;
	}
	public Widget getSourceWidget(){
		return sourceWidget;
	}
	public void setSourceWidget(Widget sourceWidget) {
		this.sourceWidget = sourceWidget;
		if (sourceWidget != null) {
			sourceElement = sourceWidget.getElement();
		} else {
			sourceElement = null;
		}
	}
	public Element getSourceElement() {
		return sourceElement;
	}
	public void setDragOver(boolean dragOver) {
		this.dragOver = dragOver;
	}
	public boolean isDragOver() {
		return dragOver;
	}

	
	
	
}
