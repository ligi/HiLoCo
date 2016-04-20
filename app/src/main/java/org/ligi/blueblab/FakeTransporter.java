package org.ligi.blueblab;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
        for (int i = 0; i < entropySource.getRandom().nextInt(7); i++) {
            users.add(new User(UUID.randomUUID().toString(),
                               entropySource.getRandomName(),
                               entropySource.getRandomMood(),
                               entropySource.getRandom().nextInt(Integer.MAX_VALUE)));
        }
        return users;
    }


}
