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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.touk.gwtaculous.effects.helpers.EffectHelper;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * This is a special effect which allows to combine more than one core effect into a parallel effect. 
 *
 */
public class EffectParallel extends Effect {
	
	private List<Effect> effects;
	private JavaScriptObject effectsArray;
	
	public EffectParallel() {
		super();
		this.effectsArray = JavaScriptObject.createArray();
		this.effects = new ArrayList<Effect>();
	}
	
	public EffectParallel(Effect... effects){
		this();
		this.effects.addAll(Arrays.asList(effects));
	}
	
	public void addEffect(Effect effect){
		effects.add(effect);
	}
	
	public void clear(){
		effects.clear();
	}

	@Override
	protected JavaScriptObject initEffect() {
		copyEffects();
		return parallelEffect(effectsArray, getOptions()); 
	}
	
	private void copyEffects() {
		for (int i=0; i < effects.size(); i++) {
			effects.get(i).setSync(true);
			EffectHelper.setOption(effectsArray, i, effects.get(i).initEffect());
		}
	}

	public native static JavaScriptObject parallelEffect (JavaScriptObject effects, JavaScriptObject opts) /*-{
		return new $wnd.Effect.Parallel($wnd.Array.from(effects), opts);
	}-*/;
		
}
