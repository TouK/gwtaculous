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

import pl.touk.gwtaculous.effects.handler.AfterFinishHandler;
import pl.touk.gwtaculous.effects.handler.AfterSetupHandler;
import pl.touk.gwtaculous.effects.handler.AfterUpdateHandler;
import pl.touk.gwtaculous.effects.handler.BeforeSetupHandler;
import pl.touk.gwtaculous.effects.handler.BeforeStartHandler;
import pl.touk.gwtaculous.effects.handler.BeforeUpdateHandler;
import pl.touk.gwtaculous.effects.handler.ScheduledFinishHandler;
import pl.touk.gwtaculous.effects.helpers.EffectHelper;
import pl.touk.gwtaculous.effects.helpers.EffectOption;
import pl.touk.gwtaculous.effects.helpers.EffectQueue;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract prototype for all effects. 
 * Facade for all CoreEffects parameters and functions (See CoreEffects in scriptaculous documentation for details)
 */
public abstract class Effect {
	
	public static enum Transistion {sinoidal,linear,reverse,wobble,flicker,pulse,spring,none,full};
	public static enum ScaleMode {box,contents};
	
	private Element element;
	private Widget widget;
	protected JavaScriptObject effect;
	private JavaScriptObject options;
	private double duration = 1.0;
	private int fps = 25;
	private Transistion transition = Transistion.sinoidal;
	private double from = 0.0;
	private double to = 1.0;
	private boolean sync;
	private EffectQueue queue;
	private double delay = 0.0;
	 
	private AfterFinishHandler afterFinishhandler; 
	private AfterSetupHandler afterSetupHandler;
	private AfterUpdateHandler afterUpdateHandler;
	private BeforeSetupHandler beforeSetupHandler;
	private BeforeStartHandler beforeStartHandler;
	private BeforeUpdateHandler beforeUpdateHandler;
	private ScheduledFinishHandler scheduledFinishHandler;
	
	private Timer scheduledEffectTimer;
	
	
	/**
	 * This constructor is available only for Effect Parallel purpose, use Effect(Widget widget) to get full access
	 * to effect options and info
	 */
	protected Effect() {
		this.options = JavaScriptObject.createObject();
	}
	
	public Effect(Widget widget){
		this();
		this.element = widget.getElement();
		this.widget = widget;
	}
	
	/**
	 * Start the effect animation by creating actual js Effect object 
	 */
	public void initialize() {
		this.effect = initEffect();
		
		if (scheduledEffectTimer!=null){
			double scheduledTimeMs = (delay*1000) + (duration*1000);
			scheduledEffectTimer.schedule((int)scheduledTimeMs);
		}
	}
	
	/**
	 * Initialize js effect object. You should override this method if your effect is initialized other way than:
	 * $wnd.Effect[effectName](element, options)
	 */
	protected JavaScriptObject initEffect() {
		return EffectHelper.init(getEffectName(), getElement(), getOptions());
	}
	
	/**
	 * Returns effect name according to effect full class name (with package name). 
	 * If class name doesn't match "Effect[Name]" pattern you should override this method
	 */
	protected String getEffectName() {
		int lastDotPos = getClass().getName().lastIndexOf(".");
		String className = getClass().getName().substring(lastDotPos+1);
		return className.replaceFirst("Effect", "");
	}
	
	/**
	 * Stop the effect as is. 
	 */
	public void cancel() {
		if (effect != null) cancel(effect);
	}
	
	/**
	 * Gets basic debugging information about the native effect instance.
	 * Information is displayed in standard alert box.
	 */
	public void inspect() {
		if (effect != null) inspect(effect);
	}

	/**
	 * Duration of the effect in seconds, given as a float. Defaults to 1.0. 
	 */
	public void setDuration(double duration) {
		this.duration = duration;
		EffectHelper.setOption(options, "duration", duration);
	}
	/**
	 * Gets duration of the effect in seconds
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Target this many frames per second. Default to 25. Can’t be higher than 100. 
	 */
	public void setFps(int fps) {
		this.fps = fps;
		EffectHelper.setOption(options, "fps", fps);
	}
	/**
	 * Gets effect targeted frames per second.
	 */
	public int getFps() {
		return fps;
	}
	
	/**
	 * Sets the starting point of the transition, a float between 0.0  and 1.0. Defaults to 0.0. 
	 */
	public void setFrom(double from) {
		this.from = from;
		EffectHelper.setOption(options, "from", from);
	}
	/**
	 * Gets the starting point of the transition, a float between 0.0  and 1.0.
	 */
	public double getFrom() {
		return from;
	}
	
	/**
	 * Sets the end point of the transition, a float between 0.0 and 1.0. Defaults to 1.0. 
	 */
	public void setTo(double to) {
		this.to = to;
		EffectHelper.setOption(options, "to", to);
	}	
	/**
	 * Gets the end point of the transition, a float between 0.0 and 1.0.
	 */
	public double getTo() {
		return to;
	}

	/**
	 * Sets whether the effect should render new frames automatically (which it does by default).
	 */
	public void setSync(boolean sync) {
		this.sync = sync;
		EffectHelper.setOption(options, "sync", sync);
	}
	/**
	 * Gets whether the effect render new frames automatically. 
	 */
	public boolean getSync() {
		return sync;
	}
	
	/**
	 * Sets the number of seconds to wait before the effect actually starts. Defaults to 0.0.
	 */
	public void setDelay(double delay) {
		this.delay = delay;
		EffectHelper.setOption(options, "delay", delay);
	}
	/**
	 * Gets the number of seconds before the effect actually starts.
	 */
	public double getDelay() {
		return delay;
	}
	
	/**
	 *  Sets a function that modifies the current point of the animation, which is between 0 and 1. Effect.Transitions.sinoidal is set by default, 
	 */
	public void setTransition(Transistion transition) {
		this.transition = transition;
		EffectHelper.setOption(options, "transition", "Effect.Transitions." + transition);
	}
	/**
	 * Gets a function that modifies the current point of the animation
	 */
	public Transistion getTransition() {
		return transition;
	}

	/**
	 * Sets queuing options. See EffectQueue for details
	 */
	public void setQueue(EffectQueue queue) {
		this.queue = queue;
		EffectHelper.setOption(options, "queue", queue.getQueue());
	}
	/**
	 * Gets queuing options. See EffectQueue for details
	 */
	public EffectQueue getQueue() {
		return queue;
	}
	
	/**
	 * Gets the native DOM element the effect is applied to. 
	 */
	public Element getElement(){
		return element;
	}
	
	/**
	 * Gets the Widget the effect is applied to.
	 */
	public Widget getWidget() {
		return widget;
	}
	
	/**
	 * Gets native java script object that represents set of effect options.
	 */
	public JavaScriptObject getOptions(){
		return options;
	}
	/**
	 * Sets native java script effect options object.
	 */
	public void setOptions(JavaScriptObject options){
		this.options = options;
	}
	/**
	 * Sets effect options object according to EffectOption[] array (pairs of names and values)
	 */
	public void setOptions(EffectOption[] effectOptions){
		EffectHelper.copyOptions(this.options, effectOptions);
	}

	protected void onAfterFinish(){
		if (afterFinishhandler != null) {
			afterFinishhandler.onAfterFinish(this);
		}
	}
	protected void onAfterSetup(){
		if (afterSetupHandler != null) {
			afterSetupHandler.onAfterSetup(this);
		}
	}
	protected void onAfterUpdate(){
		if (afterUpdateHandler != null) {
			afterUpdateHandler.onAfterUpdate(this);
		}
	}
	protected void onBeforeSetup(){
		if (beforeSetupHandler != null) {
			beforeSetupHandler.onBeforeSetup(this);
		}
	}
	protected void onBeforeStart(){
		if (beforeStartHandler != null) {
			beforeStartHandler.onBeforeStart(this);
		}
	}
	protected void onBeforeUpdate(){
		if (beforeUpdateHandler != null) {
			beforeUpdateHandler.onBeforeUpdate(this);
		}
	}
	protected void onScheduledFinish(){
		if (scheduledFinishHandler != null) {
			scheduledFinishHandler.onScheduledFinish(this);
		}
	}

	/**
	 * Adds a AfterFinish event handler. 
	 */
	public void addAfterFinishHandler(AfterFinishHandler handler){
		this.afterFinishhandler = handler;
		EffectHelper.registerNativeAfterFinish(getOptions(),this);
	}
	/**
	 * Adds a AfterSetup event handler. 
	 */
	public void addAfterSetupHandler(AfterSetupHandler handler){
		this.afterSetupHandler = handler;
		EffectHelper.registerNativeAfterSetup(getOptions(),this);
	}
	/**
	 * Adds a AfterUpdate event handler. 
	 */
	public void addAfterUpdateHandler(AfterUpdateHandler handler){
		this.afterUpdateHandler = handler;
		EffectHelper.registerNativeAfterUpdate(getOptions(),this);
	}
	/**
	 * Adds a BeforeSetup event handler. 
	 */
	public void addBeforeSetupHandler(BeforeSetupHandler handler){
		this.beforeSetupHandler = handler;
		EffectHelper.registerNativeBeforeSetup(getOptions(),this);
	}
	/**
	 * Adds a BeforeStart event handler. 
	 */
	public void addBeforeStartHandler(BeforeStartHandler handler){
		this.beforeStartHandler = handler;
		EffectHelper.registerNativeBeforeStart(getOptions(),this);
	}
	/**
	 * Adds a BeforeUpdate event handler. 
	 */
	public void addBeforeUpdateHandler(BeforeUpdateHandler handler){
		this.beforeUpdateHandler = handler;
		EffectHelper.registerNativeBeforeUpdate(getOptions(),this);
	}
	/**
	 * Adds a ScheduledFinish event handler. 
	 */
	public void addScheduledFinishHandler(ScheduledFinishHandler handler){
		this.scheduledFinishHandler = handler;
		this.scheduledEffectTimer = new Timer() {
			public void run() {
				onScheduledFinish();
			}
		};
	}
	
	private native void cancel (JavaScriptObject effect) /*-{
		effect.cancel();
	}-*/;
	
	private native void inspect (JavaScriptObject effect) /*-{
		effect.inspect();
	}-*/;

}
