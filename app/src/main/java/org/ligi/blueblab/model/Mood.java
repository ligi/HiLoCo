package org.ligi.blueblab.model;

import org.ligi.blueblab.R;

public enum Mood {
    NEUTRAL(R.drawable.neutral),
    AMUSED(R.drawable.amused),
    CHATTY(R.drawable.chatty),
    SAD(R.drawable.sad),
    ANGRY(R.drawable.angry),
    CALM(R.drawable.calm);

    private final int resId;

    Mood(final int resId) {
        this.resId = resId;
    }

    public int getResId() {
        return resId;
    }

    public static Mood[] ALL_MOODS = new Mood[]{NEUTRAL, AMUSED, CALM, CHATTY, SAD, ANGRY};
}
