package auctioncentral.gui;

import java.util.*;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Dimension;

public class Window
    extends JFrame
    implements Observer/*, ActionListener*/ {
    
    private Deque<AbstractScreen> myScreenHistory;
    
    public Window(AbstractScreen initialScreen) {
        super("Auction Central: the auctioneer for non-profit organizations");
        myScreenHistory = new LinkedList<AbstractScreen>();
        initView(initialScreen);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initView(AbstractScreen initialScreen) {
        setPreferredSize(new Dimension(640, 480));
        pack();
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
        setVisible(true);
    }

    @Override
    public void update(Observable o, Object p) {
        
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
