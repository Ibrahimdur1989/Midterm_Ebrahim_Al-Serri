package com.example.midterm_ebrahim_al_serri;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ListView lvHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        lvHistory = findViewById(R.id.lvHistory);

        ArrayList<Integer> list = new ArrayList<>(HistoryStore.getAll());
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, list);
        lvHistory.setAdapter(adapter);
    }
}