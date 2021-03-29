package rguiles.a10;

import androidx.annotation.NonNull;
import java.io.Serializable;

/**
 * Class controlling item entrance into db
 */
public class ListItem implements Serializable {
    private long dttm;
    private String item;

    /**
     * Default constructor
     * @param item string of user input
     */
    public ListItem(String item) {
        this.item = item;
        dttm = System.nanoTime();
    }

    /**
     * Constructor that also passes date and time
     * @param dttm Date and Time
     * @param item string of user input
     */
    public ListItem(long dttm, String item) {
        this.dttm = dttm;
        this.item = item;
    }

    /**
     * Return overide item
     * @return item of user input
     */
    @NonNull
    @Override
    public String toString(){
        return item;
    }

    /**
     * Return date and time
     * @return dttm
     */
    public long getDttm() {
        return dttm;
    }

    /**
     * Return item
     * @return user input
     */
    public String getItem() {
        return item;
    }
}
