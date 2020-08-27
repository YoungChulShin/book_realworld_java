package study.java;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static study.java.Attributes.*;

public class ReportImporter implements Importer {

    private static final String NAME_PREFIX = "Patient: ";

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);

        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLines(2, line -> false, BODY);

        Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "REPORT");

        return new Document(attributes);
    }
}
