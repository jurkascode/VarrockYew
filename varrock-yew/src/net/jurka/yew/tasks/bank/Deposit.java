package net.jurka.yew.tasks.bank;

import net.jurka.yew.Task;
import org.powerbot.script.methods.MethodContext;

/**
 * Created by rita on 26.01.14.
 */
public class Deposit extends Task {

    public Deposit(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.bank.isOpen();
    }

    @Override
    public void execute() {
        if (ctx.bank.isOpen()) {
            ctx.bank.depositInventory();
        }
    }
}
