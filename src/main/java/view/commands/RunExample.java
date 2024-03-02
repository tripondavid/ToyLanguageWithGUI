package view.commands;

import controller.Controller;
import exception.MyException;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            //controller.typeCheck();
            controller.allSteps();
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
