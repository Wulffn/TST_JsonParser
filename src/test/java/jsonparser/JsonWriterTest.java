package jsonparser;
import dto.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    private static JsonWriter jsonWriter;
    private static StringBuilder out;

    @BeforeAll
    public static void setup() {
        Person person = new Person("Homer", "Simpson", 50, true);
        out = new StringBuilder();
        jsonWriter = new JsonWriter(person, out);
    }

    @Test
    public void testNumberOfProperties() throws IllegalAccessException {
        int expected = 4;
        int actual = jsonWriter.getFields().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testIfMapContainsValue() throws IllegalAccessException {
        boolean expected = true;
        String valueToSearchMap = "Homer";
        Map<String, Object> fields = jsonWriter.getFields();
        boolean actual = fields.containsValue(valueToSearchMap);

        assertEquals(expected, actual);
    }

    @Test
    public void testIfMapContainsField() throws IllegalAccessException {
        boolean expected = true;
        String valueToSearchMap = "firstName";
        Map<String, Object> fields = jsonWriter.getFields();
        boolean actual = fields.containsKey(valueToSearchMap);

        assertEquals(expected, actual);
    }

    @Test
    public void testIfMapDoesNotContainValue() throws IllegalAccessException {
        boolean expected = false;
        String valueToSearchMap = "Bart";
        Map<String, Object> fields = jsonWriter.getFields();
        boolean actual = fields.containsValue(valueToSearchMap);

        assertEquals(expected, actual);
    }

    @Test
    public void testIfMapDoesNotContainField() throws IllegalAccessException {
        boolean expected = false;
        String valueToSearchMap = "birthday";
        Map<String, Object> fields = jsonWriter.getFields();
        boolean actual = fields.containsKey(valueToSearchMap);

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectObjectType() throws IllegalAccessException {
        String expected = "String";
        Object value = jsonWriter.getFields().get("firstName");
        String actual = jsonWriter.getTypeOfObject(value);

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectStringValueParsing() throws IllegalAccessException {
        String expected = "\"Homer\"";
        Object value = jsonWriter.getFields().get("firstName");
        String actual = jsonWriter.parseObjectValue(value);

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectIntegerValueParsing() throws IllegalAccessException {
        String expected = "50";
        Object value = jsonWriter.getFields().get("age");
        String actual = jsonWriter.parseObjectValue(value);

        assertEquals(expected, actual);
    }

    @Test
    public void testCorrectPropertyParsing() throws IllegalAccessException {
        String expected = "\"firstName\"";
        String value = jsonWriter.getFields().keySet().stream().findFirst().get();
        String actual = jsonWriter.parseProperty(value);

        assertEquals(expected, actual);
    }

    @Test
    public void testObjectParsingToJson() {
        String expected = "{\"firstName\":\"Homer\",\"lastName\":\"Simpson\",\"married\":true,\"age\":50}";
        String actual = out.toString().replaceAll(" ", "");

        assertEquals(expected, actual);
    }
}
