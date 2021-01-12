import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser{

    private static final int EXPECTED_ATTRIBUTES_LENGTH = 3;
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");
        if (columns.length < EXPECTED_ATTRIBUTES_LENGTH) {
            throw new CSVSyntaxException();
        }

        OverlySpecificBankStatementValidator validator = new OverlySpecificBankStatementValidator(
                columns[2], columns[0], columns[1]
        );
        Notification notification = validator.validate();
        if (notification.hasErrors()) {
            // do somethig
            return null;
        }

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];



        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line : lines) {
            bankTransactions.add(parseFrom(line));
        }

        return bankTransactions;
    }
}
