package org.example.commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import org.example.dto.*;
import org.example.utility.*;
import org.example.commands.*;
import org.example.managers.*;

/**
 * Command for help info
 */
public class Help implements Command {
    private final Collection collection = Collection.getInstance();
    private final  Console console = Console.getInstance();
    public Help(){

    }
    @Override
    public void execute(String arg) {
        String filePath = "/help.txt";
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream != null) {
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    console.print(line);
                }
            } catch (Exception e) {
                console.print("Ошибка при чтении файла: " + e.getMessage());
            }
        } else {
            console.print("Файл help.txt не найден");
        }
    }
}
