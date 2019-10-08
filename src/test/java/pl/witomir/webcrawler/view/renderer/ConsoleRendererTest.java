package pl.witomir.webcrawler.view.renderer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.view.ViewModel;

import java.util.Set;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleRendererTest {

    @InjectMocks
    ConsoleRenderer consoleRenderer;

    @Mock
    private Gson gson;

    @Test
    void renderResults() {
        var viewModel = new ViewModel();
        Set<String> internal = Set.of("internal");
        Set<String> external = Set.of("external");
        Set<String> staticPages = Set.of("static");
        viewModel.setInternalPages(internal);
        viewModel.setExternalPages(external);
        viewModel.setStaticContent(staticPages);

        consoleRenderer.renderResults(viewModel);

        verify(gson).toJson(internal);
        verify(gson).toJson(external);
        verify(gson).toJson(staticPages);
    }
}