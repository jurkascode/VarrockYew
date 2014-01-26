package net.jurka.yew.tasks.bank;

import net.jurka.yew.Constant;
import net.jurka.yew.Task;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Npc;
import org.powerbot.script.wrappers.Tile;
import sun.text.resources.CollationData_sk;

/**
 * Created by rita on 26.01.14.
 */
public class OpenBank extends Task {

    public OpenBank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Tile playerLocation = ctx.players.local().getLocation();

        return ctx.backpack.count() == Constant.MAX_ITEMS_INVETORY
                && !ctx.bank.isOpen()
                && ctx.movement.getDistance(playerLocation, Constant.AT_BANK) < 10
                && !ctx.npcs.select().id(ctx.bank.BANK_NPC_IDS).nearest().isEmpty();
    }

    @Override
    public void execute() {
        Npc bank = ctx.npcs.poll();

        if (bank.isOnScreen()) {
            bank.interact("Bank");
        }
    }
}
