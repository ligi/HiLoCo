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

    private final EntropySource entropySource;

    public FakeTransporter(final Context context, final EntropySource entropySource) {
        this.entropySource = entropySource;
    }

    @Override
    public List<User> getVisibleUsers() {
        final ArrayList<User> users = new ArrayList<>();
        for(int i=0;i<entropySource.getRandom().nextInt(4);i++) {
            users.add(new User(entropySource.getRandomName(), entropySource.getRandomMood(), UUID.randomUUID().toString(), entropySource.getRandom().nextInt(Integer.MAX_VALUE)));
        }
        return users;
    }


}
