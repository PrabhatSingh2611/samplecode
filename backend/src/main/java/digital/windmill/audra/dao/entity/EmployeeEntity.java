package digital.windmill.audra.dao.entity;

import java.time.Instant;
import java.util.UUID;



import org.hibernate.annotations.Type;

import digital.windmill.audra.dao.entity.enums.EmployeeRole;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @JoinColumn(name = "manager_id")
    private EmployeeEntity reportingManager;
    @OneToOne
    @JoinColumn(name = "position_id")
    private EmployeePositionEntity position;
    @OneToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}
