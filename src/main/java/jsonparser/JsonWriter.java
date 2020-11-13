package jsonparser;
import java.lang.reflect.Field;
import java.util.*;

public class JsonWriter {

    private final StringBuilder out;
    private final Object src;

    public JsonWriter(Object src, StringBuilder out) {
        this.out = out;
        this.src = src;
        try {
            setWriter();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void setWriter() throws IllegalAccessException {
        out.append(beginObject());
        Map<String, Object> fields = getFields();
        Iterator<Map.Entry<String, Object>> iterator = fields.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            out.append(parseProperty(entry.getKey()));
            out.append(getKeySeperator());
            out.append(parseObjectValue(entry.getValue()));
            if (iterator.hasNext()) {
                out.append(getValueSeperator());
            }
        }
        out.append(endObject());
    }

    public Class<?> getSrcClass() {
        return src.getClass();
    }

    public Map<String, Object> getFields() throws IllegalAccessException {
        Field[] fieldlist = getSrcClass().getDeclaredFields();
        Map<String, Object> fields = new HashMap();
        for (Field field : fieldlist) {
            field.setAccessible(true);
            fields.put(field.getName(), field.get(src));
        }
        return fields;
    }

    public String getTypeOfObject(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public String parseObjectValue(Object obj) {
        StringBuilder sb = new StringBuilder();
        String type = getTypeOfObject(obj);
        switch (type) {
            case "String":
                String strVal = (String) obj;
                sb.append("\"").append(strVal).append("\"");
                break;
            case "Integer":
                sb.append((int) obj);
                break;
            case "Boolean":
                sb.append((boolean) obj);
                break;
        }
        return sb.toString();
    }

    public String parseProperty(String property) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"").append(property).append("\"");
        return sb.toString();
    }

    public String beginObject() {
        return "{";
    }

    public String endObject() {
        return "}";
    }

    public String getKeySeperator() {
        return ": ";
    }

    public String getValueSeperator() {
        return ",";
    }

    public StringBuilder getOut() {
        return out;
    }
}
