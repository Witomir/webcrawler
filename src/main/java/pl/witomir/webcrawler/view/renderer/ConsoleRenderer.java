package pl.witomir.webcrawler.view.renderer;

import com.google.gson.Gson;
import com.google.inject.Inject;
import pl.witomir.webcrawler.view.ViewModel;

public class ConsoleRenderer {

    private Gson gson;

    @Inject
    public ConsoleRenderer(Gson gson) {
        this.gson = gson;
    }

    public void renderResults (ViewModel viewModel) {
        System.out.println("Internal links:");
        System.out.println(gson.toJson(viewModel.getInternalPages()));
        System.out.println("External links:");
        System.out.println(gson.toJson(viewModel.getExternalPages()));
        System.out.println("Static content links:");
        System.out.println(gson.toJson(viewModel.getStaticContent()));
    }
}
