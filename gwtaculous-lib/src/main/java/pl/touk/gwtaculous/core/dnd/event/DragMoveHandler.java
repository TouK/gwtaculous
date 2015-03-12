package pl.touk.gwtaculous.core.dnd.event;

/**
 * Handler interface for {@link DragMoveEvent} events.
 */
public interface DragMoveHandler extends DragHandler {

	/**
	 * Called when DragMoveEvent is fired.
	 * 
	 * @param event the {@link DragMoveEvent} that was fired
	 */
	void onDragMove(DragMoveEvent event);
	
}
