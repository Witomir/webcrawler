package pl.witomir.webcrawler.crawler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.witomir.webcrawler.domain.Page;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CrawlerTest {

    @InjectMocks
    Crawler crawler;

    @Mock
    private PageRepository pageRepository;

    @Mock
    private UrlUtil urlUtil;

    private Page page1;
    private Page page2;
    private Page page3;
    private Set<String> firstPageInternalLinks;
    private Set<String> secondPageInternalLinks;

    @BeforeEach
    void setUp() {
        page1 = new Page();
        page1.setUrl(Fixtures.START_URL);
        firstPageInternalLinks = Set.of(Fixtures.FIRST_INTERNAL_LINK);
        page1.setInternalLinks(firstPageInternalLinks);

        page2 = new Page();
        page2.setUrl(Fixtures.SECOND_PAGE_URL);
        secondPageInternalLinks = Set.of(Fixtures.SECOND_INTERNAL_LINK);
        page2.setInternalLinks(secondPageInternalLinks);

        page3 = new Page();
        page3.setUrl(Fixtures.THIRD_PAGE_URL);
        page3.setInternalLinks(Set.of(Fixtures.THIRD_INTERNAL_LINK));

    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void getAllPagesOnTheSameDomainStartingOn() {

        when(pageRepository.fetchPage(Fixtures.START_URL)).thenReturn(page1);
        when(urlUtil.removeVisitedLinks(firstPageInternalLinks, Set.of(Fixtures.START_URL))).thenReturn(Set.of(Fixtures.SECOND_PAGE_URL));

        Set<String> allDiscoveredPagesAfterFirstIteration = Set.of(Fixtures.START_URL, Fixtures.SECOND_PAGE_URL);
        when(pageRepository.fetchPage(Fixtures.SECOND_PAGE_URL)).thenReturn(page2);
        when(urlUtil.removeVisitedLinks(secondPageInternalLinks, allDiscoveredPagesAfterFirstIteration)).thenReturn(Set.of(Fixtures.THIRD_PAGE_URL));

        Set<String> allDiscoveredPagesAfterSecondIteration = Set.of(Fixtures.START_URL, Fixtures.SECOND_PAGE_URL, Fixtures.THIRD_PAGE_URL);
        when(pageRepository.fetchPage(Fixtures.THIRD_PAGE_URL)).thenReturn(page3);

        Set<Page> visitedPages = crawler.getAllPagesOnTheSameDomainStartingOn(Fixtures.START_URL);

        assertEquals(Set.of(page1, page2, page3), visitedPages);
        verify(urlUtil).removeVisitedLinks(secondPageInternalLinks, allDiscoveredPagesAfterSecondIteration); //because Mockito is in lenient mode, we have to verify it
    }

    private static class Fixtures {
        private static final String START_URL = "start-url";
        private static final String THIRD_PAGE_URL = "third-url";
        private static final String SECOND_PAGE_URL = "second-url";
        private static final String FIRST_INTERNAL_LINK = "one";
        private static final String SECOND_INTERNAL_LINK = "two";
        private static final String THIRD_INTERNAL_LINK = "three";
    }
}