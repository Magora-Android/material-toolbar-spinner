package com.sborzenko.materialtoolbarspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magorasystems.materialtoolbarspinner.MaterialToolbarSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created at Magora Systems (http://magora-systems.com) on 20.07.16
 *
 * @author Stanislav S. Borzenko
 */
public class UserGroupToolbarSpinnerAdapter extends MaterialToolbarSpinner.Adapter {
    private List<UserGroup> userGroupList = new ArrayList<>();

    private Context context;

    public UserGroupToolbarSpinnerAdapter(Context context) {
        this.context = context;
    }

    public void setUserGroupList(List<UserGroup> userGroupList) {
        this.userGroupList = userGroupList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return userGroupList.size();
    }

    @Override
    public UserGroup getItem(int position) {
        return userGroupList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDownView(int position,
                                View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_mts_dropdown, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserGroup userGroup = getItem(position);
        viewHolder.text.setText(userGroup.getName());

        return convertView;
    }

    @Override
    public View getToolbarView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_mts_toolbar, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserGroup userGroup = getItem(position);
        viewHolder.text.setText(userGroup.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView text;
    }
}
