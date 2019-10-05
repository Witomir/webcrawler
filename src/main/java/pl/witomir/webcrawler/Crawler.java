package pl.witomir.webcrawler;

import com.beust.jcommander.JCommander;

public class Crawler {

    public Object generateSiteMap(String[] args) {
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(args);

        return args;
    }
}
