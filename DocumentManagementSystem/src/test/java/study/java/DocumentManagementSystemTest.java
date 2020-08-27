package study.java;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static study.java.Attributes.*;

public class DocumentManagementSystemTest {

    private static final String RESOURCES =
            "src" + File.separator + "test" + File.separator + "resources" + File.separator;

    private static final String LETTER = RESOURCES + "patient.letter";

    private DocumentManagementSystem system = new DocumentManagementSystem();

    @Test
    public void shouldImportFile() throws IOException {
        system.importFile(LETTER);

        final Document document = onlyDocument();   // system에서 document를 가져온다

        assertAttributeEquals(document, PATH, LETTER);
    }

    @Test
    public void shouldImportLetterAttributes() throws IOException {

        system.importFile(LETTER);

        final Document document = onlyDocument();

        assertAttributeEquals(document, PATIENT, "Joe Bloggs");
        assertAttributeEquals(document, ADDRESS,
                "123 Fake Street\n" +
                        "Westminster\n" +
                        "London\n" +
                        "United Kingdom");

        assertAttributeEquals(document, TYPE, "LETTER");
    }

    @Test(expected = FileNotFoundException.class)
    public void shouldNotImportMissingFile() throws Exception {
        system.importFile("test.txt");
    }

    @Test(expected = UnknownFileTypeException.class)
    public void shouldNotImportUnknownFile() throws Exception {
        system.importFile(RESOURCES + "unknown.txt");
    }

    ////////////////////////////////////
    //== Test Help Functions
    ////////////////////////////////////
    private void assertAttributeEquals(
            Document document, String attributeName, String expectedValue) {

        assertEquals(
                "Document has the wrong value for " + attributeName,
                expectedValue,
                document.getAttribute(attributeName));
    }

    private Document onlyDocument() {
        final List<Document> documents = system.contents();
        assertThat(documents, hasSize(1));
        return documents.get(0);
    }
}