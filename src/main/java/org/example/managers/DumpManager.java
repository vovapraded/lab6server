package org.example.managers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import org.example.dto.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * The class is responsible for loading and saving the collection
 */
public class DumpManager {
        public static void loadFromFile(Collection collection) {
            StringBuilder stringBuilder = new StringBuilder();
            String filePath = System.getenv("FILE_PATH");
            try {
                File file = new File(filePath);
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    stringBuilder.append(scanner.nextLine());
                }
                if (!stringBuilder.isEmpty()){
                    collection.addHashMap(DumpManager.parseJson(stringBuilder.toString()));
                }
                scanner.close();
            } catch (FileNotFoundException e){
                System.out.println("Файл не найден или нет прав");
            }
            catch (Exception e) {
                System.out.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }
        public static HashMap<Long,Ticket> parseJson(String json) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Venue.class, new VenueFormatting())
                    .registerTypeAdapter(Coordinates.class, new CoordinatesFormatting())
                    .registerTypeAdapter(Ticket.class, new TicketFormatting())
                    .create();
            Type type = new TypeToken<HashMap<Long, Ticket>>(){}.getType();
            HashMap<Long, Ticket> hashMap = gson.fromJson(json, type);
            ArrayList<Venue> venues = new ArrayList<>();
            for (Ticket ticket : hashMap.values()){
                venues.add( ticket.getVenue());
            }
            return hashMap;
        }

        public static void saveToFile(Collection collection){
            String json = DumpManager.convertToJson(collection.getHashMap());
            // Путь к файлу, который нужно перезаписать
            String filePath = System.getenv("FILE_PATH");

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                // Преобразование строки в массив байтов
                byte[] bytes = json.getBytes();
                // Запись массива байтов в файл
                fos.write(bytes);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла: " + e.getMessage());
            }
        }
        public static String convertToJson(HashMap<Long, Ticket> hashMap) {
            Gson gsonBuiler = new GsonBuilder()
                    .registerTypeAdapter(Date.class, new DateFormatting())
                    .create();
            return gsonBuiler.toJson(hashMap);
        }

    }