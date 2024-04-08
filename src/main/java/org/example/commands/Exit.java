package org.example.commands;

import lombok.NoArgsConstructor;
import org.example.managers.Collection;
import org.example.utility.Console;

import java.io.Serial;
import java.io.Serializable;

/**
 * The shutdown command
 */
public class Exit extends Command implements Serializable {
    @Serial
    private static final long serialVersionUID = "Exit".hashCode();
    @Override
    public void execute() {
        console.print("Завершение работы");
        System.exit(0);
    }
}
