package org.ligi.blueblab;

import android.app.Application;
import android.support.v4.content.ContextCompat;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        userModel = new UserModel();
        userModel.color = ContextCompat.getColor(this, R.color.faceColor1);
    }

    public static UserModel userModel;

}
