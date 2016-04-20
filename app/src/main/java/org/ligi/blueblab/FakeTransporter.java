package org.ligi.blueblab;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ligi.blueblab.model.Mood;
import org.ligi.blueblab.model.Transporter;
import org.ligi.blueblab.model.User;

public class FakeTransporter implements Transporter {
    @Override
    public List<User> getVisibleUsers() {
        final ArrayList<User> users = new ArrayList<>();
        users.add(new User("foo", Mood.AMUSED, UUID.randomUUID().toString(), Color.BLUE));
        users.add(new User("bar", Mood.AMUSED, UUID.randomUUID().toString(), Color.BLUE));
        return users;
    }
}
