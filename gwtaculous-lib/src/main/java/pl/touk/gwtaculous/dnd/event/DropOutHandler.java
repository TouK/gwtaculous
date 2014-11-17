package pl.touk.gwtaculous.dnd.event;

/**
 * Handler interface for {@link DropOutEvent} events.
 */
public interface DropOutHandler extends DragHandler {

	/**
	 * Called when DropOutEvent is fired.
	 * 
	 * @param event the {@link DropOutEvent} that was fired
	 */
	void onDropOut(DropOutEvent event);
	
}
