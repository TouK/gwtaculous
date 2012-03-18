package pl.touk.gwtaculous.demo;

import pl.touk.gwtaculous.dnd.DragAndDrop;
import pl.touk.gwtaculous.dnd.DragOption;
import pl.touk.gwtaculous.dnd.DropOption;
import pl.touk.gwtaculous.dnd.event.DragOutEvent;
import pl.touk.gwtaculous.dnd.event.DragOutHandler;
import pl.touk.gwtaculous.dnd.event.DragOverEvent;
import pl.touk.gwtaculous.dnd.event.DragOverHandler;
import pl.touk.gwtaculous.dnd.event.DropInEvent;
import pl.touk.gwtaculous.dnd.event.DropInHandler;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;

public class DragAndDropDemo extends Composite {
	
	private FlowPanel mainPanel;
	private FlowPanel flowPanel_1;
	private FlowPanel flowPanel_3;
	private FlowPanel flowPanel_4;
	private CustomFlowPanel flowPanel_6;
	private FlowPanel flowPanel_7;
	private EvenMoreCustomFlowPanel flowPanel_9;
	private Image image_2;
	private Image image_3;
	private Image image_1;
	
	public DragAndDropDemo() {
		mainPanel = new FlowPanel();
		flowPanel_1 = new FlowPanel();
		flowPanel_3 = new FlowPanel();
		flowPanel_4 = new FlowPanel();
		flowPanel_6 = new CustomFlowPanel();
		flowPanel_7 = new FlowPanel();
		flowPanel_9 = new EvenMoreCustomFlowPanel();
		
		image_1 = new Image("images/big_colony_2.png");
		image_2 = new Image("images/big_fleet_2.png");
		image_3 = new Image("images/big_leadership_2.png");
		
		initWidgets();
	}

	private void initWidgets() {
		initMainPanel();
		initSmallPanels();
		initImages();
		addDragAndDropCapabilities();
		initWidget(mainPanel);
	}
	
	private void initMainPanel(){
		mainPanel.getElement().setId("mainPanel");
		mainPanel.add(flowPanel_1);
		mainPanel.add(flowPanel_3);
		mainPanel.add(flowPanel_4);
		mainPanel.add(flowPanel_6);
		mainPanel.add(flowPanel_7);
		mainPanel.add(flowPanel_9);
	}
	
	private void initSmallPanels(){
		flowPanel_1.setStyleName("selector selector_1");
		flowPanel_3.setStyleName("selector selector_3");
		flowPanel_4.setStyleName("selector selector_4");
		flowPanel_6.setStyleName("selector selector_6");
		flowPanel_7.setStyleName("selector selector_7");
		flowPanel_9.setStyleName("selector selector_9");
		
		flowPanel_1.add(image_1);
		flowPanel_4.add(image_2);
		flowPanel_7.add(image_3);
	}
	
	private void initImages() {
		image_1.setStyleName("small square");
		image_1.getElement().getStyle().setTop(25, Unit.PX);
		image_1.getElement().getStyle().setLeft(25, Unit.PX);
		
		image_2.setStyleName("small rounded");
		image_2.getElement().getStyle().setTop(75, Unit.PX);
		image_2.getElement().getStyle().setLeft(55, Unit.PX);
		
		image_3.setStyleName("small rotated");
		image_3.getElement().getStyle().setTop(45, Unit.PX);
		image_3.getElement().getStyle().setLeft(35, Unit.PX);
	}

	private void addDragAndDropCapabilities() {
		
		DragAndDrop.makeMeDraggable(image_1);
		
		DragAndDrop.makeMeDraggable(image_2, DragOption.CLONE_WIDGET, DragOption.MOVE_TO_CLONE);
		
		DragAndDrop.makeMeDraggable(image_3, DragOption.AUTO_MOVE_CURSOR, DragOption.SILENT);
		
		DragAndDrop.makeMeDroppable(flowPanel_3);
		
		DragAndDrop.makeMeDroppable(flowPanel_6);
		
		DragAndDrop.makeMeDroppable(flowPanel_9, DropOption.SILENT, DropOption.FIRE_DRAG_OVER_OUT_EVENT);
	}
	
	private class CustomFlowPanel extends FlowPanel implements DropInHandler {

		public void onDropIn(DropInEvent event) {
			Window.alert("Something was dropped here !");
		}
	}
	
	private class EvenMoreCustomFlowPanel extends FlowPanel implements DropInHandler, DragOverHandler, DragOutHandler {

		public void onDropIn(DropInEvent event) {
			Window.alert("Something was dropped over there !");
		}

		@Override
		public void onDragOut(DragOutEvent event) {
			flowPanel_9.removeStyleName("highlighted");
		}

		@Override
		public void onDragOver(DragOverEvent event) {
			flowPanel_9.addStyleName("highlighted");
		}
	}
	
	
	
}
