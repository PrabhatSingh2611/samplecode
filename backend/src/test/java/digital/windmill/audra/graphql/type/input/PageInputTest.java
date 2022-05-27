package digital.windmill.audra.graphql.type.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class PageInputTest {

    private PageInput pageInput;
    
    @BeforeEach
    void beforeEach() {
        pageInput = new PageInput();
    }

    @ParameterizedTest
    @MethodSource("testData")
    void shoudReturnMaxItemsPerPageOnNull(Integer value, Integer expectedResult) {
        pageInput.setItemsPerPage(value);
        Assertions.assertEquals(expectedResult, pageInput.getItemsPerPage());
    }

    static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(-10, PageInput.MAX_ITEMS_PER_PAGE),
                Arguments.of(-1, PageInput.MAX_ITEMS_PER_PAGE),
                Arguments.of(0, PageInput.MAX_ITEMS_PER_PAGE),
                Arguments.of(10, 10),
                Arguments.of(99, 99),
                Arguments.of(101, PageInput.MAX_ITEMS_PER_PAGE),
                Arguments.of(1000, PageInput.MAX_ITEMS_PER_PAGE)
                );
    }
}
