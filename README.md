# gwtaculous

## Introduction

GWTaculous is visual effects library for application created in or based on GWT that has to be compatible with older browsers (example: IE8). It also give you access to simple drag & drop API that can be used with GWT 2.5+ applications.

Its visual effect module is based on other open source library - scriptaculous which is javascript solution for visual effects and html object manipulations. Currently most of those effects are easy to implement in CSS3 compatible browsers so if you have no browser restriction we recommend to use our wrapper only for older browsers.

At the first glance GWTaculous is a java wrapper for javascript functions (JSNI) in scriptaculous library. But in fact it is complete solution with full java API (visual effects, effect queues, animation GWT event handlers etc). Let alone documentation and use examples. It also contains some minor bug fixes and will be updated with new visual effects. You don't even have to know javascript to use it. Bring life to your user interface with a single line of code !

Drag ad drop capabilities are based on GWT Event.NativePreviewHandler and can be used with all GWT supported browsers.

## License

As all our top.touk.pl projects its based on ASF License 2.0 :
```
/*
 * Copyright (c) 2008-2010 TouK.pl
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
```

## Requirments

If you are useing maven use this dependency:
```
<dependency>
    <groupId>pl.touk.gwtaculous</groupId>
    <artifactId>gwtaculous-lib</artifactId>
    <version>[check for latest library version]</version>
</dependency>
```

You will have to include GWTaculous module in your application.gwt.xml file (file where i.e. your entry point is declared - read more here ):
```
<module>
    .
    .
    <inherits name="pl.touk.Gwtaculous"/>
    .
    .
</module>
```

Yeep. That's it ! This one line will also automatically link and include all necessary javascrpts into "/js" folder in the root of your application.

**UPDATE**: If you are using GWT 1.7+ you will have to additionally include scriptaculous.js library in your hosting page (for visual effects module only).

## Example usage (visual effects)

First of all this library comes with complete example GWT application. You can build (mvn clean install) and deploy it on any application server. Check repository for latest examples: gwtaculous-example (module).

If you successfully included GWTaculous library into your GWT project launching visual effect on your Widget is simple as this:
Effects.fade(some_widget);

This will immediately start fading your widget till it completely disappear (default time of effect is set to 0.5s) Use of "Effects" facade is plain and simple but it gives you only basic access to effect properties and you will have to pass them directly by name and value String pair:
```
Effects.blindDown(some_widget, new EffectOption("duration", 4.0), new EffectOption("delay", 1.0));
```

It is not very convenient if you think about complex effects or you don't know scriptaculous effect API. To solve that problem use dedicated Effect objects to create desired animation:
```
EffectScale effect = new EffectScale(some_widget, 100);
effect.setScaleFrom(0);
effect.setScaleX(false);
effect.setScaleContent(false);
effect.setDuration(5.0);
effect.initialize();
```

This approach won't lunch effect until you call initialize() function. Until that moment you can change it configuration, add event handlers, set desired effect queues or group effects into parallel animations.
```
EffectMove effectMove = new EffectMove(some_widget, 400, 0);
EffectOpacity effectOpacity = new EffectOpacity(some_widget);
effectOpacity.setFrom(1);
effectOpacity.setTo(0);
 
EffectParallel effectParallel = new EffectParallel();
effectParallel.addEffect(effectMove);
effectParallel.addEffect(effectOpacity);
effectParallel.setDuration(1.5);
effectParallel.setDelay(1.5);
effectParallel.initialize();
```
