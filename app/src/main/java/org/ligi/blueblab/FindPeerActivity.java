package org.ligi.blueblab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.Random;

public class FindPeerActivity extends AppCompatActivity {

    public static final int SIZE = 5;
    @Bind(R.id.gridLayout)
    SquareGridByWidthLayout gridLayout;

    private class AvatarView {
        private final View rootView;

        private AvatarView(final View rootView) {
            this.rootView = rootView;
        }
    }

    private AvatarView myAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.find_peers);

        ButterKnife.bind(this);

        final LayoutInflater from = LayoutInflater.from(this);
        final Random random = new Random();
        final int MIDDLE = (SIZE / 2) ;


        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                final View inflate = from.inflate(R.layout.avatar, gridLayout, false);

                gridLayout.addView(inflate);
                final AvatarView avatarView = new AvatarView(inflate);

                final boolean b = random.nextInt() % 10 != 5;

                if (x == MIDDLE && y == MIDDLE) {
                    myAvatar = avatarView;
                } else if (b) {
                    avatarView.rootView.setVisibility(View.INVISIBLE);
                }
            }
        }

        myAvatar.rootView.setVisibility(View.VISIBLE);
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
