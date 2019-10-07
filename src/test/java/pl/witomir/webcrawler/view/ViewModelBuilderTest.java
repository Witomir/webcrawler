package pl.witomir.webcrawler.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import pl.witomir.webcrawler.domain.Page;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ViewModelBuilderTest {

    @InjectMocks
    private ViewModelBuilder viewModelBuilder;

    @Test
    void build() {
        viewModelBuilder.build(Set.of(new Page()));
    }
}