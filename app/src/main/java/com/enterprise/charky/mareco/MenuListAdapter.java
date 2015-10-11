package com.enterprise.charky.mareco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.enterprise.charky.mareco.irtransmitter.IRProvider;

import java.util.ArrayList;

/**
 * Created by charky on 11.10.15.
 */
public class MenuListAdapter extends BaseAdapter {
    private Context context;

    private ArrayList<IRProvider> mIRProviders;

    public MenuListAdapter(Context pContext, ArrayList<IRProvider> pIRProviders) {
        context = pContext;
        mIRProviders = pIRProviders;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.drawer_list_item, parent, false);

        TextView txtTitle = (TextView) itemView.findViewById(R.id.title);
        TextView txtSubTitle = (TextView) itemView.findViewById(R.id.subtitle);
        ImageView imgIcon = (ImageView) itemView.findViewById(R.id.icon);

        IRProvider pIRProvider = mIRProviders.get(position);

        txtTitle.setText(pIRProvider.irpTitle);
        txtSubTitle.setText(pIRProvider.irpSubTitle);
        imgIcon.setImageResource(pIRProvider.irpIcon);

        return itemView;
    }

    @Override
    public int getCount() {
        return mIRProviders.size();
    }

    @Override
    public IRProvider getItem(int position) {
        return mIRProviders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}