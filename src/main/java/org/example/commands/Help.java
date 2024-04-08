package org.example.commands;

import java.io.*;
import java.util.Scanner;
import org.example.dto.*;
import org.example.utility.*;
import org.example.commands.*;
import org.example.managers.*;

/**
 * Command for help info
 */
public class Help extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Help".hashCode();

    @Override
    public void execute() {
        String filePath = "/help.txt";
        InputStream inputStream = getClass().getResourceAsStream(filePath);
        if (inputStream != null) {
            try (Scanner scanner = new Scanner(inputStream)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    console.addToSend(line);
                }
            } catch (Exception e) {
                console.addToSend("Ошибка при чтении файла: " + e.getMessage());
            }
        } else {
            console.addToSend("Файл help.txt не найден");
        }
        console.send();
    }
}
