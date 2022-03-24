package digital.windmill.audra.graphql.type.input;

import lombok.Data;

@Data
public class PageInput {
    private Integer currentPage;
    private Integer totalPages;
}
