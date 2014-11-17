package pl.touk.gwtaculous.dnd.event;

/**
 * Handler interface for {@link DragOutEvent} events.
 */
public interface DragOutHandler extends DropHandler {

	/**
	 * Called when DragOutEvent is fired.
	 * 
	 * @param event the {@link DragOutEvent} that was fired
	 */
	void onDragOut(DragOutEvent event);
	
}
