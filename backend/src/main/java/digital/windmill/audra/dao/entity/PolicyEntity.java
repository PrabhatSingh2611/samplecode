package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.PolicyStatus;
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
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "policy")
public class PolicyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ResourceEntity file;

    @Column(name = "publication_date")
    private Instant publicationDate;

    @Column(name = "status", columnDefinition = "POLICY_STATUS")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PolicyStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity owner;
}
