package digital.windmill.audra.dao.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "title")
    private String title;
    @Column(name = "serial_number")
    private String serialNumber;
    @OneToOne
    @JoinColumn(name = "asset_type_id")
    private AssetTypeEntity type;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @Column(name = "archived_date")
    private Instant archivedDate;
    @Column(name = "purchased_date")
    private Instant purchasedDate;

}
