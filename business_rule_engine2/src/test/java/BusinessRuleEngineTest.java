import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class BusinessRuleEngineTest {

    @Test
    void shouldHaveNoRulesInitially() {
        final Facts facts = new Facts();
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);

        assertEquals(0, businessRuleEngine.count());
    }

    @Test
    void shouldAddTwoActions() {
        final Facts facts = new Facts();
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);

        businessRuleEngine.addAction((f) -> { });
        businessRuleEngine.addAction((f) -> { });

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    void shouldExecuteOneAction() {
        final Facts facts = new Facts();
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);
        final Action mockAction = mock(Action.class);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        verify(mockAction).execute(facts);
    }

    @Test
    void shouldPerformAnActionWithFacts() {
        final Action mockAction = mock(Action.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addAction(mockAction);
        businessRuleEngine.run();

        verify(mockAction).execute(mockFacts);
    }
}