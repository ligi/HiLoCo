package org.ligi.blueblab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;

public class FindPeerActivity extends AppCompatActivity {

    @Bind(R.id.gridLayout)
    SquareGridByWidthLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.find_peers);

        ButterKnife.bind(this);

        final LayoutInflater from = LayoutInflater.from(this);

        for (int i = 0; i < 25; i++) {
            final View inflate = from.inflate(R.layout.avatar, gridLayout, true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
