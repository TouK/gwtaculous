package pl.touk.gwtaculous.demo;

import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point for GWTaculous example usage application.
 *
 * @author Ula Trzaskowska
 */
public class EntryPoint implements com.google.gwt.core.client.EntryPoint {

	@Override
	public void onModuleLoad() {
		EffectsDemo demo = new EffectsDemo();
		RootPanel.get().add(demo);
	}

}
