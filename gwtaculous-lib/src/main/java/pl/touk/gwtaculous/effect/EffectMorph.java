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
package pl.touk.gwtaculous.effect;

import pl.touk.gwtaculous.helpers.EffectHelper;

import com.google.gwt.user.client.ui.Widget;

/**
 * This effect changes the CSS properties of an element.
 * Requires target "style" attribute 
 */
public class EffectMorph extends Effect {	
	
	/**
	 * @param widget The widget the effect is applied to
	 * @param style The target style of your element, as a string written with the standard CSS syntax (i.e: "background:#f00; color: #fff;"), a hash, or a CSS class name.
	 */
	public EffectMorph(Widget widget, String style) {
		super(widget);
		setStyle(style);
	}
	
	private void setStyle(String style){
		EffectHelper.setOption(getOptions(), "style", style);
	}

}
