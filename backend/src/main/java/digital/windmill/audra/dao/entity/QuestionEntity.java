package digital.windmill.audra.dao.entity;

import digital.windmill.audra.dao.entity.enums.QuestionType;
import digital.windmill.i18n.dao.MultiLanguageText;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Embedded
    @AttributeOverride(name = "texts", column = @Column(name = "body_i18n", columnDefinition = "jsonb"))
    private MultiLanguageText body;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private QuestionType type;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionEntity> options;
}
