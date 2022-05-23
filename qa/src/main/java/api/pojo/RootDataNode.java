package api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RootDataNode {
	private AssetType assetType;
	private AssetType assetTypes;
	private AssetType createAssetType;
	private Location locations;
	private Location location;
	private Location createLocation;
}
