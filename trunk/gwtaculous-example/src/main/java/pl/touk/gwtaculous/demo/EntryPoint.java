package pl.touk.gwtaculous.demo;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Entry point for GWTaculous example usage application.
 *
 * @author Ula Trzaskowska
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {
	
	private FlowPanel chooseDemoPanel;
	private SimplePanel demoPanel;
	private Label effectDemoLink;
	private Label dragAndDropDemoLink;
	
	@Override
	public void onModuleLoad() {
		
		chooseDemoPanel = new FlowPanel();
		demoPanel = new SimplePanel();
		effectDemoLink = new Label("Effects Demo");
		dragAndDropDemoLink = new Label("Drag and Drop Demo");
		
		chooseDemoPanel.getElement().setId("chooseDemoPanel");
		chooseDemoPanel.add(effectDemoLink);
		chooseDemoPanel.add(dragAndDropDemoLink);
		
		effectDemoLink.addClickHandler(new ModuleClickHandler());
		dragAndDropDemoLink.addClickHandler(new ModuleClickHandler());
		
		RootPanel.get().add(chooseDemoPanel);
		RootPanel.get().add(demoPanel);
	}
	
	private class ModuleClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			Object source = event.getSource();
			if (effectDemoLink.equals(source)) {
				demoPanel.clear();
				demoPanel.add(new EffectsDemo());
			} else if (dragAndDropDemoLink.equals(source)) {
				demoPanel.clear();
				demoPanel.add(new DragAndDropDemo());
			}
		}
	}
}
