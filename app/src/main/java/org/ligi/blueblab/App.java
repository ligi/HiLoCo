package org.ligi.blueblab;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        userModel = new UserModel();
    }

    public static UserModel userModel;

}
