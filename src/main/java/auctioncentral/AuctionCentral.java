package auctioncentral;

import auctioncentral.gui.contact.ContactAddItemView;
import auctioncentral.gui.contact.ContactEditAuctionView;
import auctioncentral.model.*;
import auctioncentral.model.Calendar;
import auctioncentral.gui.*;
import auctioncentral.view.CalendarView;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AuctionCentral /*implements Serializable*/ {

    private static String CONFIG_FILE = "config.txt";
    private static String SER_SAVE_FILE = "last.ser";

    public static void main(String[] args) throws IOException {
        make24AuctionsSER();

//        String serFileName = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)), StandardCharsets.UTF_8);
//        AuctionCentral ac = (AuctionCentral) deserializeFrom(serFileName);

        //LoginManager.setInstance(ac.getLoginManager());
        //Calendar.setInst(ac.getCalendar());

        Window window = new Window(new LoginView());
        window.start();
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                //serializeTo(this, SER_SAVE_FILE);
                SerializeOnExit.execute("last");
            }
        });
    }
    /*
    private static void serializeTo(Object o, String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object deserializeFrom(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();
            ois.close();
            fis.close();
            return o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    */
    private static void make24AuctionsSER() {
        LoginManager lm = new LoginManager();
        LoginManager.setInstance(lm);
        for (int i = 0; i < 50; i++) {
            lm.register(new Bidder("bidder" + i, "bidder" + i + "name"));
            lm.register(new Staff("staff" + i, "staff" + i + "name"));
            lm.register(new Contact("contact" + i, "contact" + i + "name", "nonprofit" + i));
        }
        Random r = new Random();
        Calendar cal = new Calendar();
        Calendar.setInst(cal);
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        for (int i = 1; i <= 24; i++) {
            Auction a = new Auction((Contact) lm.getUser("contact" + i),
                    c.getTime(), null, null);
            cal.addAuction(a);
            for (int j = 0; j < i; j++) {
                a.addItem(new Item("item" + j, ItemCondition.values()[r.nextInt(ItemCondition.values().length - 1)],
                        ItemSize.values()[r.nextInt(ItemSize.values().length)], r.nextInt(201) + 1,
                        "donor" + j, "description" + j, "comment" + j));
            }
            if (i % 2 == 0) {
                c.add(java.util.Calendar.DATE, 1);
            }
        }
        //return new AuctionCentral(lm, cal);
    }
}
