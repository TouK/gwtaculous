package pl.touk.gwtaculous.core.dnd.event;

/**
 * Handler interface for {@link DragOverEvent} events.
 */
public interface DragOverHandler extends DropHandler {

	/**
	 * Called when DragOverEvent is fired.
	 * 
	 * @param event the {@link DragOverEvent} that was fired
	 */
	void onDragOver(DragOverEvent event);
	
}
