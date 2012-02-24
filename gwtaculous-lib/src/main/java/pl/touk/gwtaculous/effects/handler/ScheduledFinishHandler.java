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
package pl.touk.gwtaculous.effects.handler;

import pl.touk.gwtaculous.effects.effect.Effect;

/**
 * Handler for ScheduledFinish event. 
 * This event occurs in time moment when effect supposed to be finished
 */
public interface ScheduledFinishHandler {
	
	/**
	 * Called X seconds after effect has started. Where X is sum of delay and duration of the effect 
	 */
	void onScheduledFinish(Effect effect);
}
