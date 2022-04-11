package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
    @OneToOne
    @JoinColumn(name = "position_id")
    private EmployeePositionEntity position;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}
