package com.upfor.grover.handler.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Jackson: LocalDateTime 转 Long 序列化器
 */
public class LocalDateTimeToLongSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (localDateTime != null) {
            long timestamp = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
            jsonGenerator.writeNumber(timestamp);
        } else {
            jsonGenerator.writeNull();
        }
    }

}
