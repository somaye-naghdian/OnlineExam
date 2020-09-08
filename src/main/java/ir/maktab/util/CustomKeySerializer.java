package ir.maktab.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import ir.maktab.model.entity.Question;

import java.io.IOException;

public class CustomKeySerializer extends JsonSerializer<Question> {

private static ObjectMapper mapper = new ObjectMapper();

@Override
public void serialize(Question value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeFieldName(mapper.writeValueAsString(value));
        }
}
