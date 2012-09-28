package pl.touk.gwtaculous.dnd;

public enum DropOption {
	
	/** If source Widget is also Handler for one of drop events, it will be automatically registered on EventBus to listen for designated events */
	AUTO_REGISTER,
	
	/** {@link DragStopEvent} dependent. Given container widget will "adopt" dragged widget, dragged widget will become child element of this panel (by default container panel = drop box) */
	ADOPT_WIDGET, 
	
	/** {@link DragOverEvent} and {@link DragOutEvent} dependent. Drop panel will visually hint were dragged widget will be placed after drop */
	DROP_HINT, 
	
	/** 
	 * There will be no DropObject related events fired except those given as parameters in DragOption 
	 * DropObject related events: {@link DropInEvent}, {@link DragOverEvent}, {@link DragOutEvent}
	 */
	SILENT, 
	
	/** {@link DropInEvent} fired after successful drop */
	FIRE_DROP_IN_EVENT, 
	
	/** {@link DragOverEvent} and {@link DragOutEvent} fired after drag over / out drop box */
	FIRE_DRAG_OVER_OUT_EVENT, 

}
