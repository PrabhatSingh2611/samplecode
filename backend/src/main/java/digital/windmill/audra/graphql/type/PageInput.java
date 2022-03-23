package digital.windmill.audra.graphql.type;

import lombok.Data;

@Data
public class PageInput {
    private Integer currentPage;
    private Integer totalPages;
}
