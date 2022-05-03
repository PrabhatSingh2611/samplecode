package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.VacancyPriority;
import digital.windmill.audra.dao.entity.enums.VacancyStatus;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "vacancy")
public class VacancyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @OneToOne
    @JoinColumn(name = "position_id")
    private EmployeePositionEntity position;
    @Column(name = "description")
    private String description;
    @Column(name = "status", columnDefinition = "VACANCY_STATUS")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private VacancyStatus status;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity assignTo;
    @Column(name = "priority", columnDefinition = "VACANCY_PRIORITY")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private VacancyPriority priority;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "updated_at")
    private Instant updatedAt;

}
