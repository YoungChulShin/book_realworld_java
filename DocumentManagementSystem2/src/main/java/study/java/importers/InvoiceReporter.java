package study.java.importers;

import study.java.Document;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static study.java.Attributes.*;

public class InvoiceReporter implements Importer {

    @Override
    public Document importFile(File file) throws IOException {
        final TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "INVOICE");
        return new Document(attributes);
    }
}
