package digital.windmill.audra.dao.entity;

import digital.windmill.i18n.dao.MultiLanguageText;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "playbook_video")
public class PlaybookVideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "url")
    private String url;

    @Embedded
    @AttributeOverride(name = "texts", column = @Column(name = "description_i18n", columnDefinition = "jsonb"))
    private MultiLanguageText description;
}
