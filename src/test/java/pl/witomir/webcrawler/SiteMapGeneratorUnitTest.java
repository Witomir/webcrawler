package pl.witomir.webcrawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.console.Options;
import pl.witomir.webcrawler.console.ArgumentsParser;
import pl.witomir.webcrawler.crawler.Crawler;
import pl.witomir.webcrawler.domain.Site;
import pl.witomir.webcrawler.renderer.ConsoleRenderer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SiteMapGeneratorUnitTest {

    @Mock
    private ArgumentsParser argumentsParser;

    @Mock
    private ConsoleRenderer consoleRenderer;

    @Mock
    private Crawler crawler;

    @Mock
    private Options options;

    @InjectMocks
    private SiteMapGenerator siteMapGenerator;

    @Test
    void generateSiteMap() {
        String startingUrl = "test args";
        Site siteMap = new Site();
        var args = new String[]{startingUrl};

        when(argumentsParser.parseArguments(args)).thenReturn(options);
        when(options.getStartingUrl()).thenReturn(startingUrl);
        when(crawler.generateSiteMapStartingOn(startingUrl)).thenReturn(siteMap);

        siteMapGenerator.generateSiteMap(args);

        verify(consoleRenderer).renderResults(siteMap);
    }
}