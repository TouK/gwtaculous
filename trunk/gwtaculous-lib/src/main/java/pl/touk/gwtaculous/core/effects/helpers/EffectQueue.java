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

package pl.touk.gwtaculous.core.effects.helpers;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Represent effect queue that can be used as an option during setting of an effect. See scriptaculous documentation for details
 */
public class EffectQueue {
	
	public enum QueuePosition{front,end};
	
	private QueuePosition position;
	private String scope;
	private int limit;
	
	private JavaScriptObject queue;
	
	public EffectQueue() {
		queue = JavaScriptObject.createObject();
		setPosition(QueuePosition.end);
	}		

	public QueuePosition getPosition() {
		return position;
	}
	public void setPosition(QueuePosition position) {
		this.position = position;
		EffectHelper.setOption(queue, "position", position.toString());
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
		EffectHelper.setOption(queue, "scope", scope);
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
		EffectHelper.setOption(queue, "limit", limit);
	}
	public JavaScriptObject getQueue(){
		return queue;
	}
	
	
	
	
}
