package digital.windmill.audra.graphql.mapper;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.graphql.type.QuestionOption;
import digital.windmill.audra.graphql.type.input.CreateQuestionOptionInput;
import digital.windmill.i18n.mapper.I18nMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;


@Mapper(componentModel = "spring", uses = {I18nMapper.class}, imports = {UUID.class})
public interface OptionMapper {

    @Mapping(target = "id", source = "uuid")
    QuestionOption mapOptionEntityToOption(OptionEntity entity);


    @Mapping(target = "uuid", expression = "java(UUID.randomUUID())")
    OptionEntity mapCreateOptionInputToQuestionEntity(CreateQuestionOptionInput input);
}
