package com.enterprise.charky.mareco.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enterprise.charky.mareco.R;


import java.util.ArrayList;

/**
 * Created by charky on 11.10.15.
 */
public class NavigationListAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<NavigationListItem> mNavigationListItem;

    public NavigationListAdapter(Context pContext, ArrayList<NavigationListItem> pNavigationListItem) {
        context = pContext;
        mNavigationListItem = pNavigationListItem;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        NavigationListItem pNavigationListItem = mNavigationListItem.get(position);


        View itemView = inflater.inflate(pNavigationListItem.LayoutID, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

        txtTitle.setText(pNavigationListItem.LabelText);
        imgIcon.setImageResource(pNavigationListItem.IconID);

        return itemView;
    }

    @Override
    public int getCount() {
        return mNavigationListItem.size();
    }

    @Override
    public NavigationListItem getItem(int position) {
        return mNavigationListItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}