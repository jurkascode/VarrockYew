package net.jurka.yew.tasks;

import net.jurka.yew.Constant;
import net.jurka.yew.Task;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Tile;

import java.util.ArrayList;

public class Chop extends Task {

    public Chop(MethodContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        for (GameObject object : ctx.objects.select().nearest().limit(3)) {
            System.out.println(object.getId() + " " + object.getName() + " " + object.getLocation());
        }

        return ctx.backpack.select().count() < Constant.MAX_ITEMS_INVETORY
                && !ctx.players.local().isInMotion()
                && ctx.players.local().getAnimation() == -1
                && !ctx.objects.select().id(Constant.TREE_ID).nearest().within(new Tile(3205, 3503), 25f).isEmpty(); // The expensive call should be in end;
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.poll();

        if (tree.isOnScreen()) {
            tree.interact("Chop");
        } else {

            // Tree too far
            ctx.camera.turnTo(tree);
            // walk there
            ctx.movement.stepTowards(tree);
        }
    }
}
