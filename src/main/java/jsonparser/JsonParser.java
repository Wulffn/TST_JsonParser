package jsonparser;

import java.io.IOException;

public class JsonParser {

    public String toJson(Object src) {
        StringBuilder writer = new StringBuilder();
        JsonWriter jsonWriter = new JsonWriter(src, writer);
        return jsonWriter.getOut().toString();
    }
}
