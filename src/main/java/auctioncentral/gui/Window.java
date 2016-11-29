package auctioncentral.gui;

import java.util.*;
import javax.swing.*;
import java.awt.CardLayout;

public class Window extends JFrame {
    private Deque<AbstractScreen> myScreenHistory;
    
    public Window(AbstractScreen initialScreen) {
        super("Auction Central");
        myScreenHistory = new LinkedList<AbstractScreen>();
        initView(initialScreen);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initView(AbstractScreen initialScreen) {
        addScreen(initialScreen);
    }

    public void popScreen() {
        myScreenHistory.pop();
        switchScreen(myScreenHistory.peek());
    }

    public void addScreen(AbstractScreen screen) {
        myScreenHistory.push(screen);
        switchScreen(screen);
    }

    private void switchScreen(AbstractScreen screen) {
        setContentPane(screen);
    }

    private void guiEntryPoint() {
        setVisible(true);
    }

    public void start() {
        Thread th = new Thread(new Runnable() {
                @Override public void run() {
                    guiEntryPoint();
                }
            });
        th.start();
        try {
            th.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
