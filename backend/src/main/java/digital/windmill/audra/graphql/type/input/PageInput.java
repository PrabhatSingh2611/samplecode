package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInput {

    public static final int MAX_ITEMS_PER_PAGE = 100;
    public static final int DEFAULT_ITEMS_PER_PAGE = 10;
    public static final PageRequest DEFAULT_PAGINATION = PageRequest.of(0, DEFAULT_ITEMS_PER_PAGE);

    private Integer itemsPerPage;
    private Integer pageNumber;

    public Integer getItemsPerPage() {
        if (itemsPerPage == null
                || itemsPerPage <= 0
                || itemsPerPage > MAX_ITEMS_PER_PAGE) {
            return MAX_ITEMS_PER_PAGE;
        }
        return itemsPerPage;
    }

    public Integer getPageNumber() {
        return pageNumber == null || pageNumber.intValue() <= 0 ? 0
                : (pageNumber.intValue() - 1);
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(getPageNumber(), getItemsPerPage());
    }
}
