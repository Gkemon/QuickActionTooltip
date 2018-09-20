package gkemon.binarygeek.com.quickactiontooltip;

import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import me.piruin.quickaction.ActionItem;
import me.piruin.quickaction.QuickAction;

public class MainActivity extends AppCompatActivity {

    private QuickAction quickAction;
    private QuickAction quickIntent;
    private static final int ID_UP = 1;
    private static final int ID_DOWN = 2;
    private static final int ID_SEARCH = 3;
    private static final int ID_INFO = 4;
    private static final int ID_ERASE = 5;
    private static final int ID_OK = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Config default color
        QuickAction.setDefaultColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, null));
        QuickAction.setDefaultTextColor(Color.BLACK);

        ActionItem nextItem = new ActionItem(ID_DOWN, "Next", R.drawable.ic_arrow_back_black_24dp);
        ActionItem prevItem = new ActionItem(ID_UP, "Prev", R.drawable.ic_arrow_back_black_24dp);
        ActionItem searchItem = new ActionItem(ID_SEARCH, "Find", R.drawable.ic_arrow_back_black_24dp);
        ActionItem infoItem = new ActionItem(ID_INFO, "Info", R.drawable.ic_arrow_back_black_24dp);
        ActionItem eraseItem = new ActionItem(ID_ERASE, "Clear", R.drawable.ic_arrow_back_black_24dp);
        ActionItem okItem = new ActionItem(ID_OK, "OK", R.drawable.ic_arrow_back_black_24dp);

        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        prevItem.setSticky(true);
        nextItem.setSticky(true);

        //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
        //orientation
        quickAction = new QuickAction(this, QuickAction.HORIZONTAL);
        quickAction.setColorRes(R.color.colorPrimary);
        quickAction.setTextColorRes(R.color.colorWhite);

        //set divider with color
        //quickAction.setDividerColor(ContextCompat.getColor(this, R.color.white));
        //

        //set enable divider default is disable for vertical
        //quickAction.setEnabledDivider(true);
        //Note this must be called before addActionItem()

        //add action items into QuickAction
        quickAction.addActionItem(nextItem, prevItem);
        quickAction.setTextColor(Color.YELLOW);
        quickAction.addActionItem(searchItem);
        quickAction.addActionItem(infoItem);
        quickAction.addActionItem(eraseItem);
        quickAction.addActionItem(okItem);

        //Set listener for action item clicked
        quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
            @Override public void onItemClick(ActionItem item) {
                //here we can filter which action item was clicked with pos or actionId parameter
                String title = item.getTitle();
                Toast.makeText(MainActivity.this, title+" selected", Toast.LENGTH_SHORT).show();
                if (!item.isSticky()) quickAction.remove(item);
            }
        });

        //set listener for on dismiss event, this listener will be called only if QuickAction dialog
        // was dismissed
        //by clicking the area outside the dialog.
        quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
            @Override public void onDismiss() {
                Toast.makeText(MainActivity.this, "Dismissed", Toast.LENGTH_SHORT).show();
            }
        });



        findViewById(R.id.action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quickAction.show(view);
            }
        });

    }
}
