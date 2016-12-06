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

public class AuctionCentral {

    private static final String CONFIG_FILE = "config.txt";
    private static String SER_SAVE_FILE_PREFIX = "last";

    public static void main(String[] args) throws IOException {
        make24AuctionsSER();

//        String fileText = new String(Files.readAllBytes(Paths.get(CONFIG_FILE)), StandardCharsets.UTF_8);
//        String[] fileNames = fileText.split("\n");
//        Calendar.setInst((Calendar) deserializeFrom(fileNames[0].trim()));
//        LoginManager.setInstance((LoginManager) deserializeFrom(fileNames[1].trim()));

        Window window = new Window();
        window.addScreen(new LoginView(window));
        window.start();
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SerializeOnExit.execute(SER_SAVE_FILE_PREFIX);
            }
        });
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

    private static void make24AuctionsSER() {
        LoginManager lm = new LoginManager();
        LoginManager.setInstance(lm);
        for (int i = 0; i < 50; i++) {
            lm.register(new Bidder("bidder" + i, "Bidder" + i + "Name"));
            lm.register(new Staff("staff" + i, "Staff" + i + "Name"));
            lm.register(new Contact("contact" + i, "Contact" + i + "Name", "Nonprofit" + i + "Name"));
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

        java.util.Calendar cc = java.util.Calendar.getInstance();
        cc.setTime(new Date());
        cc.add(java.util.Calendar.YEAR, -2);
        Contact contact2YrsAgo = ((Contact) lm.getUser("contact49"));
        cal.faddAuction(new Auction(contact2YrsAgo, cc.getTime(), null, null));
        Contact contact11MonthsAgo = ((Contact) lm.getUser("contact48"));
        cc.add(java.util.Calendar.YEAR, 1);
        cc.add(java.util.Calendar.MONTH, 1);
        cal.faddAuction(new Auction(contact11MonthsAgo, cc.getTime(), null, null));
    }
}
