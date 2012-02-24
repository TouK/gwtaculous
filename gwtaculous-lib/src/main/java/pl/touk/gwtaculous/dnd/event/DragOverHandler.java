package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DragOverEvent} events.
 */
public interface DragOverHandler extends EventHandler {

	/**
	 * Called when DragOverEvent is fired.
	 * 
	 * @param event the {@link DragOverEvent} that was fired
	 */
	void onDragOver(DragOverEvent event);
	
}
