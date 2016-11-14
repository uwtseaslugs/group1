package auctioncentral;

import auctioncentral.model.*;
import auctioncentral.view.login.*;

import java.io.*;

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
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.add(java.util.Calendar.DATE, 13);
        for (int i = 0; i < 12; i++) {
            c.add(java.util.Calendar.DATE, 1);
            calendar.addAuction(new Auction(new Contact("" + i, "" + i), c.getTime(), null, null));

        }
    }
}
