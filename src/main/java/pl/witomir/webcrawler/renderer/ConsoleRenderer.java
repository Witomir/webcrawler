package pl.witomir.webcrawler.renderer;

import com.google.gson.Gson;
import com.google.inject.Inject;
import pl.witomir.webcrawler.domain.Site;

public class ConsoleRenderer {

    private Gson gson;

    @Inject
    public ConsoleRenderer(Gson gson) {
        this.gson = gson;
    }

    public void renderResults (Site site) {
        System.out.println(gson.toJson(site));
    }
}
