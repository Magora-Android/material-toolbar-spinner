package com.sborzenko.materialtoolbarspinner;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.magorasystems.materialtoolbarspinner.MaterialToolbarSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at Magora Systems (http://magora-systems.com) on 20.07.16
 *
 * @author Stanislav S. Borzenko
 */
public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener,
        View.OnClickListener,
        SearchView.OnCloseListener {
    private Toolbar toolbar;

    private MaterialToolbarSpinner spinner;

    private TextView userGroupTextView;

    private UserGroupToolbarSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initSpinner();
        initSearchView();

        userGroupTextView = (TextView) findViewById(R.id.tv_user_group);
    }

    // region AdapterView.OnItemSelectedListener (Spinner item selected)
    @Override
    public void onItemSelected(AdapterView<?> parent,
                               View view, int position, long id) {
        UserGroup userGroup = spinnerAdapter.getItem(position);
        userGroupTextView.setText(userGroup.getName());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(MainActivity.this,
                "Nothing is selected", Toast.LENGTH_SHORT).show();
    }
    // endregion

    // region View.OnClickListener
    @Override
    public void onClick(View view) {
        spinner.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this, "Open search",
                Toast.LENGTH_SHORT).show();

        toolbar.getMenu().findItem(R.id.action_like).setVisible(false);
    }
    // endregion

    // region SearchView.OnCloseListener
    @Override
    public boolean onClose() {
        spinner.setVisibility(View.VISIBLE);
        Toast.makeText(MainActivity.this, "Close search",
                Toast.LENGTH_SHORT).show();

        toolbar.getMenu().findItem(R.id.action_like).setVisible(true);

        return false;
    }
    // endregion

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.search);
        Drawable thumbUpDrawable
                = AndroidUtils.getTintDrawableByThemeAttr(toolbar.getContext(),
                R.drawable.ic_thumb_up, R.attr.colorControlNormal);

        toolbar.getMenu().findItem(R.id.action_like)
                .setIcon(thumbUpDrawable);
    }

    private void initSpinner() {
        spinner = (MaterialToolbarSpinner)
                toolbar.findViewById(R.id.mt_spinner);

        spinnerAdapter = new UserGroupToolbarSpinnerAdapter(this);
        spinnerAdapter.setUserGroupList(getUserGroupList());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void initSearchView() {
        SearchView searchView
                = (SearchView) MenuItemCompat.getActionView(
                toolbar.getMenu().findItem(R.id.action_search));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search");
        searchView.setOnSearchClickListener(this);
        searchView.setOnCloseListener(this);
    }

    private List<UserGroup> getUserGroupList() {
        List<UserGroup> userGroupList = new ArrayList<>();

        UserGroup userGroup1 = new UserGroup();
        userGroup1.setName("Suggestions");
        userGroupList.add(userGroup1);

        UserGroup userGroup2 = new UserGroup();
        userGroup2.setName("Followers");
        userGroupList.add(userGroup2);

        UserGroup userGroup3 = new UserGroup();
        userGroup3.setName("Following");
        userGroupList.add(userGroup3);

        return userGroupList;
    }
}
