package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DragInitEvent} events.
 */
public interface DragInitHandler extends EventHandler {
	
	/**
	 * Called when DragInitEvent is fired.
	 * 
	 * @param event the {@link DragInitEvent} that was fired
	 */
	void onDragInit(DragInitEvent event);
}
