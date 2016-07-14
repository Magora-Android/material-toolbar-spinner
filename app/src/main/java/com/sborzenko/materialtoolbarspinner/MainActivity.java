package com.sborzenko.materialtoolbarspinner;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();

        final View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner,
                toolbar, false);
        Toolbar.LayoutParams lp = new Toolbar.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.MATCH_PARENT);

        toolbar.addView(spinnerContainer, lp);


        SearchView searchView
                = (SearchView) MenuItemCompat.getActionView(
                toolbar.getMenu().findItem(R.id.action_search));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search");
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerContainer.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();

                toolbar.getMenu().findItem(R.id.action_like).setVisible(false);
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                spinnerContainer.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "close search", Toast.LENGTH_SHORT).show();

                toolbar.getMenu().findItem(R.id.action_like).setVisible(true);

                return false;
            }
        });

        /*FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        frameLayout.addView(spinnerContainer, lp2);*/

        UserGroupSpinnerAdapter spinnerAdapter = new UserGroupSpinnerAdapter(toolbar.getContext(), this);
        spinnerAdapter.addItems(getUserGroupList());

        Spinner spinner = (Spinner) spinnerContainer.findViewById(R.id.toolbar_spinner);
        int dropDownVerticalOffset;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            dropDownVerticalOffset = getResources().getDimensionPixelSize(R.dimen.dropdown_vertical_offset_pre_lollipop);
        } else {
            dropDownVerticalOffset = getResources().getDimensionPixelSize(R.dimen.dropdown_vertical_offset_post_lollipop);
        }
        spinner.setDropDownVerticalOffset(dropDownVerticalOffset);
        spinner.setAdapter(spinnerAdapter);

        /*Spinner mySpinner = (Spinner) findViewById(R.id.my_spinner);
        UserGroupSpinnerAdapter mySpinnerAdapter = new UserGroupSpinnerAdapter(this);
        mySpinnerAdapter.addItems(getUserGroupList());
        mySpinner.setAdapter(mySpinnerAdapter);*/
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
