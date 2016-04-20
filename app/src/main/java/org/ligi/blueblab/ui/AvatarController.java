package org.ligi.blueblab.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.TintContextWrapper;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.ligi.blueblab.R;
import org.ligi.blueblab.model.Mood;

public class AvatarController {
    final View rootView;

    @Bind(R.id.faceImage)
    AppCompatImageView faceImage;

    @Bind(R.id.circleBackground)
    AppCompatImageView circleBackground;

    @Bind(R.id.nameText)
    TextView nameText;

    public Mood mood = Mood.NEUTRAL;
    public int color;

    AvatarController(final View rootView) {
        this.rootView = rootView;
        ButterKnife.bind(this, rootView);
    }

    public void setName(String name) {
        nameText.setText(name);
    }

    public void setMood(Mood mood) {
        this.mood = mood;
        if (mood == null) {
            faceImage.setVisibility(View.GONE);
        } else {
            faceImage.setVisibility(View.VISIBLE);
            faceImage.setImageResource(mood.getResId());
        }
    }

    public void setHasFace(boolean hasFace) {
        circleBackground.setVisibility(hasFace ? View.VISIBLE : View.GONE);
    }

    public void setFaceColor(int color) {
        this.color = color;
        final Drawable drawable = circleBackground.getDrawable();
        final Drawable mutate = drawable.mutate();
        DrawableCompat.setTint(mutate, color);
        circleBackground.setImageDrawable(mutate);
    }
}
