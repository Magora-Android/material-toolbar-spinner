package com.sborzenko.materialtoolbarspinner;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    private List<UserGroup> userGroupList = new ArrayList<>();

    private Context context;

    public UserGroupSpinnerAdapter(Context context) {
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
    public View getDropDownView(int position,
                                View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.toolbar_spinner_item_dropdown, parent, false);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.toolbar_spinner_item_actionbar, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            setTriangleAtEnd(viewHolder.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        UserGroup userGroup = getItem(position);
        viewHolder.text.setText(userGroup.getName());

        return convertView;
    }

    private void setTriangleAtEnd(TextView textView) {
        Drawable triangleDrawable
                = AndroidUtils.getTintDrawableByThemeAttr(context,
                R.drawable.spinner_triangle, R.attr.toolbarElementsColor);
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null,
                triangleDrawable, null);

        int spinnerTrianglePadding
                = context.getResources().getDimensionPixelSize(
                R.dimen.toolbar_spinner_triangle_padding);
        textView.setCompoundDrawablePadding(spinnerTrianglePadding);
    }

    static class ViewHolder {
        TextView text;
    }
}