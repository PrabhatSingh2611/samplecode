package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.graphql.type.Question;
import digital.windmill.audra.graphql.type.input.CreateQuestionInput;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Objects;
import java.util.UUID;


@Mapper(componentModel = "spring",
        uses = {OptionMapper.class, I18nMapper.class},
        imports = {UUID.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuestionMapper {

    @Mapping(target = "id", source = "uuid")
    Question mapQuestionEntityToQuestion(QuestionEntity entity);


    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    QuestionEntity mapCreateQuestionInputToQuestionEntity(CreateQuestionInput input);

    @AfterMapping
    default void addQuestionToOption(@MappingTarget QuestionEntity questionEntity) {
        if (Objects.nonNull(questionEntity.getOptions())) {
            questionEntity.getOptions().forEach(option -> option.setQuestion(questionEntity));
        }
    }
}
