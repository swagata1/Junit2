package com.mindtree.fsmmindtree.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;
import com.mindtree.fsmmindtree.data.technician.Result;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TechnicianAdapter extends ArrayAdapter<Result> {
    private Context mContext;
    private ArrayList<Result> mTechnicianList;

    public TechnicianAdapter(Context context, ArrayList<Result> technicianList) {
        super(context, 0, technicianList);

        mContext = context;
        mTechnicianList = technicianList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder technicianViewHolder = null;

        Result task = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_technician, parent, false);

            technicianViewHolder = new ViewHolder(convertView);
            technicianViewHolder.txtTechnicianName.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));
            technicianViewHolder.txtTechnicianSkill.setTypeface(AppUtils.getCustomFont(mContext, AppConstants.Fonts.FONT_NORMAL));

            convertView.setTag(technicianViewHolder);
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            technicianViewHolder = (ViewHolder) convertView.getTag();
        }

        technicianViewHolder.txtTechnicianName.setText(mTechnicianList.get(position).getUsername());

        return convertView;
    }

    /**
     * This class contains all butterknife-injected Views & Layouts from layout file 'row_technician.xml'
     * for easy to all layout elements.
     *
     * @author ButterKnifeZelezny, plugin for Android Studio by Avast Developers (http://github.com/avast)
     */
    static class ViewHolder {
        @Bind(R.id.imgSupervisor)
        ImageView imgSupervisor;
        @Bind(R.id.txtTechnicianName)
        TextView txtTechnicianName;
        @Bind(R.id.txtTechnicianSkill)
        TextView txtTechnicianSkill;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
