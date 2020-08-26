import java.io.IOException;

public class BankTransactionAnalyzerSimple {
    public static void main(final String... args) throws IOException {

        final BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();
        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final Exporter exporter = new HtmlExporter();

        bankStatementAnalyzer.analyze(args[0], bankStatementParser, exporter);
    }
}
