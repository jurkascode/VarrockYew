package net.jurka.yew;

import net.jurka.yew.tasks.Chop;
import net.jurka.yew.tasks.WalkToBank;
import net.jurka.yew.tasks.WalkToTree;
import net.jurka.yew.tasks.bank.Deposit;
import net.jurka.yew.tasks.bank.OpenBank;
import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.util.Random;

import java.awt.*;
import java.util.LinkedList;

@Manifest(name = "Varrock yew", authors = "Jurka", description = "Chop the yew in Varrock")
public class YewScript extends PollingScript implements MessageListener, PaintListener {

    private LinkedList<Task> tasks = new LinkedList<Task>();

    @Override
    public void start() {
        tasks.add(new Chop(ctx));
        tasks.add(new WalkToBank(ctx));
        tasks.add(new WalkToTree(ctx));
        tasks.add(new OpenBank(ctx));
        tasks.add(new Deposit(ctx));
    }

    @Override
    public int poll() {

        if (tasks.isEmpty()) {
            log.warning("There are no tasks to execute stop!");
            this.getController().stop();
        }

        for (Task task : tasks) {
            if (task.activate()) {
                log.info("Current task: " + task.getClass().getSimpleName());
                task.execute();
                return Random.nextInt(500, 1500);
            }
        }

        return 500;
    }

    @Override
    public void messaged(MessageEvent e) {
    }

    @Override
    public void repaint(Graphics g) {

    }
}