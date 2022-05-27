package digital.windmill.audra.dao.entity;

import digital.windmill.i18n.dao.MultiLanguageText;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    @Embedded
    @AttributeOverride(name = "texts", column = @Column(name = "title_i18n", columnDefinition = "jsonb"))
    private MultiLanguageText title;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private ResourceEntity image;

    @OneToMany(mappedBy = "playbook", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaybookStepEntity> steps;

    @CreationTimestamp()
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}
