package reuse.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static reuse.fixture.UserFixture.TEST_USER;

public class UserTest {
    @DisplayName("후기에 작성된 평가 점수가 사용자에게 더해지는지")
    @Test
    public void addScore() {
        Integer score = 10;

        int addedScore = TEST_USER.addScore(score);
        assertThat(addedScore).isEqualTo(10);
    }
}


