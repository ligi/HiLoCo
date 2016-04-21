package org.ligi.blueblab;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ligi.blueblab.model.Transporter;
import org.ligi.blueblab.model.User;

public class FakeTransporter implements Transporter {

    private final EntropySource entropySource;

    final ArrayList<User> users = new ArrayList<>();

    public FakeTransporter(final Context context, final EntropySource entropySource) {
        this.entropySource = entropySource;

        for (int i = 0; i < entropySource.getRandom().nextInt(7); i++) {
            addRandomUser();
        }

    }

    private void addRandomUser() {
        users.add(new User(UUID.randomUUID().toString(),
                           entropySource.getRandomName(),
                           entropySource.getRandomMood(),
                           entropySource.getRandom().nextInt(Integer.MAX_VALUE)));
    }

    @Override
    public List<User> getVisibleUsers() {

        for (int i=0;i<entropySource.getRandom().nextInt(3);i++) {
            if (entropySource.getRandom().nextBoolean() && !users.isEmpty()) {
                users.remove(entropySource.getRandom().nextInt(users.size()));
            } else if(users.size()<12) {
                addRandomUser();
            }
        }

        return users;
    }


}
