package api;

import api.enums.Queries;
import api.pojo.QueryResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class LocationTest extends BaseApiTest {

    @DataProvider(name = "locationDetails")
    public Object[][] locationDetails() {
        return new Object[][]{
                {"Lisbon", "cabfb51b-41c5-4f4d-afd5-146c796391af"},
                {"Madrid", "79761728-8f51-475f-aa04-42385a0dfe36"}
        };
    }

    @DataProvider(name = "addLocation")
    public Object[][] addLocation() {
        return new Object[][]{
                {"demo1"},
                {"demo2"}
        };
    }

    @Test
    void listOfLocations() throws IOException {
        String[] name = {"Lisbon", "Madrid"};
        String[] uuid = {"cabfb51b-41c5-4f4d-afd5-146c796391af", "79761728-8f51-475f-aa04-42385a0dfe36"};

        QueryResponse response = sendRequest(Queries.LOCATIONS, queryVariables);
        for (int i = 0; i < name.length; i++) {
            Assert.assertEquals(response.getData().getLocations().getItems().get(i).getName(), name[i].trim());
            Assert.assertEquals(response.getData().getLocations().getItems().get(i).getUuid(), uuid[i].trim());
        }
    }

    @Test(dataProvider = "locationDetails")
    void locationDetails(String name, String uuid) throws IOException {
        queryVariables.setUuid(uuid);
        QueryResponse response = sendRequest(Queries.LOCATION, queryVariables);

        Assert.assertEquals(response.getData().getLocation().getLocation().getUuid().trim(), uuid.trim());
        Assert.assertEquals(response.getData().getLocation().getLocation().getName().trim(), name.trim());
    }

    @Test(dataProvider = "addLocation")
    void addLocation(String name) throws IOException {
        queryVariables.setName(name);
        QueryResponse response = sendRequest(Queries.ADD_LOCATION, queryVariables);

        Assert.assertEquals(response.getData().getCreateAssetType().getItem().getTitle(), name.trim());
    }
}
