package pl.touk.gwtaculous.core;

import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.ScriptInjector;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GwtaculousCoreEntryPoint implements com.google.gwt.core.client.EntryPoint {

    private static final Logger log = Logger.getLogger(GwtaculousCoreEntryPoint.class.getSimpleName());

    @Override
    public void onModuleLoad() {
        log.log(Level.INFO, "Gwtaculous depended script initialization started...");
        ScriptInjector.fromUrl("js/prototype.js").setCallback(new Callback<Void, Exception>() {
            @Override
            public void onFailure(Exception e) {
                log.log(Level.SEVERE, "Gwtaculous depended script initialization failed! (prototype.js)", e);
            }

            @Override
            public void onSuccess(Void aVoid) {
                log.log(Level.INFO, "Gwtaculous depended script initialization complete (prototype.js)");
                ScriptInjector.fromUrl("js/effects.js").setCallback(new Callback<Void, Exception>() {
                    @Override
                    public void onFailure(Exception e) {
                        log.log(Level.SEVERE, "Gwtaculous depended script initialization failed! (effects.js)", e);
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        log.log(Level.INFO, "Gwtaculous depended script initialization complete (effects.js)");
                    }
                }).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(false).inject();
            }
        }).setWindow(ScriptInjector.TOP_WINDOW).setRemoveTag(false).inject();

    }

}
