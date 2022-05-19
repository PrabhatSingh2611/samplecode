package digital.windmill.audra.mapper;

import digital.windmill.audra.dao.entity.OptionEntity;
import digital.windmill.audra.graphql.mapper.OptionMapperImpl;
import digital.windmill.audra.graphql.type.input.CreateQuestionOptionInput;
import digital.windmill.i18n.dao.MultiLanguageText;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OptionMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";

    @Mock
    private I18nMapper i18nMapper;

    @InjectMocks
    OptionMapperImpl mapper;

    @Test
    void shouldMapOptionEntityToOption() {
        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.mapOptionEntityToOption(createOptionEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getText());
        assertEquals(TEST_UUID, result.getId());
    }

    @Test
    void shouldMapCreateOptionInputToQuestionEntity() {
        when(i18nMapper.create(anyString())).then(i -> {
            var multiLanguageText = new MultiLanguageText();
            multiLanguageText.put("en", i.getArgument(0, String.class));
            return multiLanguageText;
        });
        var result = mapper.mapCreateOptionInputToQuestionEntity(createQuestionOptionInput());
        assertNotNull(result);
        assertEquals(TEXT, result.getText().get("en"));
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
        o.setUuid(TEST_UUID);
        return o;
    }
}
