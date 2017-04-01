package com.mindtree.fsmmindtree.common;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
/**
 * Created by sunilbagunji on 08/12/15.
 */
public class BaseFragment extends Fragment {
    private ProgressDialog pd;

    public void showProgress(String title, String message) {
        pd = new ProgressDialog(getActivity());
        pd.setTitle(title);
        pd.setMessage(message);
        pd.show();
    }

    public void hideProgress() {
        if (pd != null) {
            pd.dismiss();
        }
    }
}
