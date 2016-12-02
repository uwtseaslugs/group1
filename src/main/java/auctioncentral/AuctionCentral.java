package auctioncentral;

import auctioncentral.gui.contact.ContactAddItemView;
import auctioncentral.gui.contact.ContactEditAuctionView;
import auctioncentral.model.*;
import auctioncentral.model.Calendar;
import auctioncentral.gui.*;

import java.io.*;
import java.util.*;

public class AuctionCentral implements Serializable {

    public static void main(String[] args) {
        LoginManager.getInstance().register(new Bidder("bidder1", "Bidder1 Name"));
        addAuctions();
        new Window(new ContactAddItemView(null)).start();
        serializeTo(new AuctionCentral(), "last.ser");
    }

    private ILoginManager myLoginManager;
    private ICalendar myCalendar;

    public AuctionCentral() {
        myLoginManager = LoginManager.getInstance();
        myCalendar = Calendar.inst();
    }

    public ILoginManager getLoginManager() {
        return myLoginManager;
    }

    public ICalendar getCalendar() {
        return myCalendar;
    }

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

    public static ILoginManager loginManager = new LoginManager();

    private static void add25Auctions() {
        Random r = new Random();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        for (int i = 0; i < 25; i++) {
            Auction a = new Auction(new Contact("user" + i, "name" + i, "Nonprofit" + i),
                    c.getTime(), null, null);
            int itemsCount = r.nextInt(9) + 2;
            Calendar.inst().addAuction(a);
            for (int j = 0; j < itemsCount; j++) {
                a.addItem(new Item("item" + j, ItemCondition.values()[r.nextInt(ItemCondition.values().length - 1)],
                        ItemSize.SMALL, r.nextInt(201) + 1,
                        "donor" + j, "description" + j, "comment" + j));
            }
            if (i % 2 == 0) {
                c.add(java.util.Calendar.DATE, 1);
            }
        }
    }

    // test
    private static void addAuctions() {
        Random r = new Random();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        int auctionCount = 0;
        while (auctionCount <= 6) {
            c.add(java.util.Calendar.DATE, 1);
            c.add(java.util.Calendar.DATE, r.nextInt(3));
            Auction a = new Auction(new Contact("user" + auctionCount, "name" + auctionCount, "Nonprofit" + auctionCount),
                    c.getTime(), null, null);
            if (Calendar.inst().canAddAuction(a)) {
                auctionCount++;
                Calendar.inst().addAuction(a);
                int itemsCount = r.nextInt(9) + 2;
                for (int i = 0; i < itemsCount; i++) {
                    a.addItem(new Item("item" + i, ItemCondition.values()[r.nextInt(ItemCondition.values().length - 1)],
                            ItemSize.SMALL, r.nextInt(201) + 1,
                            "donor" + i, "description" + i, "comment" + i));
                }
            }
        }
    }
}
