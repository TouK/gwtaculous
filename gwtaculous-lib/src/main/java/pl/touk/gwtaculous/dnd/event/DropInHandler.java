package pl.touk.gwtaculous.dnd.event;

/**
 * Handler interface for {@link DropInEvent} events.
 */
public interface DropInHandler extends DropHandler {

	/**
	 * Called when DropInEvent is fired.
	 * 
	 * @param event the {@link DropInEvent} that was fired
	 */
	void onDropIn(DropInEvent event);

}
