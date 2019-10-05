package pl.witomir.webcrawler.console;

import com.beust.jcommander.JCommander;
import com.google.inject.Inject;

public class ArgumentsParser {

    private JCommander.Builder builder;

    @Inject
    public ArgumentsParser(JCommander.Builder builder) {
        this.builder = builder;
    }

    public Arguments parseArguments(String[] args) {
        Arguments arguments = new Arguments();

        builder
                .addObject(arguments)
                .build()
                .parse(args);

        return arguments;
    }
}
