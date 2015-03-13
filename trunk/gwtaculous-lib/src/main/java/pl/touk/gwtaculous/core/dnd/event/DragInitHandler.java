package pl.touk.gwtaculous.core.dnd.event;

/**
 * Handler interface for {@link DragInitEvent} events.
 */
public interface DragInitHandler extends DragHandler {
	
	/**
	 * Called when DragInitEvent is fired.
	 * 
	 * @param event the {@link DragInitEvent} that was fired
	 */
	void onDragInit(DragInitEvent event);
}
