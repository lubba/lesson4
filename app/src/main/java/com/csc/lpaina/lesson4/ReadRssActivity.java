package com.csc.lpaina.lesson4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hugo.weaving.DebugLog;

public class ReadRssActivity extends Activity {

    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String query = intent.getStringExtra(MainActivity.QUERY_TAG);

        RSSParserTask task = new RSSParserTask();
        task.execute(query);
        List<Card> cards = null;
        try {
            cards = task.get();
        } catch (InterruptedException | ExecutionException e) {
            Log.e("ReadRssActivity", "onCreate: ", e);
        }

        if (cards == null) {
            Intent intentReturn = new Intent(this, MainActivity.class);
            intentReturn.putExtra(MainActivity.ERROR_TAG, true);
            startActivity(intentReturn);
            finish();
        }
        setContentView(R.layout.activity_read_rss);
        TextView textView = (TextView) findViewById(R.id.rss_feed_name);
        textView.setText(query);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(new RVAdapter(cards));
    }
}
