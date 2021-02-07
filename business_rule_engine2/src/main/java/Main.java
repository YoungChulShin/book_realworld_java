public class Main {
    public static void main(final String... args) {

        var env = new Facts();
        env.addFacts("name", "Bob");
        env.addFacts("jobTitle", "CEO");

        BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(env);
        businessRuleEngine.addAction(facts -> {
            var forecastedAmount = 0.0;
            var dealStage = Stage.valueOf(facts.getFacts("stage"));
            var amount = Double.parseDouble(facts.getFacts("amount"));
            switch (dealStage) {
                case LEAD:
                    forecastedAmount = amount * 0.2;
                    break;
                case EVALUATING:
                    forecastedAmount = amount * 0.5;
                    break;
                case INTERESTED:
                    forecastedAmount = amount * 0.8;
                    break;
                case CLOSED:
                    forecastedAmount = amount;
                    break;
            }
            facts.addFacts("forecastedAmount", String.valueOf(forecastedAmount));
        });
    }
}
