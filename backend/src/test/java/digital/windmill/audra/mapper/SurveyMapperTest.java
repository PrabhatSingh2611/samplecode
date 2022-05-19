package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.dao.entity.QuestionEntity;
import digital.windmill.audra.dao.entity.SurveyEntity;
import digital.windmill.audra.dao.entity.enums.QuestionType;
import digital.windmill.audra.graphql.mapper.QuestionMapper;
import digital.windmill.audra.graphql.mapper.SurveyMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateQuestionInput;
import digital.windmill.audra.graphql.type.input.CreateQuestionOptionInput;
import digital.windmill.audra.graphql.type.input.CreateSurveyInput;
import digital.windmill.audra.graphql.type.input.PatchSurveyInput;
import digital.windmill.i18n.dao.MultiLanguageText;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SurveyMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    QuestionMapper questionMapper;

    @Mock
    private I18nMapper i18nMapper;

    @InjectMocks
    SurveyMapperImpl mapper;

    @Test
    void shouldMapSurveyEntityToSurvey() {
        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.mapSurveyEntityToSurvey(createSurveyEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getTitle());
        assertEquals(TEST_UUID, result.getId());
        assertEquals(TEXT, result.getDescription());
    }

    @Test
    void shouldMapInputToSurveyEntity() {
        when(questionMapper
                .mapCreateQuestionInputToQuestionEntity(any(CreateQuestionInput.class)))
                .thenReturn(createQuestionEntity());

        when(i18nMapper.create(anyString())).then(i -> {
            var multiLanguageText = new MultiLanguageText();
            multiLanguageText.put("en", i.getArgument(0, String.class));
            return multiLanguageText;
        });

        var result = mapper.mapCreateAnnounceInputToSurveyEntity(createSurveyInput());
        assertNotNull(result);
        assertEquals(TEXT, result.getTitle().get("en"));
        assertEquals(TEXT, result.getDescription().get("en"));
        assertEquals(QuestionType.SINGLE, result.getQuestions().get(0).getType());
        assertEquals(TEXT, result.getQuestions().get(0).getBody().get("en"));
        assertEquals(TEXT, result.getQuestions().get(0).getOptions().get(0).getText().get("en"));
    }

    @Test
    void shouldMapInputToSurveyEntityWhenUpdate() {
        when(i18nMapper.update(any(MultiLanguageText.class), anyString())).then(i -> {
            var multiLanguageText = i.getArgument(0, MultiLanguageText.class);
            multiLanguageText.put("en", i.getArgument(1, String.class));
            return multiLanguageText;
        });
        when(questionMapper
                .mapCreateQuestionInputToQuestionEntity(any(CreateQuestionInput.class)))
                .thenReturn(createQuestionEntity());

        var result = mapper.mapPatchSurveyInputToSurveyEntity(
                patchSurveyInput(), createSurveyEntityWhenUpdate());
        assertNotNull(result);
        assertEquals(TEXT, result.getTitle().get("en"));
        assertEquals(TEXT, result.getDescription().get("en"));
        assertEquals(1, result.getQuestions().get(0).getOptions().size());
    }

    private CreateSurveyInput createSurveyInput() {
        return CreateSurveyInput.builder()
                .title(TEXT)
                .description(TEXT)
                .questions(List.of(createQuestionInput()))
                .build();
    }

    private CreateQuestionInput createQuestionInput() {
        return CreateQuestionInput
                .builder()
                .type(QuestionType.SINGLE)
                .body(TEXT)
                .options(List.of(createOptionInput()))
                .build();
    }

    private CreateQuestionOptionInput createOptionInput() {
        return CreateQuestionOptionInput
                .builder()
                .text(TEXT)
                .build();
    }

    private PatchSurveyInput patchSurveyInput() {
        return PatchSurveyInput.builder()
                .id(TEST_UUID)
                .title(TEXT)
                .description(TEXT)
                .questions(List.of(createQuestionInput()))
                .build();
    }


    private SurveyEntity createSurveyEntity() {
        SurveyEntity s = new SurveyEntity();
        s.setId(ID);
        s.setUuid(TEST_UUID);
        s.setTitle(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        s.setDescription(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        s.setQuestions(List.of(createQuestionEntity()));
        return s;
    }

    private SurveyEntity createSurveyEntityWhenUpdate() {
        var optionEntity = new OptionEntity();
        optionEntity.setText(new MultiLanguageText() {{
            put("en", TEXT);
        }});

        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setBody(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        questionEntity.setType(QuestionType.SINGLE);
        List<OptionEntity> options = new ArrayList<>();
        options.add(optionEntity);

        questionEntity.setOptions(options);


        optionEntity.setQuestion(questionEntity);

        var surveyEntity = new SurveyEntity();

        List<QuestionEntity> questions = new ArrayList<>();
        questions.add(questionEntity);

        surveyEntity.setQuestions(questions);
        surveyEntity.setTitle(new MultiLanguageText() {{
            put("en", TEXT);
        }});

        questionEntity.setSurvey(surveyEntity);
        return surveyEntity;
    }

    private QuestionEntity createQuestionEntity() {
        var q = new QuestionEntity();
        q.setBody(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        q.setUuid(TEST_UUID);
        q.setId(ID);
        q.setType(QuestionType.SINGLE);
        q.setOptions(List.of(createOptionEntity()));
        return q;
    }

    private OptionEntity createOptionEntity() {
        var o = new OptionEntity();
        o.setId(ID);
        o.setText(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        o.setUuid(TEST_UUID);
        return o;
    }
}
