package digital.windmill.audra.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageInput extends digital.windmill.audra.graphql.type.input.PageInput {

    private Integer itemsPerPage;
    private Integer pageNumber;

    

}
