package com.sborzenko.materialtoolbarspinner;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener,
        View.OnClickListener,
        SearchView.OnCloseListener {
    private Toolbar toolbar;

    private Spinner spinner;

    private TextView userGroupTextView;

    private UserGroupSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initSpinner();
        initSearchView();

        userGroupTextView = (TextView) findViewById(R.id.tv_user_group);
    }

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
        spinner = (Spinner) toolbar.findViewById(R.id.toolbar_spinner);

        int dropDownVerticalOffset;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            dropDownVerticalOffset = getResources().getDimensionPixelSize(
                    R.dimen.dropdown_vertical_offset_pre_lollipop);
        } else {
            dropDownVerticalOffset = getResources().getDimensionPixelSize(
                    R.dimen.dropdown_vertical_offset_post_lollipop);
        }
        spinner.setDropDownVerticalOffset(dropDownVerticalOffset);

        int width = getResources().getDimensionPixelSize(
                R.dimen.toolbar_spinner_width);
        spinnerAdapter = new UserGroupSpinnerAdapter(this, width);
        spinnerAdapter.setUserGroupList(getUserGroupList());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
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
