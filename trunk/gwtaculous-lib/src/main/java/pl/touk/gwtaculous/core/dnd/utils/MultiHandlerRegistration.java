package pl.touk.gwtaculous.core.dnd.utils;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.event.shared.HandlerRegistration;

public class MultiHandlerRegistration implements HandlerRegistration {
	
	private ArrayList<HandlerRegistration> handlerRegistrations;
	
	public MultiHandlerRegistration(HandlerRegistration...handlerRegistrations){
		this.handlerRegistrations = new ArrayList<HandlerRegistration>(Arrays.asList(handlerRegistrations));
	}
	
	@Override
	public void removeHandler() {
		for (HandlerRegistration handlerRegistration : handlerRegistrations) {
			removeHandlerIfNotNull(handlerRegistration);
		}
		handlerRegistrations.clear();
	}
	
	public static void removeHandlerIfNotNull(HandlerRegistration hr){
		if (hr != null) {
			hr.removeHandler();
		}
	}
	
}
