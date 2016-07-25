package com.sborzenko.materialtoolbarspinner;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
public class WidgetToolbarSpinnerActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    private Toolbar toolbar;

    private TextView userGroupTextView;

    private UserGroupToolbarSpinnerAdapter spinnerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_toolbar_spinner);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        userGroupTextView = (TextView) findViewById(R.id.tv_user_group);

        initSpinner();
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
        Toast.makeText(WidgetToolbarSpinnerActivity.this,
                "Nothing is selected", Toast.LENGTH_SHORT).show();
    }
    // endregion

    private void initSpinner() {
        MaterialToolbarSpinner spinner = (MaterialToolbarSpinner)
                toolbar.findViewById(R.id.mt_spinner);

        int width = getResources().getDimensionPixelSize(
                R.dimen.toolbar_spinner_width);
        spinnerAdapter = new UserGroupToolbarSpinnerAdapter(this);
        spinnerAdapter.setUserGroupList(getUserGroupList());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(this);
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
