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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "option")
public class OptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Embedded
    @AttributeOverride(name = "texts", column = @Column(name = "text_i18n", columnDefinition = "jsonb"))
    private MultiLanguageText text;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;
}
