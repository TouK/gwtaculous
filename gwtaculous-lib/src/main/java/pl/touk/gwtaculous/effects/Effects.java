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

package pl.touk.gwtaculous.effects;

import pl.touk.gwtaculous.effects.helpers.EffectHelper;
import pl.touk.gwtaculous.effects.helpers.EffectOption;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Static facade to scriptaculous animation effects that enables simple direct effect call
 * Example: Effects.appear(Widget) launches animation effect "appear" on Widget with default set of options
 *
 */
public class Effects {
	
	public static enum EffectName {
		Appear, 
		BlindDown, 
		BlindUp, 
		DropOut, 
		Fade, 
		Fold, 
		Grow, 
		Highlight, 
		Morph, 
		Move, 
		Opacity, 
		Puff, 
		Pulsate, 
		Scale, 
		ScrollTo, 
		Shake, 
		Shrink,
		SlideDown,
		SlideUp,
		Squish,
		SwitchOff
	};
	
	private Effects(){}
	
	public static void appear (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Appear.toString(), element, opts);
    }
	public static void appear (Widget widget, EffectOption... opts) {
    	appear(widget.getElement(), opts);
    }
    
    public static void blindDown (Widget widget, EffectOption... opts) {
    	blindDown(widget.getElement(), opts);
    }
    public static void blindDown (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.BlindDown.toString(), element, opts);
    }
    
    public static void blindUp (Widget widget, EffectOption... opts) {
    	blindUp(widget.getElement(), opts);
    }
    public static void blindUp (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.BlindUp.toString(), element, opts);
    }
    
    public static void dropOut (Widget widget, EffectOption... opts) {
    	dropOut(widget.getElement(), opts);
    }
    public static void dropOut (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.DropOut.toString(), element, opts);
    }
    
    public static void fade (Widget widget, EffectOption... opts) {
    	fade(widget.getElement(), opts);
    }
    public static void fade (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Fade.toString(), element, opts);
    }
    
    public static void fold (Widget widget, EffectOption... opts) {
    	fold(widget.getElement(), opts);
    }
    public static void fold (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Fold.toString(), element, opts);
    }
    
    public static void grow (Widget widget, EffectOption... opts) {
    	grow(widget.getElement(), opts);
    }
    public static void grow (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Grow.toString(), element, opts);
    }
    
    public static void highlight (Widget widget, EffectOption... opts) {
    	highlight(widget.getElement(), opts);
    }
    public static void highlight (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Highlight.toString(), element, opts);
    }
    
    public static void move (Widget widget, EffectOption... opts) {
    	move(widget.getElement(), opts);
    }
    public static void move (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Move.toString(), element, opts);
    }
    
    public static void morph (Widget widget, EffectOption... opts) {
    	morph(widget.getElement(), opts);
    }
    public static void morph (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Morph.toString(), element, opts);
    }
 
    public static void opacity (Widget widget, EffectOption... opts) {
    	opacity(widget.getElement(), opts);
    }
    public static void opacity (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Opacity.toString(), element, opts);
    }
    
    public static void puff (Widget widget, EffectOption... opts) {
    	puff(widget.getElement(), opts);
    }
    public static void puff (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Puff.toString(), element, opts);
    }
    
    public static void pulsate (Widget widget, EffectOption... opts) {
    	pulsate(widget.getElement(), opts);
    }
    public static void pulsate (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Pulsate.toString(), element, opts);
    }
    
    public static void scale (Widget widget, EffectOption... opts) {
    	scale(widget.getElement(), opts);
    }
    public static void scale (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Scale.toString(), element, opts);
    }
    
    public static void scrollTo (Widget widget, EffectOption... opts) {
    	scrollTo(widget.getElement(), opts);
    }
    public static void scrollTo (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.ScrollTo.toString(), element, opts);
    }
    
    public static void shake (Widget widget, EffectOption... opts) {
    	shake(widget.getElement(), opts);
    }
    public static void shake (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Shake.toString(), element, opts);
    }
    
    public static void shrink (Widget widget, EffectOption... opts) {
    	shrink(widget.getElement(), opts);
    }
    public static void shrink (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Shrink.toString(), element, opts);
    }
    
    public static void slideDown (Widget widget, EffectOption... opts) {
    	slideDown(widget.getElement(), opts);
    }
    public static void slideDown (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.SlideDown.toString(), element, opts);
    }
    
    public static void slideUp (Widget widget, EffectOption... opts) {
    	slideUp(widget.getElement(), opts);
    }
    public static void slideUp (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.SlideUp.toString(), element, opts);
    }
    
    public static void squish (Widget widget, EffectOption... opts) {
    	squish(widget.getElement(), opts);
    }
    public static void squish (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.Squish.toString(), element, opts);
    }
    
    public static void switchOff (Widget widget, EffectOption... opts) {
    	switchOff(widget.getElement(), opts);
    }
    public static void switchOff (Element element, EffectOption... opts) {
    	EffectHelper.init(EffectName.SwitchOff.toString(), element, opts);
    }
    

}
