package study.java;

import java.util.Map;

public class Document {

    private final Map<String, String> attributes;   // Document안에 attribute를 정의하고 외부에서 수정되는 것을 막는다

    public Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }
}