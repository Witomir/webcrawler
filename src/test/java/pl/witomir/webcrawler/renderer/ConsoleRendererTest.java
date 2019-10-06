package pl.witomir.webcrawler.renderer;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.domain.SiteMap;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ConsoleRendererTest {

    @InjectMocks
    ConsoleRenderer consoleRenderer;

    @Mock
    private Gson gson;

    @Test
    void renderResults() {
        consoleRenderer.renderResults(new SiteMap());
        verify(gson).toJson(new SiteMap());
    }
}