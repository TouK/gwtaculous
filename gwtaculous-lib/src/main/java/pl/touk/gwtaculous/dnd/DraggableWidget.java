package pl.touk.gwtaculous.dnd;

import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * Basic wrapper for Widget that becomes draggable. Used in DragAndDropController to automatically register necessary mouse event handlers.
 *
 */
public class DraggableWidget implements HasMouseMoveHandlers, HasMouseUpHandlers, HasMouseDownHandlers, IsWidget {
	
	private Widget widget;
	
	public DraggableWidget(Widget widget) {
		this.widget = widget;
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		widget.fireEvent(event);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return widget.addDomHandler(handler, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return widget.addDomHandler(handler, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return widget.addDomHandler(handler, MouseDownEvent.getType());
	}
	
}
