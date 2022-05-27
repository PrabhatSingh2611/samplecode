package digital.windmill.audra.graphql.type.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInput {

    public static final int MAX_ITEMS_PER_PAGE = 100;
    public static final PageRequest DEFAULT_PAGINATION = PageRequest.of(0, MAX_ITEMS_PER_PAGE);

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

    public int getPageNumber() {
        return pageNumber == null ? 0
                : pageNumber.intValue();
    }

    public PageRequest toPageRequest() {
        return PageRequest.of(getPageNumber(), getItemsPerPage());
    }
}
