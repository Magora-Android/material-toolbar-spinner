package com.sborzenko.materialtoolbarspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at Magora Systems (http://magora-systems.com) on 08.07.16
 *
 * @author Stanislav S. Borzenko
 */
public class UserGroupSpinnerAdapter extends BaseAdapter {
    private List<UserGroup> mItems = new ArrayList<>();

    private Context context;
    private Context toolbarContext;

    public UserGroupSpinnerAdapter(Context context, Context toolbarContext) {
        this.context = context;
        this.toolbarContext = toolbarContext;
    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(UserGroup userGroup) {
        mItems.add(userGroup);
    }

    public void addItems(List<UserGroup> userGroupList) {
        mItems.addAll(userGroupList);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = LayoutInflater.from(toolbarContext)
                    .inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.toolbar_spinner_item_actionbar, parent, false);
            view.setTag("NON_DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getTitle(position));

        return view;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position).getName() : "";
    }
}