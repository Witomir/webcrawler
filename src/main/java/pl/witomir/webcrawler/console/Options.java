package pl.witomir.webcrawler.console;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class Options {

    @Parameter(required = true, names = "-starting-url", description = "Starting URL")
    private String startingUrl;
}
