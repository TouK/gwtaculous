package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DragOutEvent} events.
 */
public interface DragOutHandler extends EventHandler {

	/**
	 * Called when DragOutEvent is fired.
	 * 
	 * @param event the {@link DragOutEvent} that was fired
	 */
	void onDragOut(DragOutEvent event);
	
}
