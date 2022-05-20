package digital.windmill.audra.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private EmployeeEntity assignee;
    @Column(name = "purchased_date")
    private Instant waybillDate;
    @OneToOne
    @JoinColumn(name= "location_id")
    private LocationEntity location;
    @Column(name= "tag_number")
    private String tagNumber;
    @Column(name = "next_action_date")
    private Instant nextActionDate;
    @Column(name = "action_on_name")
    private String actionOnName;
    @Column(name = "archived_date")
    private Instant archivedDate;
    @Column(name= "comment")
    private String comment;
}
