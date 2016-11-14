package model;


/**
 * Created by Bernd on 29-10-2016
 */
public class TableViewItem {

    /**
     * The Selected.
     */
    public boolean selected;

    /**
     * The Id.
     */
    public int id;

    /**
     * Set selected.
     *
     * @param selected the selected
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    /**
     * Set id.
     *
     * @param id the id
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Get selected boolean.
     *
     * @return the boolean
     */
    public boolean getSelected(){
        return this.selected;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }


}
