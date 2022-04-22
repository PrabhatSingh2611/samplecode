package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.ObjectiveStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="objective")
public class ObjectiveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "comments")
    private String comments;
    @Column(name = "due_to_date")
    private Instant dueToDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private ObjectiveStatus status;
}
