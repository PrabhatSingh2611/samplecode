package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.AssetRequestStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "asset_request")
public class AssetRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "request_date")
    private Instant requestDate;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @OneToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity asset;
    @Column(name = "status", columnDefinition = "EMPLOYEE_ROLE")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private AssetRequestStatus status;
    @Column(name = "comment")
    private String comment;

}
