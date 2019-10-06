package pl.witomir.webcrawler.console;

import com.beust.jcommander.JCommander;
import com.google.inject.Inject;

public class ArgumentsParser {

    private JCommander.Builder builder;

    @Inject
    public ArgumentsParser(JCommander.Builder builder) {
        this.builder = builder;
    }

    public Options parseArguments(String[] args) {
        Options options = new Options();

        builder
                .addObject(options)
                .build()
                .parse(args);

        return options;
    }
}
