package auctioncentral.gui;

import java.util.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Window extends JFrame {
    
    private Deque<AbstractScreen> myScreenHistory;
    
    public Window() {
        super("Auction Central");
        myScreenHistory = new LinkedList<AbstractScreen>();
        initView();
    }

    private void initView() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setPreferredSize(new Dimension(640, 480));
        pack();
        setVisible(true);
    }

    public void popScreen() {
        myScreenHistory.pop();
        switchScreen(myScreenHistory.peek());
    }

    public void addScreen(AbstractScreen screen) {
        screen = new StatusBorder(screen, this);
        myScreenHistory.push(screen);
        switchScreen(screen);
    }

    private void switchScreen(AbstractScreen screen) {
        setContentPane(screen);
        pack();
        setVisible(true);
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
