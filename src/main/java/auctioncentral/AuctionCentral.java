package auctioncentral;

import auctioncentral.model.*;
import auctioncentral.model.Calendar;
import auctioncentral.view.login.*;

import java.io.*;
import java.util.*;

public class AuctionCentral {

    public static ILoginManager loginManager = new LoginManager();
    public static ICalendar calendar = new Calendar();

    public static void main(String[] args) {
//        calendar = (ICalendar) deserializeFrom("cal.ser");
        addAuctions();
        new LoginUsernameMenu().show();
        serializeTo(calendar, "cal.ser");
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

    // test
    private static void addAuctions() {
        Random r = new Random();
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 8);
        int auctionCount = 0;
        while (auctionCount <= 6) {
            c.add(java.util.Calendar.DATE, 1);
            switch (r.nextInt(3)) {
                case 0:
                    c.add(java.util.Calendar.DATE, 1);
                    break;
                case 1:
                    c.add(java.util.Calendar.DATE, 2);
                    break;
                default:
                    break;
            }
            Auction a = new Auction(new Contact("user" + auctionCount, "name" + auctionCount, "Nonprofit" + auctionCount),
                    c.getTime(), null, null);
            if (calendar.canAddAuction(a)) {
                auctionCount++;
                calendar.addAuction(a);
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
