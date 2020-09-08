package ir.maktab.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.maktab.model.entity.Question;

import java.io.IOException;

public class CustomKeyDeserializer extends KeyDeserializer {

    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Object deserializeKey(String key, DeserializationContext deserializationContext) throws IOException, JsonProcessingException, JsonProcessingException {
        return mapper.readValue(key, Question.class);
    }
}

