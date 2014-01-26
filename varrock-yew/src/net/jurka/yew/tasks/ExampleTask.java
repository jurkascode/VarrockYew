package net.jurka.yew.tasks;

import net.jurka.yew.Task;
import org.powerbot.script.methods.MethodContext;

public class ExampleTask extends Task {

    public ExampleTask(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        return true;
    }

    @Override
    public void execute() {
        // Do something here
    }
}