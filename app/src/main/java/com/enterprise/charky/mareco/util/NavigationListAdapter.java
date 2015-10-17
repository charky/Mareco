package com.enterprise.charky.mareco.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enterprise.charky.mareco.R;
import com.enterprise.charky.mareco.irtransmitter.IRCodes;

import java.util.ArrayList;

/**
 * Created by charky on 11.10.15.
 */
public class NavigationListAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<IRCodes> mIRCodes;

    public NavigationListAdapter(Context pContext, ArrayList<IRCodes> pIRCodes) {
        context = pContext;
        mIRCodes = pIRCodes;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

        IRCodes pIRCodes = mIRCodes.get(position);
        txtTitle.setText(pIRCodes.getTitle());
        imgIcon.setImageResource(pIRCodes.getIcon());

        return itemView;
    }

    @Override
    public int getCount() {
        return mIRCodes.size();
    }

    @Override
    public IRCodes getItem(int position) {
        return mIRCodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}