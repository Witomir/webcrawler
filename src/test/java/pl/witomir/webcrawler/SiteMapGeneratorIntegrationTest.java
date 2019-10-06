package pl.witomir.webcrawler;

import com.google.common.io.Resources;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.util.Modules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteMapGeneratorIntegrationTest {

    @Inject
    private SiteMapGenerator siteMapGenerator;

    @Mock
    private RestTemplate restTemplate;

    private String startPage;
    private String about;
    private String contact;
    private String search;

    private class TestModule implements Module {
        @Override
        public void configure(Binder binder) {
            binder.bind(RestTemplate.class).toInstance(restTemplate);
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        startPage = Resources.toString(Resources.getResource("responses/start_page.html"), Charset.defaultCharset());
        about = Resources.toString(Resources.getResource("responses/about.html"), Charset.defaultCharset());
        contact = Resources.toString(Resources.getResource("responses/contact.html"), Charset.defaultCharset());
        search = Resources.toString(Resources.getResource("responses/search.html"), Charset.defaultCharset());

        Guice.createInjector(
                Modules
                        .override(new DiModule())
                        .with(new TestModule())
        ).injectMembers(this);
    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void generateSiteMap() {
        String startingUrl = "http://www.example.com";
        var args = new String[]{"-starting-url", startingUrl};

        when(restTemplate.getForObject(anyString(), eq(String.class))).thenReturn("<html></html>");
        when(restTemplate.getForObject(Fixtures.START_PAGE, String.class)).thenReturn(startPage);
        when(restTemplate.getForObject(Fixtures.ABOUT_PAGE, String.class)).thenReturn(about);
        when(restTemplate.getForObject(Fixtures.SEARCH_PAGE, String.class)).thenReturn(search);
        when(restTemplate.getForObject(Fixtures.CONTACT_PAGE, String.class)).thenReturn(contact);

        siteMapGenerator.generateSiteMap(args);
    }

    private static class Fixtures {
        private static final String START_PAGE = "http://www.example.com";
        private static final String ABOUT_PAGE = "http://www.example.com/about";
        private static final String SEARCH_PAGE = "http://www.example.com/search.html";
        private static final String CONTACT_PAGE = "http://www.example.com/contact";
    }
}