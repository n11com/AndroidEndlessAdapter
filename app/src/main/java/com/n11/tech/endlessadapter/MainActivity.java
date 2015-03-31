package com.n11.tech.endlessadapter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.n11.tech.endlessadapter.adapter.SampleAdapter;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ListView endlessListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        endlessListView = (ListView) findViewById(R.id.listView);

        SampleAdapter sampleAdapter = new SampleAdapter(this, new ArrayList<String>());
        endlessListView.setAdapter(sampleAdapter);
    }

}
