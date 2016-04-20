package org.ligi.blueblab;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.ligi.axt.AXT;
import org.ligi.blueblab.model.Mood;
import org.ligi.blueblab.model.Transporter;
import org.ligi.blueblab.model.User;

public class FakeTransporter implements Transporter {

    private final Context context;
    private final Random random = new Random();
    private String[] names;

    public FakeTransporter(final Context context) {
        this.context = context;

        final InputStream inputStream = context.getResources().openRawResource(R.raw.names);

        try {
            final String s = AXT.at(inputStream).readToString();
            names = s.split("\r");
        } catch (IOException e) {

        }
    }

    @Override
    public List<User> getVisibleUsers() {
        final ArrayList<User> users = new ArrayList<>();
        users.add(new User(getRandomName(), getRandomMood(), UUID.randomUUID().toString(), random.nextInt(Integer.MAX_VALUE)));
        users.add(new User(getRandomName(), getRandomMood(), UUID.randomUUID().toString(), random.nextInt(Integer.MAX_VALUE)));
        return users;
    }

    private String getRandomName() {
        return names[random.nextInt(names.length)];
    }

    private Mood getRandomMood() {
        return Mood.ALL_MOODS[random.nextInt(5)];
    }
}
