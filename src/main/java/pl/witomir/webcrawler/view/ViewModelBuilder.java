package pl.witomir.webcrawler.view;

import pl.witomir.webcrawler.domain.Page;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ViewModelBuilder {
    public ViewModel build(Set<Page> pages) {
        var viewModel = new ViewModel();
        viewModel.setInternalPages(getAllInternalPages(pages));
        viewModel.setExternalPages(getAllExternalPages(pages));
        viewModel.setStaticContent(getAllStaticContentLinks(pages));

        return viewModel;
    }

    private TreeSet<String> getAllInternalPages(Set<Page> pages) {
        var internalLinks = new TreeSet<String>();

        for(Page page : pages) {
            if(Objects.nonNull(page.getInternalLinks())){
                internalLinks.addAll(page.getInternalLinks());
            }
        }

        return internalLinks;
    }

    private TreeSet<String> getAllExternalPages(Set<Page> pages) {
        var externalPages = new TreeSet<String>();

        for(Page page : pages) {
            if(Objects.nonNull(page.getExternalLinks())){
                externalPages.addAll(page.getExternalLinks());
            }
        }

        return externalPages;
    }

    private TreeSet<String> getAllStaticContentLinks(Set<Page> pages) {
        var staticContent = new TreeSet<String>();

        for(Page page : pages) {
            if(Objects.nonNull(page.getExternalLinks())){
                staticContent.addAll(page.getStaticContentLinks());
            }
        }

        return staticContent;
    }

}
