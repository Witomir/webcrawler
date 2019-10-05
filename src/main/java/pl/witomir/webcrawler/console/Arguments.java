package pl.witomir.webcrawler.console;

import com.beust.jcommander.Parameter;

public class Arguments {

    @Parameter(names = "-starting-url", description = "Starting URL")
    private String startingUrl;
}
