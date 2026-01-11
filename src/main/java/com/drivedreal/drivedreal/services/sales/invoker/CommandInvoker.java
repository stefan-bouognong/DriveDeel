package com.drivedreal.drivedreal.services.sales.invoker;

import com.drivedreal.drivedreal.services.sales.command.ICommand;
import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

    private List<ICommand> commandHistory = new ArrayList<>();
    private int currentIndex = -1;

    public void executeCommand(ICommand command) {
        command.execute();
        // Remove commands after currentIndex (redo history)
        while (commandHistory.size() > currentIndex + 1) {
            commandHistory.remove(commandHistory.size() - 1);
        }
        commandHistory.add(command);
        currentIndex++;
    }

    public void undo() {
        if (currentIndex >= 0) {
            ICommand command = commandHistory.get(currentIndex);
            command.undo();
            currentIndex--;
        }
    }

    public void redo() {
        if (currentIndex + 1 < commandHistory.size()) {
            ICommand command = commandHistory.get(currentIndex + 1);
            command.execute();
            currentIndex++;
        }
    }

    public List<ICommand> getHistory() {
        return commandHistory;
    }
}

