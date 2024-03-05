package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class CardTest {

    static Stream<Arguments> createCard() {
        return Stream.of(
                Arguments.of(
                        Map.entry(new Card(Letter.TWO, Mark.CLOVER), 2),
                        Map.entry(new Card(Letter.TEN, Mark.CLOVER), 10),
                        Map.entry(new Card(Letter.K, Mark.CLOVER), 10),
                        Map.entry(new Card(Letter.A, Mark.CLOVER), 1)
                )
        );
    }

    @DisplayName("문자(String)와 모양(String)을 가지고 있다.")
    @Test
    void create_success() {
        assertThatCode(() -> new Card(Letter.THREE, Mark.DIAMOND))
                .doesNotThrowAnyException();
    }

    @DisplayName("숫자로 계산한 값을 반환한다.")
    @ParameterizedTest
    @MethodSource("createCard")
    void getValue(Map.Entry<Card, Integer> input) {
        int actual = input.getKey().getValue();
        int expected = input.getValue();

        assertThat(actual).isEqualTo(expected);
    }
}
