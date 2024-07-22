import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import task.Solution;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SolutionTest {

    @InjectMocks
    private Solution solution;

    @Test
    void shouldReturnShortenerURLHttp() {
        //given
        String url = "http:///loooooong.com/somepath";
        String keyword = "MY-NEW-PATH";

        //when
        String shortenerUrl = solution.shortGivenUrl(url, keyword);

        //then
        assertThat(shortenerUrl).isNotEmpty();
        assertThat(shortenerUrl).isNotEqualTo(url);
        assertThat(shortenerUrl).isEqualTo("http://short.com/MY-NEW-PATH");
    }

    @Test
    void shouldReturnShortenerURLHttps() {
        //given
        String url = "https:///loooooong.com/somepath";
        String keyword = "MY-NEW-PATH";

        //when
        String shortenerUrl = solution.shortGivenUrl(url, keyword);

        //then
        assertThat(shortenerUrl).isNotEmpty();
        assertThat(shortenerUrl).isNotEqualTo(url);
        assertThat(shortenerUrl).isEqualTo("https://short.com/MY-NEW-PATH");
    }

    @Test
    void shouldThrowExceptionWhenKeywordIsEmpty() {
        //given
        String url = "http:///loooooong.com/somepath";
        String keyword = "";

        //when //then
        assertThatThrownBy(() -> solution.shortGivenUrl(url, keyword))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Keyword can not be empty string");
    }

    @Test
    void shouldThrowExceptionWhenUrlIsEmpty() {
        //given
        String url = "";
        String keyword = "test";

        //when //then
        assertThatThrownBy(() -> solution.shortGivenUrl(url, keyword))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Url can not be empty");
    }


    @Test
    void shouldThrowExceptionWhenKeywordHasMoreThan20Chars() {
        //given
        String url = "http:///loooooong.com/somepath";
        String keyword = "1234567890123456789011";

        //when //then
        assertThatThrownBy(() -> solution.shortGivenUrl(url, keyword))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Keyword can not have more than 20 characters");

    }

    @Test
    void shouldThrowExceptionWhenKeywordIsTheSameButUrlIsDifferent(){
        //given
        String url1 = "http:///loooooong.com/somepath";
        String url2 = "http:///loooooong.com/somepath2";
        String keyword = "keyword";

        //when
        solution.shortGivenUrl(url1, keyword);

        // then
        assertThatThrownBy(() -> solution.shortGivenUrl(url2, keyword))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Some url already contains this keyword");
    }

}
