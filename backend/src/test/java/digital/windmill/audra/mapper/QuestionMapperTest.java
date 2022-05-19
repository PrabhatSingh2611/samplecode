package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.dao.entity.enums.QuestionType;
import digital.windmill.audra.graphql.mapper.OptionMapper;
import digital.windmill.audra.graphql.mapper.QuestionMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateQuestionInput;
import digital.windmill.audra.graphql.type.input.CreateQuestionOptionInput;
import digital.windmill.i18n.dao.MultiLanguageText;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    OptionMapper optionMapper;

    @Mock
    private I18nMapper i18nMapper;

    @InjectMocks
    QuestionMapperImpl mapper;

    @Test
    void shouldMapQuestionEntityToQuestion() {
        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.mapQuestionEntityToQuestion(createQuestionEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getBody());
        assertEquals(TEST_UUID, result.getId());
        assertEquals(QuestionType.SINGLE, result.getType());
    }

    @Test
    void shouldMapCreateQuestionInputToQuestionEntity() {
        when(optionMapper
                .mapCreateOptionInputToQuestionEntity(any(CreateQuestionOptionInput.class)))
                .thenReturn(createOptionEntity());
        when(i18nMapper.create(anyString())).then(i -> {
            var multiLanguageText = new MultiLanguageText();
            multiLanguageText.put("en", i.getArgument(0, String.class));
            return multiLanguageText;
        });
        var result = mapper.mapCreateQuestionInputToQuestionEntity(createQuestionInput());
        assertNotNull(result);
        assertEquals(TEXT, result.getBody().get("en"));
        assertEquals(QuestionType.SINGLE, result.getType());
        assertEquals(TEXT, result.getOptions().get(0).getText().get("en"));
    }

    private CreateQuestionInput createQuestionInput() {
        return CreateQuestionInput
                .builder()
                .type(QuestionType.SINGLE)
                .body(TEXT)
                .options(List.of(createQuestionOptionInput()))
                .build();
    }

    private CreateQuestionOptionInput createQuestionOptionInput() {
        return CreateQuestionOptionInput
                .builder()
                .text(TEXT)
                .build();
    }

    private OptionEntity createOptionEntity() {
        var o = new OptionEntity();
        o.setId(ID);
        o.setText(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        return o;
    }


    private QuestionEntity createQuestionEntity() {
        var q = new QuestionEntity();
        q.setBody(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        q.setId(ID);
        q.setUuid(TEST_UUID);
        q.setType(QuestionType.SINGLE);
        return q;
    }
}
