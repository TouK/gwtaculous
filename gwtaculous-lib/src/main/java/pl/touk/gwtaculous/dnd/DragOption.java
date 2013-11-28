package pl.touk.gwtaculous.dnd;

public enum DragOption {
	
	/** If source Widget is also Handler for one of drag events, it will be automatically registered on EventBus to listen for designated events */
	AUTO_REGISTER,
	
	/** Widget position will be NOT changed during drag process*/
	BLOCK_WIDGET,
	
	/** Clones source element and perform actual drag on a copy, clone is automatically terminated when drag stops */
	CLONE_WIDGET, 
	
	/** After successful drag fix position of source element to last known position of its dragged clone */
	MOVE_TO_CLONE, 
	
	/** 
	 * Experimental: After unsuccessful drag position and styles of source element are restored to state before drag
	 * This is default when CLONE_WIDGET is selected
	 */
	RESTORE_IF_REJECTED, 	
	
	/** Drag movement is restricted only to X axis, this option is mutually exclusive with MOVE_AXIS_Y */
	MOVE_AXIS_X, 
	
	/** Drag movement is restricted only to Y axis, this option is mutually exclusive with MOVE_AXIS_X */
	MOVE_AXIS_Y, 
	
	/** 
	 * There will be no DragObject related events fired except those given as parameters in DragOption.
	 * DragObject related events: {@link DragInitEvent, {@link DragStartEvent}, {@link DragMoveEvent}, {@link DragStopEvent}, {@link DropOutEvent}
	 * This will also disable drop related events because they base on drag events
	 */
	SILENT, 
	
	/** {@link DragInitEvent} fired during drag process */
	FIRE_DRAG_INIT_EVENT, 
	
	/** {@link DragStartEvent} fired during drag process */
	FIRE_DRAG_START_EVENT, 
	
	/** {@link DragMoveEvent} fired during drag process, this will also disable drag over and drag out events */
	FIRE_DRAG_MOVE_EVENT, 
	
	/** {@link DragStopEvent} fired during drag process, this will also disable drop in and drop out events */
	FIRE_DRAG_STOP_EVENT, 
	
	/** {@link DropOutEvent} fired after dragging element that was dropped somewhere else then on drop box) */
	FIRE_DROP_OUT_EVENT, 
	
	/** Mouse cursor over dragged element is set to css "move" value during drag process */
	AUTO_MOVE_CURSOR,
	
}
