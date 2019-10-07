package pl.witomir.webcrawler;

import com.beust.jcommander.ParameterException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class MainTest {

    @Test
    public void applicationContextLoadsAndThrowsExceptionAboutWrongParameters() {
        assertThrows(ParameterException.class, () -> Main.main(new String[]{}));
    }

    @Test
    @Disabled
    public void applicationContextLoads() {
        Main.main(new String[]{"-starting-url", "https://wiprodigital.com/"});
    }
}