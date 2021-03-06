package com.csc.lpaina.lesson4;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hugo.weaving.DebugLog;

@DebugLog
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CardViewHolder> {

    private Cursor cursor;

    public RVAdapter(Cursor cursor) {
        this.cursor = cursor;
        cursor.moveToFirst();
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardViewHolder, int i) {
        if (cursor.move(i)) {
            cardViewHolder.textViewTitle.setText(cursor.getString(cursor.getColumnIndex(Card.TITLE_NAME)));
            cardViewHolder.textViewDescription.setText(cursor.getString(cursor.getColumnIndex(Card.DESCRIPTION_NAME)));
            cardViewHolder.textViewChannel.setText(cursor.getString(cursor.getColumnIndex(Card.CHANNEL_NAME)));
            cardViewHolder.textViewLink.setText(cursor.getString(cursor.getColumnIndex(Card.LINK_NAME)));
            cursor.moveToFirst();
        }

    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewTitle;
        final TextView textViewDescription;
        final TextView textViewChannel;
        final TextView textViewLink;

        CardViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.feed_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.feed_description);
            textViewChannel = (TextView) itemView.findViewById(R.id.feed_channel);
            textViewLink = (TextView) itemView.findViewById(R.id.feed_link);
        }
    }

}
