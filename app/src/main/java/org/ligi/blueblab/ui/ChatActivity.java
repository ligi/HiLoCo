package org.ligi.blueblab.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ligi.blueblab.App;
import org.ligi.blueblab.R;
import org.ligi.blueblab.model.Message;
import org.ligi.blueblab.model.Mood;

public class ChatActivity extends AppCompatActivity {

    @Bind(R.id.messageRecycler)
    RecyclerView messageRecycler;

    @Bind(R.id.messageText)
    EditText messageText;

    private RecyclerView.Adapter adapter;

    @OnClick(R.id.sendFAB)
    void onSendClick() {
        messageList.add(new Message(App.userModel.toUser(),messageText.getText().toString()));
        adapter.notifyDataSetChanged();
        messageText.setText("");
    }

    List<Message> messageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setReverseLayout(true);
        messageRecycler.setLayoutManager(layout);
        adapter = new MessageAdapter();
        messageRecycler.setAdapter(adapter);
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {
        @Override
        public MessageViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final LayoutInflater from = LayoutInflater.from(parent.getContext());
            final View inflate = from.inflate(R.layout.message, parent, false);
            return new MessageViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(final MessageViewHolder holder, final int position) {
            holder.setText(messageList.get(position).message);
        }

        @Override
        public int getItemCount() {
            return messageList.size();
        }
    }

    class MessageViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.messageText)
        TextView messageText;

        AvatarController avatarController;

        public MessageViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            avatarController=new AvatarController(itemView);
            avatarController.setSize();

        }

        public void setText(String message) {
            messageText.setText(message);
            avatarController.setMood(Mood.NEUTRAL);
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
