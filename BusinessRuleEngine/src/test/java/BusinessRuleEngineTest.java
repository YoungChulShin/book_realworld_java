import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BusinessRuleEngineTest {

    @Test
    public void shouldHaveNoRulesInitially() {

        // given, when
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        // then
        assertThat(businessRuleEngine.count()).isEqualTo(0);
    }

    @Test
    public void shouldAddTwoActions() {

        // given
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();

        // when
        businessRuleEngine.addAction(() -> {});
        businessRuleEngine.addAction(() -> {});

        // then
        assertThat(businessRuleEngine.count()).isEqualTo(2);
    }

    @Test
    public void shouldExecuteOneAction() {

        // given
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine();
        Action mockAction = mock(Action.class);
        businessRuleEngine.addAction(mockAction);

        // when
        businessRuleEngine.run();

        // then
        verify(mockAction).execute();
    }

    @Test
    public void shouldPerformActionWithFacts() {

        // given
        Action mockAction = mock(Action.class);
        Facts mockFacts = mock(Facts.class);
        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        // when
        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        // then
        verify(mockAction).perform(mockFacts);
    }
}