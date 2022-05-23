package api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetType {
	private PageInfo pageInfo;
	private int totalItems;
	private List<AssetTypeItems> items;
	private AssetTypeItems item;

}
