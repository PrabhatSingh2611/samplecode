package digital.windmill.audra.dao.entity;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.Type;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "role", columnDefinition = "EMPLOYEE_ROLE")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private EmployeeRole role;
    @Column(name = "birthday")
    private Instant birthday;
    @ManyToOne
    @JoinColumn(name = "manager")
    private EmployeeEntity reportingManager;
    @OneToOne
    @JoinColumn(name = "position_id")
    private EmployeePositionEntity position;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}
