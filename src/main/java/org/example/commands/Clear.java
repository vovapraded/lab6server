package org.example.commands;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.managers.*;
import org.example.utility.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * The clear collection command
 */
public class Clear extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Clear".hashCode();

    @Override
    public void execute() {
        collection.clearCollection();
        console.addToSend("Коллекция очищена");
        console.send();
    }
}
