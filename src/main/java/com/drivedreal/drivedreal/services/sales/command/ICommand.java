package com.drivedreal.drivedreal.services.sales.command;

public interface ICommand {
    void execute();
    void undo();
    String getDescription();
}

