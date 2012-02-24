package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DragStopEvent} events.
 */
public interface DragStopHandler extends EventHandler {

	/**
	 * Called when DragStopEvent is fired.
	 * 
	 * @param event the {@link DragStopEvent} that was fired
	 */
	void onDragStop(DragStopEvent event);
	
}
