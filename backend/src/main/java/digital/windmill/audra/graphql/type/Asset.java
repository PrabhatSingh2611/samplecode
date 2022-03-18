package digital.windmill.audra.graphql.type;

import java.time.ZonedDateTime;

import lombok.Data;

@Data
public class Asset {

    private Long id;
    private String title;
    private String serial;
    private AssetType type;
    private Employee employee;
    private ZonedDateTime archivedDate;
    private ZonedDateTime purchasedDate;

}

/**
 * type Asset {
    id: Int!
    title: String
    serial: String
    type: AssetType
    employee: Employee
    archivedDate: ZonedDateTime
    purchasedDate: ZonedDateTime
}
*/