package com.mindtree.fsmmindtree;
import com.mindtree.fsmmindtree.data.LoginResponseObject;
import com.mindtree.fsmmindtree.presenter.LoginPresenter;

import org.junit.Test;
import org.mockito.*;

import de.greenrobot.event.EventBus;

/**
 * Created by M1036063 on 4/1/2017.
 */

public class Apptest3 {

   @Test
    public void logInUserTestHappyFlow()
    {
        LoginPresenter mockLogInPresenter=Mockito.mock(LoginPresenter.class);
        LoginResponseObject responseObject = Mockito.mock(LoginResponseObject.class);
        Mockito.doReturn(true).when(mockLogInPresenter).validateInputs();
        EventBus mockEventBus=Mockito.mock(EventBus.class);
        Mockito.doNothing().when(responseObject).setAccesstoken(null);
        Mockito.doNothing().when(mockEventBus).post(responseObject);
        Mockito.doCallRealMethod().when(mockLogInPresenter).loginUser();


    }
    @Test
    public void logInUserTestApiFlow()
    {
        LoginPresenter mockLogInPresenter=Mockito.mock(LoginPresenter.class);
        Mockito.doReturn(false).when(mockLogInPresenter).validateInputs();
        Mockito.doNothing().when(mockLogInPresenter).callApi();

        Mockito.doCallRealMethod().when(mockLogInPresenter).loginUser();
        mockLogInPresenter.loginUser();

    }






}
