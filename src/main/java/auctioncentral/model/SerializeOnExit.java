package auctioncentral.model;

import java.util.List;
import java.util.ArrayList;
//import java.io.Serializable;
import java.io.*;

public abstract class SerializeOnExit implements Serializable {
    private static final List<SerializeOnExit> theList =
        new ArrayList<SerializeOnExit>();

    public SerializeOnExit() {
        theList.add(this);
    }

    public abstract String getSerializedName();

    public static void execute(String prefix) {
        for (SerializeOnExit element : theList) {
            String fname = prefix + "_" + element.getSerializedName() + ".ser";
            try {
                FileOutputStream fos = new FileOutputStream(fname);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(element);
                oos.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}