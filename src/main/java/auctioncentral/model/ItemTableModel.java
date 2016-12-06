package auctioncentral.model;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ItemTableModel extends AbstractTableModel {
    private List<Item> itemList;
    private String[] colNames;
    private Object[][] data;

    public ItemTableModel(Auction auction){
        colNames = new String[]{"Item Name", "Minimum Bid", "Item Condition"};
        itemList = auction.getItems();
        data = new Object[itemList.size()][colNames.length];
        tableInit();
    }

    private void tableInit(){
        Item item;

        for (int i = 0; i < itemList.size(); i++){
            item = itemList.get(i);
            data[i][0] = item.getName();
            data[i][1] = item.getMinimumBid();
            data[i][2] = item.getCondition();
        }
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    public String getColumnName(int col) {
        return colNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

}
