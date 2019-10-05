package pl.witomir.webcrawler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;

@ExtendWith(MockitoExtension.class)
class CrawlerTest {

    @InjectMocks
    private Crawler crawler;

    @Test
    void generateSiteMap() {
        var crawler = new Crawler();
        crawler.generateSiteMap(new String[]{"-starting-url", "http://wiprodigital.com"});
    }
}