package pl.witomir.webcrawler;

import com.beust.jcommander.JCommander;
import com.google.inject.Binder;
import com.google.inject.Module;

public class DiModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(JCommander.Builder.class).toInstance(JCommander.newBuilder());
    }
}
