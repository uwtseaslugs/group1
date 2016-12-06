package auctioncentral.model;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public abstract class SerializeOnExit implements Serializable {
    private static final List<SerializeOnExit> theList =
        new ArrayList<SerializeOnExit>();

    public SerializeOnExit() {
        theList.add(this);
    }

    public abstract String getSerializedName();

    public void add() {
        if (!theList.contains(this)) {
            theList.add(this);
        }

    }

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
