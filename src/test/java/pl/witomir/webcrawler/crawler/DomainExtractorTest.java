package pl.witomir.webcrawler.crawler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class DomainExtractorTest {

    @InjectMocks
    DomainExtractor domainExtractor;


    @ParameterizedTest
    @MethodSource("domainNameDataProvider")
    void testExtraction(String url, String expectedDomain) {
        assertEquals(expectedDomain, domainExtractor.getDomainName(url));
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
}