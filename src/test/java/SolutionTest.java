import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import task.Solution;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class SolutionTest {

    @InjectMocks
    private Solution solution;

    @Test
    void shouldMakeTwoPartitionOfPalindromes() {
        //given
        String word = "aab";

        //when
        List<List<String>> partition = solution.partition(word);

        //then
        assertThat(partition).hasSize(2)
                .contains(List.of("a", "a", "b"), List.of("aa", "b"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"exceedingStringLengthWord"})
    void shouldThrowExceptionWhenStringLengthIsOutOfScope(String value) {
        //when then
        assertThatThrownBy(() -> solution.partition(value))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Given string is out of scope");
    }

}
