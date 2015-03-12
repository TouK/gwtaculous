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

import com.google.gwt.user.client.ui.Widget;

/**
 * Moves the element slightly to the left, then to the right, repeatedly.
 */
public class EffectShake extends Effect {
	
	public EffectShake(Widget widget) {
		super(widget);
	}
	
	/**
	 * Number of pixels to move horizontally, defaults to 20
	 */
	public void setDistance(int distance){
		EffectHelper.setOption(getOptions(), "distance", distance);
	}
}
