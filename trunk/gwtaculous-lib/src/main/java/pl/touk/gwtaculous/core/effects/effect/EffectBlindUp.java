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
 * This effect simulates a window blind up, where the contents of the affected elements stay in place.
 *
 */
public class EffectBlindUp extends Effect {
	
	public EffectBlindUp(Widget widget) {
		super(widget);
	}
	
	public void setScaleX(boolean scaleX) {
		EffectHelper.setOption(getOptions(), "scaleX", scaleX);
	}

	public void setScaleY(boolean scaleY) {
		EffectHelper.setOption(getOptions(), "scaleY", scaleY);
	}

	public void setScaleContent(boolean scaleContent) {
		EffectHelper.setOption(getOptions(), "scaleContent", scaleContent);
	}

	public void setScaleFromCenter(boolean scaleFromCenter) {
		EffectHelper.setOption(getOptions(), "scaleFromCenter", scaleFromCenter);
	}

	public void setScaleMode(ScaleMode scaleMode) {
		EffectHelper.setOption(getOptions(), "scaleMode", scaleMode.toString());
	}

	public void setScaleFrom(int scaleFrom) {
		EffectHelper.setOption(getOptions(), "scaleFrom", scaleFrom);
	}

	public void setScaleTo(int scaleTo) {
		EffectHelper.setOption(getOptions(), "scaleTo", scaleTo);
	}
	
}
