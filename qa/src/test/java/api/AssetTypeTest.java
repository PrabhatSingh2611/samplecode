package api;

import api.pojo.QueryResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import api.enums.Queries;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class AssetTypeTest extends BaseApiTest {

    @DataProvider(name = "DetailsOfAssetType")
    public Object[][] DetailsOfAssetType() {
        return new Object[][]{
                {"Laptops", "https://google.com/laptops", "c0ba7c5f-b683-40c0-88cb-5a51912703cb"},
                {"Monitors", "https://google.com/monitors", "6bd7fd52-1eb2-460e-8615-10e12376af4a"}
        };
    }

    @DataProvider(name = "addAssetType")
    public Object[][] addAssetType() {
        return new Object[][]{
                {"demo1", "demo1"},
                {"demo2", "demo2"}
        };
    }

    @Test
     void listOfAssetTypes() throws IOException {
        String[] title = {"Laptops", "Monitors", "Accessories"};
        String[] icon = {"https://google.com/laptops", "https://google.com/monitors", "https://google.com/accessories"};

        sendRequest(Queries.ASSET_TYPES, queryVariables);
        QueryResponse response = getRespose();

        for (int i = 0; i < title.length; i++) {
            Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i).getTitle(), title[i].trim());
            Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i).getIcon(), icon[i].trim());
        }
    }

    @Test
    void listOfAssetTypes2() throws IOException {
        String[] title = {"Laptops", "Monitors", "Accessories"};
        String[] icon = {"https://google.com/laptops", "https://google.com/monitors", "https://google.com/accessories"};

        QueryResponse response = sendRequest(Queries.ASSET_TYPES, queryVariables);

        for (int i = 0; i < title.length; i++) {
            Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i).getTitle(), title[i].trim());
            Assert.assertEquals(response.getData().getAssetTypes().getItems().get(i).getIcon(), icon[i].trim());
        }
    }

    @Test(dataProvider = "DetailsOfAssetType")
    void DetailsOfAssetType(String title, String icon, String uuid) throws IOException {
        queryVariables.setUuid(uuid);

        QueryResponse response = sendRequest(Queries.ASSET_TYPE, queryVariables);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getData().getAssetType().getItem().getUuid().trim(), uuid.trim());
        softAssert.assertEquals(response.getData().getAssetType().getItem().getTitle().trim(), title.trim());
        softAssert.assertEquals(response.getData().getAssetType().getItem().getIcon().trim(), icon.trim());
        softAssert.assertAll();
    }

    @Test(dataProvider = "addAssetType")
    void addAssetType(String title, String icon) throws IOException {
        queryVariables.setTitle(title);
        QueryResponse response = sendRequest(Queries.ADD_ASSET_TYPE, queryVariables);

        Assert.assertEquals(response.getData().getCreateAssetType().getItem().getTitle(), title.trim());
    }
}
