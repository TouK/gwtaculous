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

import pl.touk.gwtaculous.effects.helpers.EffectHelper;

import com.google.gwt.user.client.ui.Widget;

/**
 * This effect moves an element by modifying its position attributes.
 * X or Y coordinates required (either the new absolute target of the effect elements position or the modifier of its current position), depending on the mode option
 * 
 */
public class EffectMove extends Effect {

	public static enum Mode {relative,absoute};

	public EffectMove(Widget widget,  int x, int y) {
		super(widget);
		setCords(x, y);
	}
	
	public void setCords(int x, int y) {
		setX(x);
		setY(y);
	}
	
	/**
	 * Either the new absolute target of the effect elements left value or the modifier of its current left value, depending on the mode option 
	 */
	public void setX(int x){
		EffectHelper.setOption(getOptions(), "x", x);
	}
	
	/**
	 * Either the new absolute target of the effect elements top value or the modifier of its current top value, depending on the mode option 
	 */
	public void setY(int y){
		EffectHelper.setOption(getOptions(), "y", y);
	}
	
	/**
	 * Specifies if the element is moved absolutely or relative to its own position
	 */
	public void setMode(Mode mode){
		EffectHelper.setOption(getOptions(), "mode", mode.toString());
	}

}
