package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.graphql.type.Survey;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring",
        uses = {DateTimeMapper.class, QuestionMapper.class, I18nMapper.class},
        imports = {UUID.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface SurveyMapper {

    @Mapping(target = "id", source = "uuid")
    @Mapping(target = "questions", ignore = true)
    Survey mapSurveyEntityToSurvey(SurveyEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    SurveyEntity mapCreateAnnounceInputToSurveyEntity(CreateSurveyInput input);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uuid", ignore = true)
    SurveyEntity mapPatchSurveyInputToSurveyEntity(PatchSurveyInput input,
                                                   @MappingTarget SurveyEntity entity);

    @AfterMapping
    default void addSurveyToQuestion(@MappingTarget SurveyEntity surveyEntity) {
        surveyEntity.getQuestions().forEach(question -> question.setSurvey(surveyEntity));
    }
}
