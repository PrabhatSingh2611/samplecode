package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.CandidateStatus;
import digital.windmill.audra.graphql.type.Node;
import digital.windmill.audra.graphql.type.Resource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.List;
import java.util.Optional;
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