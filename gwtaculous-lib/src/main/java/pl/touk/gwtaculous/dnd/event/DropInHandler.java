package pl.touk.gwtaculous.dnd.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * Handler interface for {@link DropInEvent} events.
 */
public interface DropInHandler extends EventHandler {

	/**
	 * Called when DropInEvent is fired.
	 * 
	 * @param event the {@link DropInEvent} that was fired
	 */
	void onDropIn(DropInEvent event);

}
