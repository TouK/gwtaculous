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

/**
 * Represent simple single option of an effect. Pair of strings (key and value), 
 * which is usually used in EffectOption[] array to configure effect object.
 */
public class EffectOption
{
    private String name;
    private String value;

    
    public EffectOption (String name, String value) {
        this.name = name;
        this.value = value;
    }

    public EffectOption (String name, double value) {
        this.name = name;
        this.value = Double.toString(value);
    }
    
    public EffectOption (String name, int value) {
        this.name = name;
        this.value = Integer.toString(value);
    }
    
    public EffectOption (String name, boolean value) {
        this.name = name;
        this.value = Boolean.toString(value);
    }
    
    public String getName () {
        return name;
    }

    public String getValue () {
        return value;
    }

}
