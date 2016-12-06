package auctioncentral.gui;

import java.util.*;
import javax.swing.*;

public abstract class AbstractScreen
    extends JPanel {
    
    private Window myRoot;

    public AbstractScreen(Window theRoot) {
        myRoot = theRoot;
    }

    /**
     *
     * @return the Window containing this panel
     */
    public Window getRoot() {
        return myRoot;
    }
}
