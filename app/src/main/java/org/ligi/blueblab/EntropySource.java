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

public class EntropySource  {

    private final Random random = new Random();
    private String[] names;
    private String[] hellos=new String[] { "Hi","Hello","Aloha","Greetings"};

    public EntropySource(final Context context) {

        final InputStream inputStream = context.getResources().openRawResource(R.raw.names);

        try {
            final String s = AXT.at(inputStream).readToString();
            names = s.split("\r");
        } catch (IOException ignored) {

        }
    }


    public String getRandomName() {
        return names[random.nextInt(names.length)];
    }

    public Mood getRandomMood() {
        return Mood.ALL_MOODS[random.nextInt(Mood.ALL_MOODS.length)];
    }

    public Random getRandom() {
        return random;
    }

    public String getRandomHello() {
        return hellos[random.nextInt(hellos.length)];
    }


    public User getRandomUser() {
        return new User(UUID.randomUUID().toString(),
                 getRandomName(),
                 getRandomMood(),
                 getRandom().nextInt(Integer.MAX_VALUE));
    }

}
