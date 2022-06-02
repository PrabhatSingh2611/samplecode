package api.enums;

public enum Queries {

    //AssetTypes
    ASSET_TYPES("query assetTypes { assetTypes { pageInfo { currentPage totalPages } totalItems items { id title icon } } }"),
    ASSET_TYPE("query getAssetType($id: UUID!) { assetType(input: {id: $id}) { item { id title icon } } }"),
    ADD_ASSET_TYPE("mutation addAssetType ($title: String!) { createAssetType(input: {title: $title}) { item { id title icon } } }"),

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