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
package pl.touk.gwtaculous.effects.effect;

import com.google.gwt.user.client.ui.Widget;

/**
 * This effect changes an element’s opacity (transparency).
 * 
 * @author Michał Trzaskowski
 *
 */
public class EffectOpacity extends Effect {
	
	public EffectOpacity(Widget widget) {
		super(widget);
	}
	
	public EffectOpacity(Widget widget, double from, double to) {
		super(widget);
		setFrom(from);
		setTo(to);
	}
	
	public void setFrom(double from) {
		super.setFrom(from);
	}
	
	public void setTo(double to) {
		super.setTo(to);
	}

}
