package com.mindtree.fsmmindtree.presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.mindtree.fsmmindtree.data.technician.TechnicianResponseObject;

import de.greenrobot.event.EventBus;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sunilbagunji on 08/12/15.
 */
public class TechnicianPresenter {
    private TechnicianService mTechnicianService;
    private EventBus bus = EventBus.getDefault();
    private Context mContext;
    private ProgressDialog pd;

    public TechnicianPresenter(Context context) {
        mContext = context;

        mTechnicianService = new TechnicianService();
    }

        public void getTechnicianList() {
//            showProgress();

            callApi();
        }

    public void callApi() {
        mTechnicianService.getApi()
                .getTechnicianList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TechnicianResponseObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
//                        hideProgress();
//                        bus.post(null);
                    }

                    @Override
                    public void onNext(TechnicianResponseObject technicianResponseObject) {
//                        hideProgress();

                        bus.post(technicianResponseObject);
                    }
                });
    }

//    public void showProgress() {
//        pd = new ProgressDialog(mContext);
//        pd.setTitle("Login");
//        pd.setMessage("Loading...");
//        pd.show();;
//    }
//
//    public void hideProgress() {
//        if(pd != null) {
//            pd.hide();
//        }
//    }
}

