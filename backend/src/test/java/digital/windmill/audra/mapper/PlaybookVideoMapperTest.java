package digital.windmill.audra.mapper;


import digital.windmill.audra.dao.entity.PlaybookVideoEntity;
import digital.windmill.audra.graphql.mapper.PlaybookVideoMapperImpl;
import digital.windmill.i18n.dao.MultiLanguageText;
import digital.windmill.i18n.mapper.I18nMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaybookVideoMapperTest {
    private static final UUID TEST_UUID = UUID.fromString("755b97b4-bb9a-4318-bf3a-a23646e592af");
    private static final Long ID = 22L;
    private static final String TEXT = "9AMj3X";
    private static final String URL_STRING = "https://test.com";
    private static final URL URL_URL;

    static {
        try {
            URL_URL = new URL("https://test.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Mock
    private I18nMapper i18nMapper;

    @InjectMocks
    PlaybookVideoMapperImpl mapper;

    @Test
    void shouldMap() {

        when(i18nMapper.get(any(MultiLanguageText.class)))
                .then(i -> i.getArgument(0, MultiLanguageText.class).get("en"));

        var result = mapper.map(createPlaybookVideoEntity());
        assertNotNull(result);
        assertEquals(TEXT, result.getDescription());
        assertEquals(URL_URL, result.getUrl());
        assertEquals(TEST_UUID, result.getId());
    }

    private PlaybookVideoEntity createPlaybookVideoEntity() {
        var p = new PlaybookVideoEntity();
        p.setId(ID);
        p.setUuid(TEST_UUID);
        p.setDescription(new MultiLanguageText() {{
            put("en", TEXT);
        }});
        p.setUrl(URL_STRING);
        return p;
    }

}
