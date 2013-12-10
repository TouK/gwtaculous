package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DragStartEvent} events.
 */
public interface DragStartHandler extends DragHandler {

	/**
	 * Called when DragStartEvent is fired.
	 * 
	 * @param event the {@link DragStartEvent} that was fired
	 */
	void onDragStart(DragStartEvent event);
	
}
