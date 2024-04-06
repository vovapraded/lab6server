package org.example.commands;
import org.example.managers.*;
import org.example.utility.*;

import java.io.IOException;

/**
 * Interface for the command
 */
public interface Command {
    void execute(String arg) throws IOException;
}
