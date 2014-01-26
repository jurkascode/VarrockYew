package net.jurka.yew.tasks;

import net.jurka.yew.Constant;
import net.jurka.yew.Task;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Tile;
import org.powerbot.script.wrappers.TilePath;

import java.util.concurrent.Callable;

public class WalkToBank extends Task {


    public WalkToBank(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        Tile playerLocation = ctx.players.local().getLocation();

        return ctx.backpack.count() == Constant.MAX_ITEMS_INVETORY
                && ctx.movement.getDistance(playerLocation, Constant.AT_BANK) > 10;
    }

    @Override
    public void execute() {
        Tile playerLocation = ctx.players.local().getLocation();

        if (ctx.movement.getDistance(playerLocation, Constant.AT_TREE) < 10) {
            // At trees
            final TilePath FROM_TREE_PATH = new TilePath(ctx, new Tile[] { Constant.AT_TREE, Constant.AT_BANK});

            new Condition().wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return FROM_TREE_PATH.traverse();
                }
            });
        }
    }
}
