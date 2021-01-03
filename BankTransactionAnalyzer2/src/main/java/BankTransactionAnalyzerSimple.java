import java.io.IOException;

public class BankTransactionAnalyzerSimple {

    public static void main(final String... args) throws IOException {

        // Step1. 기본 Transaction Analyzer 소스
        /*
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;

        for (final String line : lines) {
            final String[] columns = line.split(",");
            total += Double.parseDouble(columns[1]);
        }

        System.out.println("The total for all transactions is " + total);
        */

        // Step2. 기본 1월의 Transaction Analyzer 소스
        /*
        final Path path = Paths.get(RESOURCES + args[0]);
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == Month.JANUARY) {
                total += Double.parseDouble(columns[1]);
            }
        }
        System.out.println("The total for all transactions in January is " + total);
         */

        // Step3. BankStatementCSVParser를 이용해서 리팩토링
        /*
        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);
        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);

        collectSummary(bankStatementProcessor);
         */

        // Step4. BankStatementParser를 이용한 리팩토링
        BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        BankStatementParser bankStatementParser = new BankStatementCSVParser();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser);
    }
}
