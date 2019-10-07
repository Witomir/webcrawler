package pl.witomir.webcrawler.crawler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.domain.WrongDomainException;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UrlUtilTest {

    @InjectMocks
    UrlUtil urlUtil;

    @ParameterizedTest
    @MethodSource("domainNameDataProvider")
    void testExtraction(String url, String expectedDomain) {
        assertEquals(expectedDomain, urlUtil.getDomainName(url));
    }

    @ParameterizedTest
    @MethodSource("anchorTestDataProvider")
    void anchorRemovalTest(String url, String expectedUrl) {
        assertEquals(expectedUrl, urlUtil.removeAnchorFromLink(url));
    }

    @Test
    void malformedLinkThrowsWrongDomainException() {
        assertThrows(WrongDomainException.class, () -> urlUtil.getDomainName("non url"));
    }

    @Test
    void testLinksFiltering() {
        var visitedLinks = Set.of("1", "2", "3");
        var linksInDomain = Set.of("2", "3", "4", "5");

        assertEquals(Set.of("4", "5"), urlUtil.removeVisitedLinks(linksInDomain, visitedLinks));
    }

    private static Stream<Arguments> domainNameDataProvider() {
        return Stream.of(
                Arguments.of("http://wiprodigital.com", "wiprodigital.com"),
                Arguments.of("http://www.wiprodigital.com", "wiprodigital.com"),
                Arguments.of("http://www.google.pl", "google.pl"),
                Arguments.of("http://google.com/search/qqqaaa?query=qwerty", "google.com"),
                Arguments.of("http://example.pl/", "example.pl")
        );
    }

    private static Stream<Arguments> anchorTestDataProvider() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of("", ""),
                Arguments.of("http://a.com", "http://a.com"),
                Arguments.of("http://a.com/path/site#anchor", "http://a.com/path/site"),
                Arguments.of("http://a.com/path/site#something-long-idk-over-9000", "http://a.com/path/site"),
                Arguments.of("http://hash-in-#-the-middle.com/its/a/simple/implementation", "http://hash-in-")
        );
    }
}