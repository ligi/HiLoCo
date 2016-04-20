package org.ligi.blueblab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import org.ligi.axt.AXT;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.nameInputEditText)
    EditText nameEditText;

    @Bind(R.id.avatarGrid)
    SquareGridByWidthLayout avatarGrid;

    @OnClick(R.id.startFAB)
    void onFABClick() {
        App.userModel.name = nameEditText.getText().toString();
        AXT.at(this).startCommonIntent().activityFromClass(FindPeerActivity.class);
    }

    List<AvatarController> moodAvatars = new ArrayList<>();
    List<AvatarController> colorAvatars = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final LayoutInflater from = LayoutInflater.from(this);

        Mood[] moods = new Mood[]{Mood.NEUTRAL, Mood.AMUSED, Mood.CALM, Mood.CHATTY, Mood.SAD, Mood.ANGRY};
        int[] faceColors = new int[]{R.color.faceColor1, R.color.faceColor2, R.color.faceColor3};

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                final View avatarView = from.inflate(R.layout.avatar, avatarGrid, false);
                final AvatarController avatarController = new AvatarController(avatarView);

                avatarController.setName("");


                if (y < 2) {
                    moodAvatars.add(avatarController);
                    final Mood mood = moods[(y * 3 + x) % moods.length];
                    avatarController.setMood(mood);
                    avatarController.setHasFace((mood == App.userModel.mood));
                    avatarController.setFaceColor(App.userModel.color);

                    avatarView.setClickable(true);
                    avatarView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(final View v, final MotionEvent event) {
                            App.userModel.mood = mood;
                            refresh();
                            return true;
                        }

                    });

                } else {
                    final int color = getResources().getColor(faceColors[x]);

                    avatarView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(final View v, final MotionEvent event) {
                            App.userModel.color = color;
                            refresh();
                            return true;
                        }

                    });
                    colorAvatars.add(avatarController);

                    avatarController.setFaceColor(color);
                    avatarController.setMood(null);
                }

                avatarGrid.addView(avatarView);
            }
        }
    }

    public void refresh() {
        for (final AvatarController moodAvatar : moodAvatars) {
            final boolean hasFace = moodAvatar.mood == App.userModel.mood;
            if (hasFace) {
                moodAvatar.setHasFace(true);
                moodAvatar.setFaceColor(App.userModel.color);
            } else {
                moodAvatar.setHasFace(false);
            }
        }
    }


}
