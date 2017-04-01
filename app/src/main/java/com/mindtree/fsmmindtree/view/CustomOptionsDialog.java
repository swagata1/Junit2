package com.mindtree.fsmmindtree.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mindtree.fsmmindtree.R;
import com.mindtree.fsmmindtree.common.AppConstants;
import com.mindtree.fsmmindtree.common.AppUtils;

import java.util.ArrayList;

/**
 * Created by sunilbagunji on 28/12/15.
 */
public class CustomOptionsDialog {
    private TextView txtDialogTitle;
    private RadioButton radioOption1;
    private RadioButton radioOption2;
    private RadioButton radioOption3;
    private RadioButton radioOption4;


    private Context mContext;
    private Dialog dialog;


    public CustomOptionsDialog(Context context) {
        mContext = context;

        // Create custom dialog object
        dialog = new Dialog(context);

        // Include dialog.xml file
        dialog.setContentView(R.layout.custom_options_dialog);

        txtDialogTitle = (TextView) dialog.findViewById(R.id.txtDialogTitle);
        txtDialogTitle.setTypeface(AppUtils.getCustomFont(context, AppConstants.Fonts.FONT_BOLD));

        radioOption1 = (RadioButton) dialog.findViewById(R.id.radioOption1);
        radioOption2 = (RadioButton) dialog.findViewById(R.id.radioOption2);
        radioOption3 = (RadioButton) dialog.findViewById(R.id.radioOption3);
        radioOption4 = (RadioButton) dialog.findViewById(R.id.radioOption4);

        radioOption1.setTypeface(AppUtils.getCustomFont(context, AppConstants.Fonts.FONT_NORMAL));
        radioOption2.setTypeface(AppUtils.getCustomFont(context, AppConstants.Fonts.FONT_NORMAL));
        radioOption3.setTypeface(AppUtils.getCustomFont(context, AppConstants.Fonts.FONT_NORMAL));
        radioOption4.setTypeface(AppUtils.getCustomFont(context, AppConstants.Fonts.FONT_NORMAL));
    }

    public void setTitle(String title) {
        txtDialogTitle.setText(title);
    }

    public void setOptions(ArrayList<String> options) {
        radioOption1.setText(options.get(0));
        radioOption2.setText(options.get(1));

        if (options.size() < 3) {
            radioOption3.setVisibility(View.GONE);
        } else {
            radioOption3.setText(options.get(2));
        }

        if (options.size() < 4) {
            radioOption4.setVisibility(View.GONE);
        } else {
            radioOption4.setText(options.get(3));
        }
    }

    public void showDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void setOption1Listener(OnCheckedChangeListener listener) {
        radioOption1.setOnCheckedChangeListener(listener);
    }

    public void setOption2Listener(OnCheckedChangeListener listener) {
        radioOption2.setOnCheckedChangeListener(listener);
    }

    public void setOption3Listener(OnCheckedChangeListener listener) {
        radioOption3.setOnCheckedChangeListener(listener);
    }

    public void setOption4Listener(OnCheckedChangeListener listener) {
        radioOption4.setOnCheckedChangeListener(listener);
    }

    public void setOption(int option) {
        switch(option) {
            case AppConstants.SORT_DATE:
                radioOption1.setChecked(true);
                break;

            case AppConstants.SORT_TYPE:
                radioOption2.setChecked(true);
                break;

            case AppConstants.SORT_PRIORITY:
                radioOption3.setChecked(true);
                break;

            case AppConstants.SORT_STATUS:
                radioOption4.setChecked(true);
                break;
        }
    }
}
