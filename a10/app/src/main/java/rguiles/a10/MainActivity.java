package rguiles.a10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

/**
 * Main Driver of To-Do List Application
 * @author Richard Guiles
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements TextView.OnEditorActionListener {

    private ListView listView;
    private EditText input;
    private TabLayout tabs;
    private ArrayList<ListItem> items;
    private ArrayAdapter<ListItem> itemsAdapter;
    private String selectedCollection = "Todo";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    /**
     * Creates Activity upon launch of application
     * @param savedInstanceState previous settings of app launch
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvItems);
        items = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<>(this, R.layout.list_item_layout, items);
        listView.setAdapter(itemsAdapter);
        setupLongClickHandler();

        input = findViewById(R.id.etInput);
        input.setOnEditorActionListener(this);

        tabs = findViewById(R.id.tabLayout);
        setupTabClickListener();

        updateList();
    }

    /**
     * Retrieves stored list in the database
     */
    private void updateList() {
        showToast("Getting List", Toast.LENGTH_SHORT);
        Database.getList(db, selectedCollection, items, itemsAdapter);
    }

    /**
     * Updates list whenever a different tab is selected
     */
    private void setupTabClickListener() {
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Clear Items
                items.clear();

                // Set Selected Collection
                selectedCollection = tab.getText().toString();

                // Load Items from Collection
                updateList();

                // Notify Data Changed
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab){}

            @Override
            public void onTabReselected(TabLayout.Tab tab){}
        });
    }

    /**
     * Deletes list item and updates db on held click
     */
    private void setupLongClickHandler() {
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            ListItem tmpItem = items.remove(position);
            itemsAdapter.notifyDataSetChanged();
            Database.removeItem(db, selectedCollection, items, itemsAdapter, tmpItem);
            showToast("Item Removed", Toast.LENGTH_SHORT);
            return true;
        });
    }

    /**
     * Displays a 'toast' message on screen to make app more responsive
     * @param message string passed from function call
     * @param length amount of time message stays on screen
     */
    private void showToast(String message, int length) {
        Toast toast = Toast.makeText(this, message, length);
        toast.setGravity(Gravity.CENTER, 0, -30);
        toast.show();
    }

    /**
     * Controls user input, stores item in db and list
     * @param v textbox field
     * @param actionId id of v
     * @param event registers event action
     * @return bool of action instance
     */
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(event == null || event.getAction() == KeyEvent.ACTION_UP) {
            ListItem tmpItem = new ListItem(input.getText().toString());
            items.add(tmpItem);
            input.setText("");
            itemsAdapter.notifyDataSetChanged();
            Database.add(db, selectedCollection, tmpItem);
            showToast("Item Added", Toast.LENGTH_SHORT);
        }
        return true;
    }
}