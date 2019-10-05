package pl.witomir.webcrawler.console;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ArgumentsParserTest {

    private ArgumentsParser parser;

    @BeforeEach
    public void setUp() {
        parser = new ArgumentsParser(JCommander.newBuilder());
    }

    @ParameterizedTest
    @ValueSource(strings = {"test", "google", "http://example.com"})
    void parseArgumentsCorrectlyParsesFileds(String startingUrl) {
        String[] args = {"-starting-url", startingUrl};

        Arguments arguments = parser.parseArguments(args);

        assertEquals(startingUrl, arguments.getStartingUrl());
    }

    @Test
    public void throwsExceptionWhenRanWithoutParameter() {
        assertThrows(ParameterException.class, () -> parser.parseArguments(new String[]{}));
    }
}