package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.PlaybookStepType;
import digital.windmill.i18n.dao.MultiLanguageText;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "playbook_step")
public class PlaybookStepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Embedded
    @AttributeOverride(name = "texts", column = @Column(name = "title_i18n", columnDefinition = "jsonb"))
    private MultiLanguageText title;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private PlaybookStepType type;

    @ManyToOne
    @JoinColumn(name = "playbook_task_list_id")
    private PlaybookTaskListEntity taskList;

    @ManyToOne
    @JoinColumn(name = "playbook_video_id")
    private PlaybookVideoEntity video;

    @ManyToOne
    @JoinColumn(name = "playbook_resource_id")
    private PlaybookResourceEntity resource;

    @ManyToOne
    @JoinColumn(name = "playbook_id")
    private PlaybookEntity playbook;
}
