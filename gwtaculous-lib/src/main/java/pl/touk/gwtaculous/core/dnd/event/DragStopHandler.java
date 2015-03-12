package pl.touk.gwtaculous.core.dnd.event;

/**
 * Handler interface for {@link DragStopEvent} events.
 */
public interface DragStopHandler extends DragHandler {

	/**
	 * Called when DragStopEvent is fired.
	 * 
	 * @param event the {@link DragStopEvent} that was fired
	 */
	void onDragStop(DragStopEvent event);
	
}
