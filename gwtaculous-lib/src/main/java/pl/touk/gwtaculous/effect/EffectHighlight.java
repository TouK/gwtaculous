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
 * This effect flashes a color as the background of an element.
 *
 */
public class EffectHighlight extends Effect {
	
	public EffectHighlight(Widget widget) {
		super(widget);
	}
	
	/**
	 * Sets the color of first frame of the highlight (full html color syntax required: #dddddd). 
	 * Defaults to ”#ffff99” (a light yellow).
	 */
	public void setStartColor(String htmlColor) {
		EffectHelper.setOption(getOptions(), "startcolor", htmlColor);
	}
	
	/**
	 * Sets the color of the last frame of the highlight (full html color syntax required: #dddddd). 
	 * Defaults to ”#ffffff” (white).
	 */
	public void setEndColor(String htmlColor){
		EffectHelper.setOption(getOptions(), "startcolor", htmlColor);
	}
	
	/**
	 * Sets the background-color of the element after the highlight has finished (full html color syntax required: #dddddd). 
	 * Defaults to the current background-color of the highlighted element (taken from CSS rgb color triplet)
	 */
	public void setRestoreColor(String htmlColor){
		EffectHelper.setOption(getOptions(), "restorecolor", htmlColor);
	}
	
	/**
	 * Unless this is set to true, any background image on the element will not be preserved. 
	 */
	public void keepBackgroundImage(boolean keepBackgroundImage){
		EffectHelper.setOption(getOptions(), "keepBackgroundImage", keepBackgroundImage);
	}

}
