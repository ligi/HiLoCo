package org.ligi.blueblab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.ligi.axt.AXT;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.startFAB)
    void onFABClick() {
        AXT.at(this).startCommonIntent().activityFromClass(FindPeerActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

}
