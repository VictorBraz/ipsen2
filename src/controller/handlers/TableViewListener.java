package controller.handlers;

import java.util.ArrayList;


public interface TableViewListener {
    /**
     * Sets selected rows.
     *
     * @param selectedRows the selected rows
     */
    void setSelectedRows(ArrayList selectedRows);

    /**
     * Sets selected item.
     *
     * @param selectedItemId the selected item id
     */
    void setSelectedItem(int selectedItemId);


}