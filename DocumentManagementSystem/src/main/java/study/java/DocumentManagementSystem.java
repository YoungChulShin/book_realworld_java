package study.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentManagementSystem {

    private final List<Document> documents = new ArrayList<>();
    private final List<Document> documentsView = Collections.unmodifiableList(documents);

    private final Map<String, Importer> extensionToImporter = new HashMap<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }

    public void importFile(String path) throws IOException {
        final File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }

        final int separatorIndex = path.lastIndexOf('.');
        if (separatorIndex != -1) {
            if (separatorIndex == path.length()) {
                throw new UnknownFileTypeException("No extension found for file: " + path);
            }

            String extension = path.substring(separatorIndex + 1);
            Importer importer = extensionToImporter.get(extension);
            if (importer == null) {
                throw new UnknownFileTypeException("No exporter found for extension: " + path);
            }

            Document document = importer.importFile(file);
            documents.add(document);
        } else {
            throw new UnknownFileTypeException("No extension found for file: " + path);
        }
    }

    public List<Document> contents() {
        return documentsView;
    }

    public List<Document> search(String query) {
//        return documents.stream()
//                .filter(Query.parse(query))
//                .collect(Collectors.toList());
        return null;
    }
}
