package org.ligi.blueblab.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import org.ligi.axt.simplifications.SimpleTextWatcher;
import org.ligi.blueblab.App;
import org.ligi.blueblab.R;
import org.ligi.blueblab.model.Mood;

public class UserEditActivity extends AppCompatActivity {

    @Bind(R.id.nameInputEditText)
    EditText nameEditText;

    @Bind(R.id.avatarGrid)
    SquareGridByWidthLayout avatarGrid;

    @OnClick(R.id.startFAB)
    void onFABClick() {
        if (nameEditText.getText().toString().isEmpty()) {
            nameEditText.setError("cannot be empty");
        } else {
            AXT.at(this).startCommonIntent().activityFromClass(FindPeerActivity.class);
        }
    }

    List<AvatarController> moodAvatars = new ArrayList<>();
    List<AvatarController> colorAvatars = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final LayoutInflater from = LayoutInflater.from(this);


        int[] faceColors = new int[]{R.color.faceColor1, R.color.faceColor2, R.color.faceColor3};

        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {

                final View avatarView = from.inflate(R.layout.avatar, avatarGrid, false);
                final AvatarController avatarController = new AvatarController(avatarView);

                avatarController.setName("");


                if (y < 2) {
                    moodAvatars.add(avatarController);
                    final Mood mood = Mood.ALL_MOODS[(y * 3 + x) % Mood.ALL_MOODS.length];
                    avatarController.setMood(mood);

                    avatarView.setClickable(true);
                    avatarView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(final View v, final MotionEvent event) {
                            App.userModel.setMood(mood);
                            refresh();
                            return true;
                        }

                    });

                } else {
                    final int color = getResources().getColor(faceColors[x]);

                    avatarView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(final View v, final MotionEvent event) {
                            App.userModel.setColor(color);
                            refresh();
                            return true;
                        }

                    });
                    colorAvatars.add(avatarController);
                    avatarController.setFaceColor(color);
                }

                avatarGrid.addView(avatarView);
            }
        }

        nameEditText.setText(App.userModel.getName());

        nameEditText.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                App.userModel.setName(nameEditText.getText().toString());
                super.onTextChanged(s, start, before, count);
            }
        });
        refresh();

    }

    public void refresh() {
        for (final AvatarController moodAvatar : moodAvatars) {
            final boolean hasFace = moodAvatar.mood == App.userModel.getMood();
            if (hasFace) {
                moodAvatar.setHasFace(true);
                moodAvatar.setFaceColor(App.userModel.getColor());
            } else {
                moodAvatar.setHasFace(false);
            }
        }

        for (final AvatarController colorAvatar : colorAvatars) {
            Mood mood = (colorAvatar.color == App.userModel.getColor()) ? App.userModel.getMood() : null;
            colorAvatar.setMood(mood);
        }


    }


}
