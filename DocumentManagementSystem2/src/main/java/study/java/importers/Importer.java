package study.java.importers;

import study.java.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {

    Document importFile(File file) throws IOException;
}
