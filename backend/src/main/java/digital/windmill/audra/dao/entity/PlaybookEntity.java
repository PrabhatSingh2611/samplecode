package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.PlaybookStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "playbook")
public class PlaybookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status", columnDefinition = "PLAYBOOK_STATUS")
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PlaybookStatus status;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ResourceEntity image;

    @OneToMany(mappedBy = "playbook", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sort ASC")
    private List<PlaybookSectionEntity> sections;

    @CreationTimestamp()
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
