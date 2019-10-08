package pl.witomir.webcrawler.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.witomir.webcrawler.domain.Page;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ViewModelBuilderTest {

    @InjectMocks
    private ViewModelBuilder viewModelBuilder;

    @Test
    void build() {
        Set<String> internal1 = Set.of("internal_1");
        Set<String> external1 = Set.of("external_1");
        Set<String> staticPages1 = Set.of("static_1");

        Set<String> internal2 = Set.of("internal_2");
        Set<String> external2 = Set.of("external_2");
        Set<String> staticPages2 = Set.of("static_2");

        Page page1 = new Page();
        page1.setInternalLinks(internal1);
        page1.setExternalLinks(external1);
        page1.setStaticContentLinks(staticPages1);

        Page page2 = new Page();
        page2.setInternalLinks(internal2);
        page2.setExternalLinks(external2);
        page2.setStaticContentLinks(staticPages2);

        ViewModel viewModel = viewModelBuilder.build(Set.of(page1, page2));

        var expectedInternal = new TreeSet<>();
        expectedInternal.add("internal_2");
        expectedInternal.add("internal_1");

        var expectedExternal = new TreeSet<>();
        expectedExternal.add("external_2");
        expectedExternal.add("external_1");

        var expectedStatic = new TreeSet<>();
        expectedStatic.add("static_2");
        expectedStatic.add("static_1");

        assertEquals(expectedInternal, viewModel.getInternalPages());
        assertEquals(expectedExternal, viewModel.getExternalPages());
        assertEquals(expectedStatic, viewModel.getStaticContent());
    }
}