/*
 * Copyright (c) 2010 TouK.pl
 * 
 * Authors: Michał Trzaskowski, Urszula Trzaskowska
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.touk.gwtaculous.core.effects.effect;

import pl.touk.gwtaculous.core.effects.helpers.EffectHelper;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * This effect changes an elements width and height dimensions and the base for em units. 
 * This allows for smooth, automatic relative scaling of elements contained within the scaled element.
 */
public class EffectScale extends Effect {
	
	double scaleTo;
	
	public EffectScale(Widget widget, double scaleTo) {
		super(widget);
		this.scaleTo = scaleTo;
	}
	@Override
	public JavaScriptObject initEffect() {
		return initialize(getElement(), scaleTo, getOptions());
	}
	
	public void setScaleX(boolean scaleX){
		EffectHelper.setOption(getOptions(), "scaleX", scaleX);
	}
	
	public void setScaleY(boolean scaleY){
		EffectHelper.setOption(getOptions(), "scaleY", scaleY);
	}
	
	public void setScaleContent(boolean scaleContent){
		EffectHelper.setOption(getOptions(), "scaleContent", scaleContent);
	}
	
	public void setScaleFromCenter(boolean scaleFromCenter){
		EffectHelper.setOption(getOptions(), "scaleFromCenter", scaleFromCenter);
	}
	
	public void setScaleMode(ScaleMode scaleMode){
		EffectHelper.setOption(getOptions(), "scaleMode", scaleMode.toString());
	}
	
	public void setScaleFrom(double percentage){
		EffectHelper.setOption(getOptions(), "scaleFrom", percentage);
	}
	
    private static native JavaScriptObject initialize(Element element, double scaleTo, JavaScriptObject options) /*-{
		return new $wnd.Effect.Scale(element, scaleTo, options);
	}-*/;

}
