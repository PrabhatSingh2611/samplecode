package digital.windmill.audra.graphql.type.assetTypeCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatchAssetTypeCategoryInput {

    private UUID id;
    private String name;

}
