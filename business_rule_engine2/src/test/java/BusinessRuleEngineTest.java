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

        businessRuleEngine.addRule((f) -> { });
        businessRuleEngine.addRule((f) -> { });

        assertEquals(2, businessRuleEngine.count());
    }

    @Test
    void shouldExecuteOneAction() {
        final Facts facts = new Facts();
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);
        final Rule mockRule = mock(Rule.class);

        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.run();

        verify(mockRule).perform(facts);
    }

    @Test
    void shouldPerformAnActionWithFacts() {
        final Rule mockRule = mock(Rule.class);
        final Facts mockFacts = mock(Facts.class);
        final BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(mockFacts);

        businessRuleEngine.addRule(mockRule);
        businessRuleEngine.run();

        verify(mockRule).perform(mockFacts);
    }
}