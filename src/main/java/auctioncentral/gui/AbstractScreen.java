package auctioncentral.gui;

import java.util.*;
import javax.swing.*;

public abstract class AbstractScreen
    extends JPanel implements Observer {

    /**
     *
     * @return the Window containing this panel
     */
    public Window getRoot() {
        return (Window) SwingUtilities.getRoot(this);
    }
}
