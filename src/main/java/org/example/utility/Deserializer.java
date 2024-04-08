package org.example.utility;

import lombok.SneakyThrows;
import org.example.commands.Command;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class Deserializer {
    @SneakyThrows
    public static Command deserialize(byte[] data){
        ByteArrayInputStream byteStream = new ByteArrayInputStream(data);

// Создание объектного потока ввода для десериализации объекта
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);

// Десериализация объекта из потока и приведение его к нужному типу
        Object deserializedObject = objectStream.readObject();
        return (Command) deserializedObject;

    }
}
