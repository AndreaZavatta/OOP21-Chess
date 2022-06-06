package io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.TimeZone;
/**
 * this class has utils methods for input/output classes. 
 *
 */
public final class JsonUtils {
    private JsonUtils() { }
    /**
     * 
     * @return ObjectMapper for serializing and deserializing objects
     */
    public static ObjectMapper getMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .registerModule(new Jdk8Module())
                .registerModule(new ParameterNamesModule())
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT)
                .setTimeZone(TimeZone.getDefault());
    }
}
