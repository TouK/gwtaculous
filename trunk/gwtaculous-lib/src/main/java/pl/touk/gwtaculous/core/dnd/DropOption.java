package pl.touk.gwtaculous.core.dnd;

public enum DropOption {
	
	/** If source Widget is also Handler for one of drop events, it will be automatically registered on EventBus to
     * listen for designated events */
	AUTO_REGISTER,
	
	/** {@link pl.touk.gwtaculous.core.dnd.event.DragStopEvent} dependent. Given container widget will "adopt" dragged widget,
     * dragged widget will become child element of this panel (by default container panel = drop box) */
	ADOPT_WIDGET,
	
	/** 
	 * There will be no DropObject related events fired except those given as parameters in DragOption 
	 * DropObject related events:
     * {@link pl.touk.gwtaculous.core.dnd.event.DropInEvent},
     * {@link pl.touk.gwtaculous.core.dnd.event.DragOverEvent},
     * {@link pl.touk.gwtaculous.core.dnd.event.DragOutEvent}
	 */
	SILENT, 
	
	/** {@link pl.touk.gwtaculous.core.dnd.event.DropInEvent} fired after successful drop */
	FIRE_DROP_IN_EVENT, 
	
	/** {@link pl.touk.gwtaculous.core.dnd.event.DragOverEvent} and {@link pl.touk.gwtaculous.core.dnd.event.DragOutEvent} fired after drag over / out drop box */
	FIRE_DRAG_OVER_OUT_EVENT, 

}
