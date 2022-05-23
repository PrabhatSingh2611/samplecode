package api.enums;

public enum Queries {

    //AssetTypes
    ASSET_TYPES("{ assetTypes{ items{ uuid title icon } } }"),
    ASSET_TYPE("query getAssetType($uuid: UUID!){ assetType(input: {uuid: $uuid}) { item { uuid title icon } } }"),
    ADD_ASSET_TYPE("mutation($title:String!){ createAssetType(input:{title:$title}){ item{ uuid title icon } } }"),

    //Locations
    LOCATIONS("{ locations{ items{ uuid name } } }"),
    LOCATION("query getLocation($uuid:UUID!){ location(input:{uuid:$uuid}){ location{ uuid name } } }"),
    ADD_LOCATION("mutation addLocation($name:String!){ createLocation(input:{ name:$name }){ location{ uuid name } } }");

    private String query;

    Queries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

}