package org.example.connection;

import java.util.ArrayList;
import java.util.List;

public class ResponsePublisher {
    private static List<ResponseListener> listeners = new ArrayList<>();

    // Метод для добавления слушателей
    public static void addListener(ResponseListener listener) {
        listeners.add(listener);
    }

    // Метод для генерации события
    public static  void generateResponse(byte[] data) {
        for (ResponseListener listener : listeners) {
            listener.onResponse(data);
        }
    }
}
