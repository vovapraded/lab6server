package org.example.managers;

import com.google.gson.*;
import org.example.dto.*;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
/**
 * A class for deserializing coordinates
 */
public class CoordinatesFormatting implements JsonDeserializer<Coordinates> {

    @Override
    public Coordinates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        // Чтение полей из JSON
        if (!jsonObject.has("x")||!jsonObject.has("y")) throw new JsonParseException("Ошибка чтения координат");
        Double x = jsonObject.get("x").getAsDouble();
        Long y = jsonObject.get("y").getAsLong();
        if (y<=-618){
            throw new JsonParseException("Ошибка чтения координат");
        }
        return new Coordinates(x,y);
    }
}