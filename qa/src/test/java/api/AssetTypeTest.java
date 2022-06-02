package api;

import api.pojo.QueryResponse;
import utils.XLUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.enums.Queries;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class AssetTypeTest extends BaseApiTest {
	String xlsFile = config.getTestDataDirectory()+"AssetTypeData.xlsx";
	@Test
	void listOfAssetTypes() throws Exception {

		QueryResponse response = sendRequest(Queries.ASSET_TYPES, queryVariables);

		Map<String, Map<String, String>> excelData = XLUtils.getListData(xlsFile, "AssetTypes");

		// Remove comments once QA environment is available
		/*
		 * Assert.assertEquals(response.getData().getAssetTypes().getTotalItems(), excelData.get(String.valueOf(1)).get("totalItems").trim());
		 * Assert.assertEquals(response.getData().getAssetTypes().getPageInfo(). getTotalPages(),excelData.get(String.valueOf(1)).get("totalPages").trim());
		 * Assert.assertEquals(response.getData().getAssetTypes().getPageInfo(). getCurrentPage(),excelData.get(String.valueOf(1)).get("currentPage").trim());
		 */

		for (int i = 1; i <= excelData.size(); i++) {
			Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i - 1).getId().trim(),excelData.get(String.valueOf(i)).get("id").trim());
			Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i - 1).getTitle().trim(),	excelData.get(String.valueOf(i)).get("title").trim());
			Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i - 1).getIcon().trim(),excelData.get(String.valueOf(i)).get("icon").trim());
		}
	}

	@Test(dataProvider = "AssetType")
	void detailsOfAssetType(Map<Object, Object> data) throws IOException {
		queryVariables.setId(data.get("id").toString().trim());

		QueryResponse response = sendRequest(Queries.ASSET_TYPE, queryVariables);

		Assert.assertEquals(response.getData().getAssetType().getItem().getId(), data.get("id").toString().trim());
		Assert.assertEquals(response.getData().getAssetType().getItem().getTitle(),	data.get("title").toString().trim());
		Assert.assertEquals(Objects.toString(response.getData().getAssetType().getItem().getIcon(), "").trim(),	data.get("icon").toString().trim());
	}

	@Test(dataProvider = "AssetType")
	void addAssetType(Map<Object, Object> data) throws Exception, IOException {
		queryVariables.setTitle(data.get("title").toString().trim());

		QueryResponse response = sendRequest(Queries.ADD_ASSET_TYPE, queryVariables);
		
		if (!(response.getData().getCreateAssetType().getItem().getId().trim().length() > 0)) {
			assertEquals(false, true);
		}
		Assert.assertEquals(response.getData().getCreateAssetType().getItem().getTitle().trim(),data.get("title").toString().trim());
		Assert.assertEquals(Objects.toString(response.getData().getCreateAssetType().getItem().getIcon(), "").trim(),data.get("icon").toString().trim());
	}

	@DataProvider(name = "AssetType")
	public Object[][] assetTypeDataProvider(Method m) throws Exception {
		
		switch (m.getName()) {
		case "addAssetType":
			return XLUtils.getExcelSheetData(xlsFile, "AddAssetType");
		case "detailsOfAssetType":
			return XLUtils.getExcelSheetData(xlsFile, "AssetType");
		default:
			throw new Exception("No data provider found for test case "+m.getName());
		}
	}
}
