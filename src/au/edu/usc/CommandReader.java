package au.edu.usc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommandReader {
    public static void readCommands(String fileName, Inventory inventory) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        Commands commands = new Commands(inventory);

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            commands.executeCommand(tokens);
        }

        reader.close();
    }
} // end of class



