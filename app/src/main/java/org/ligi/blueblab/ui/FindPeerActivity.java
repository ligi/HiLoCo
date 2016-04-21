package org.ligi.blueblab.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.ligi.axt.AXT;
import org.ligi.blueblab.App;
import org.ligi.blueblab.R;
import org.ligi.blueblab.model.Mood;
import org.ligi.blueblab.model.User;

public class FindPeerActivity extends AppCompatActivity {

    private int currentBG=0;
    public static final int SIZE = 5;
    public static final int CENTER = SIZE / 2;

    @Bind(R.id.gridLayout)
    SquareGridByWidthLayout gridLayout;

    @Bind(R.id.historyContainer)
    ViewGroup historyAvatarContainer;

    private AvatarController myAvatarController;

    private class AvatarControllerWithUser extends AvatarController {

        public User user;

        AvatarControllerWithUser(final View rootView) {
            super(rootView);
        }
    }

    private AvatarControllerWithUser[][] avatarControllers;
    int[] bgs=new int[]{R.drawable.bg1,R.drawable.bg2,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5,R.drawable.bg6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.find_peers);

        ButterKnife.bind(this);

        final LayoutInflater from = LayoutInflater.from(this);

        avatarControllers = new AvatarControllerWithUser[SIZE][SIZE];

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                final View avatarView = from.inflate(R.layout.avatar, gridLayout, false);

                gridLayout.addView(avatarView);
                final AvatarControllerWithUser avatarController = new AvatarControllerWithUser(avatarView);

                avatarControllers[x][y] = avatarController;

                if (x == CENTER && y == CENTER) {
                    myAvatarController = avatarController;
                    avatarController.setFaceColor(App.userModel.getColor());
                    avatarController.setMood(App.userModel.getMood());
                } else {
                    avatarController.rootView.setVisibility(View.INVISIBLE);
                }

                avatarView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(final View v, final MotionEvent event) {
                        App.chatPartner = avatarController.user;
                        AXT.at(FindPeerActivity.this).startCommonIntent().activityFromClass(ChatActivity.class);
                        return false;
                    }
                });
            }
        }

        myAvatarController.setName(App.userModel.getName());

        final Handler h = new Handler();

        h.post(new Runnable() {
            @Override
            public void run() {
                for (int x = 0; x < SIZE; x++) {
                    for (int y = 0; y < SIZE; y++) {
                        avatarControllers[x][y].user = null;
                    }
                }

                avatarControllers[CENTER][CENTER].user = App.userModel.toUser();

                Set<String> allVisibleUserIds=new HashSet<>();

                for (final User user : App.transporter.getVisibleUsers()) {
                    allVisibleUserIds.add(user.getId());
                    if (!userPositions.containsKey(user.getId())) {
                        userPositions.put(user.getId(), getRandomPoint());
                        userObjects.put(user.getId(), user);
                    }
                }

                Set<String> toRemove=new HashSet<>();

                for (final String s : userObjects.keySet()) {
                    if (!allVisibleUserIds.contains(s)) {
                        toRemove.add(s);
                    }
                }

                for (final String s : toRemove) {
                    userPositions.remove(s);
                    userObjects.remove(s);
                }

                for (final Map.Entry<String, Point> entry : userPositions.entrySet()) {
                    final Point point = entry.getValue();
                    avatarControllers[point.x][point.y].user = userObjects.get(entry.getKey());
                }

                for (int x = 0; x < SIZE; x++) {
                    for (int y = 0; y < SIZE; y++) {

                        final AvatarControllerWithUser avatarController = avatarControllers[x][y];

                        if (avatarController.user == null) {
                            avatarController.rootView.setVisibility(View.INVISIBLE);
                        } else {
                            avatarController.setFromUser(avatarController.user);
                            avatarController.rootView.setVisibility(View.VISIBLE);
                        }
                    }
                }

                h.postDelayed(this, 2000);
            }
        });


        h.post(new Runnable() {
            @Override
            public void run() {

                currentBG=(currentBG+1)%bgs.length;

                gridLayout.setBackgroundResource(bgs[currentBG]);
                h.postDelayed(this, 100);
            }
        });




        for (int i=0;i<10;i++) {
            final View inflate = from.inflate(R.layout.avatar, historyAvatarContainer, false);
            final AvatarController avatarController = new AvatarController(inflate);

            avatarController.setFromUser(App.entropySource.getRandomUser());

            final int dimension = Math.round(getResources().getDimension(R.dimen.avatar_history_size));
            inflate.setLayoutParams(new LinearLayout.LayoutParams(dimension, dimension));

            historyAvatarContainer.addView(inflate);
        }

    }

    private Point getRandomPoint() {
        Random r = new Random();
        final Point point = new Point(Math.abs(r.nextInt() % SIZE), Math.abs(r.nextInt() % SIZE));

        if (point.equals(new Point(CENTER, CENTER))) {
            return getRandomPoint(); // try again ;-)
        }
        return point;
    }

    private HashMap<String, User> userObjects = new HashMap<>();
    private HashMap<String, Point> userPositions = new HashMap<>();

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
