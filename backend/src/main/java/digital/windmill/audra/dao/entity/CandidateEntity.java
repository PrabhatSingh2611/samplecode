package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;
@Entity
@Getter
@Setter
@Table(name = "candidate")
public class CandidateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @ManyToOne
    @JoinColumn(name = "vacancy_id")
    private VacancyEntity vacancy;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "linkedin")
    private String linkedIn;
    @ManyToMany(cascade = CascadeType.MERGE)
    @Column(name = "attachment_uuid")
    @JoinTable(name = "candidate_to_resources",
            joinColumns = @JoinColumn(name = "candidate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
    private List<ResourceEntity> attachments;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private CandidateStatus status;
}