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

package pl.touk.gwtaculous.helpers;


import pl.touk.gwtaculous.effect.Effect;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * Static helper for java to javascript calls and object transformations.
 */
public class EffectHelper {
	
	private EffectHelper(){}
	
    public static JavaScriptObject buildOptions (EffectOption[] effectOptions) {
        if (effectOptions == null || effectOptions.length == 0) return null;
    	JavaScriptObject jsoOptions = JavaScriptObject.createObject();
        copyOptions(jsoOptions,effectOptions);
        return jsoOptions;
    }
    
    public static void copyOptions(JavaScriptObject jsoOptions, EffectOption[] effectOptions){
        for (int i = 0; i < effectOptions.length; i++) {
        	setOption(jsoOptions, effectOptions[i].getName(), effectOptions[i].getValue());
        }
    }
    
    public static JavaScriptObject init (String effectName, Element element, JavaScriptObject options){
    	if (options != null) return initialize(effectName, element, options);
    	return initialize(effectName, element);
    }
    
    public static JavaScriptObject init (String effectName, Element element, EffectOption... opts){
    	JavaScriptObject options = buildOptions(opts);
    	return init(effectName, element, options);
    }
    
    public static native JavaScriptObject initialize(String effectName, Element element, JavaScriptObject options) /*-{
    	return new $wnd.Effect[effectName](element, options);
   	}-*/;
    
    public static native JavaScriptObject initialize(String effectName, Element element) /*-{
		return new $wnd.Effect[effectName](element);
	}-*/;
	
    public static native void setOption (JavaScriptObject jso, String name, double value) /*-{
	    jso[name] = value;
	}-*/;
    
    public static native void setOption (JavaScriptObject jso, String name, int value) /*-{
	    jso[name] = value;
	}-*/;
    
    public static native void setOption (JavaScriptObject jso, String name, boolean value) /*-{
	    jso[name] = value;
	}-*/;
    
    public static native void setOption (JavaScriptObject jso, String name, String value) /*-{
	    jso[name] = value;
	}-*/;

    public static native void setOption (JavaScriptObject jso, String name, JavaScriptObject value) /*-{
		jso[name] = value;
	}-*/;
    
    public static native void setOption (JavaScriptObject jso, int index, JavaScriptObject value) /*-{
		jso[index] = value;
	}-*/;
    
    public static native void registerNativeBeforeStart (JavaScriptObject options, Effect x)/*-{
		options.beforeStart  = function(){x.@pl.touk.gwtaculous.effect.Effect::onBeforeStart()()};
	}-*/;
	
    public static native void registerNativeBeforeSetup(JavaScriptObject options, Effect x)/*-{
		options.beforeSetup = function(){x.@pl.touk.gwtaculous.effect.Effect::onBeforeSetup()()};
	}-*/;
	
    public static native void registerNativeAfterSetup(JavaScriptObject options, Effect x)/*-{
		options.afterSetup = function(){x.@pl.touk.gwtaculous.effect.Effect::onAfterSetup()()};
	}-*/;
	
    public static native void registerNativeBeforeUpdate(JavaScriptObject options, Effect x)/*-{
		options.beforeUpdate = function(){x.@pl.touk.gwtaculous.effect.Effect::onBeforeUpdate()()};
	}-*/;
	
    public static native void registerNativeAfterUpdate(JavaScriptObject options, Effect x)/*-{
		options.afterUpdate = function(){x.@pl.touk.gwtaculous.effect.Effect::onAfterUpdate()()};
	}-*/;
	
    public static native void registerNativeAfterFinish(JavaScriptObject options, Effect x)/*-{
		options.afterFinish = function(){x.@pl.touk.gwtaculous.effect.Effect::onAfterFinish()()};
	}-*/;
	
}
