package pl.touk.gwtaculous.dnd;

import java.util.ArrayList;
import java.util.Arrays;

import pl.touk.gwtaculous.dnd.event.DragStopEvent;
import pl.touk.gwtaculous.dnd.event.DropInHandler;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main registration facade. Here you can easily make your widgets "draggable" (able to drag) or "droppable" (able to be drop box).
 * To cancel "draggable" or "droppable" registration (cancel drag or drop capabilities) use standard HandlerRegistration cancel() method.
 */
public class DragAndDrop {
	
	/**
	 * Gets {@link EventBus} object used by DragAndDropController for handling drag and drop related events.
	 * 
	 * @return EventBus used by DragAndDropController
	 */
	public static EventBus getEventBus(){
		return DragAndDropController.getInstance().getEventBus();
	}
	
	
	/**
	 * Makes Widget drag capable. This function adds to Widget all necessary mouse handlers to determine that drag process started. 
	 * 
	 * @param Widget to get drag capabilities
	 * @param List of {@link DragOption} used to parameterize drag capabilities
	 * @return HandlerRegistration used to remove drag capabilities (with all mouse handlers)
	 */
	public static HandlerRegistration makeMeDraggable(Widget draggable, DragOption...options) {
		
		ArrayList<DragOption> dragOptions = new ArrayList<DragOption>(Arrays.asList(options));
		DragObject d = new DragObject(draggable, new DraggableWidget(draggable), dragOptions);
		
		return DragAndDropController.getInstance().makeMeDraggable(d);
	}
	
	/**
	 * Makes Widget drag capable. Drag is restricted to container Widget size and position.
	 * Container widget does not have to be parent-related widget to dragged widget.
	 * This function adds to Widget all necessary mouse handlers to determine that drag process started. 
	 * 
	 * @param Widget to get drag capabilities
	 * @param Container Widget that will limit drag area
	 * @param List of {@link DragOption} used to parameterize drag capabilities
	 * @return HandlerRegistration used to remove drag capabilities (with all mouse handlers)
	 */
	public static HandlerRegistration makeMeDraggable(Widget draggable, Widget containerWidget, DragOption...options) {
	
		ArrayList<DragOption> dragOptions = new ArrayList<DragOption>(Arrays.asList(options));
		DragObject d = new DragObject(draggable, new DraggableWidget(draggable), dragOptions);
		
		d.setContainerWidget(containerWidget);
		
		return DragAndDropController.getInstance().makeMeDraggable(d);
	}
	
	/**
	 * Makes Widget a drag lever. Pulling lever drags another widget (drag proxy). 
	 * This function adds to Widget all necessary mouse handlers to determine that drag process started. 
	 * 
	 * @param Lever widget to be pulled by mouse action
	 * @param Widget to be moved if lever widget is pulled
	 * @param List of {@link DragOption} used to parameterize drag capabilities
	 * @return HandlerRegistration used to remove drag capabilities (with all mouse handlers)
	 */
	public static HandlerRegistration makeMeDraggableLever(Widget leverWidget, Widget draggWidget, DragOption...options) {
		
		ArrayList<DragOption> dragOptions = new ArrayList<DragOption>(Arrays.asList(options));
		DragObject d = new DragObject(draggWidget, new DraggableWidget(leverWidget), dragOptions);
		
		return DragAndDropController.getInstance().makeMeDraggable(d);
	}
	
	/**
	 * Makes Widget a drag lever. Pulling lever drags another widget (drag proxy). Drag is restricted to container Widget size and position.
	 * Container widget does not have to be parent-related widget to dragged widget.
	 * This function adds to Widget all necessary mouse handlers to determine that drag process started. 
	 * 
	 * @param Lever widget to be pulled by mouse action
	 * @param Widget to be moved if lever widget is pulled
	 * @param Container Widget that will limit drag area
	 * @param List of {@link DragOption} used to parameterize drag capabilities
	 * @return HandlerRegistration used to remove drag capabilities (with all mouse handlers)
	 */
	public static HandlerRegistration makeMeDraggableLever(Widget leverWidget, Widget draggWidget, Widget containerWidget, DragOption...options) {
		
		ArrayList<DragOption> dragOptions = new ArrayList<DragOption>(Arrays.asList(options));
		DragObject d = new DragObject(draggWidget, new DraggableWidget(leverWidget), dragOptions);
		
		d.setContainerWidget(containerWidget);
		
		return DragAndDropController.getInstance().makeMeDraggable(d);
	}
	
	/**
	 * Makes Widget a drop box. Drop boxes (droppables) are responsible for launching drop related events.
	 * If Widget implements any drop handlers: {@link DropInHandler}, {@link DragOverHandler}, {@link DragOutHandler}
	 * it will also automatically hear on EventBus for corresponding events. Some drop events depends on drag events
	 * (for example {@link DropInEvent} and {@link DragStopEvent})
	 * 
	 * @param Widget to get drop capabilities
	 * @param List of {@link DropOption} used to parameterize drop capabilities
	 * @return HandlerRegistration used to remove drop capabilities
	 */
	public static HandlerRegistration makeMeDroppable(Widget droppable, DropOption...options) {
		
		ArrayList<DropOption> dropOptions = new ArrayList<DropOption>(Arrays.asList(options));
		DropObject d = new DropObject(droppable, droppable, dropOptions);
		
		return DragAndDropController.getInstance().makeMeDroppable(d);
	}		
	
}
