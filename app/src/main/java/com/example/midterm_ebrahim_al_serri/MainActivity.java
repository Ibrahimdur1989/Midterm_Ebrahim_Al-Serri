package com.example.midterm_ebrahim_al_serri;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText entNumber;
    private Button btnGenerate, btnHistory;
    private ListView lvTable;

    private final ArrayList<Integer> tableItems = new ArrayList<>();
    private ArrayAdapter<Integer> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        entNumber = findViewById(R.id.entNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        lvTable = findViewById(R.id.lvTable);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tableItems);
        lvTable.setAdapter(adapter);

        btnGenerate.setOnClickListener(v -> {
            String s = entNumber.getText().toString().trim();
            if (s.isEmpty()) { entNumber.setError("Enter a number"); return; }
            int n = Integer.parseInt(s);
            fillMultiples(n);
            HistoryStore.addIfNotExists(n);
        });

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class)));


        if (HistoryStore.lastNumber != null) {
            entNumber.setText(String.valueOf(HistoryStore.lastNumber));
            fillMultiples(HistoryStore.lastNumber);
        }


        lvTable.setOnItemClickListener((parent, view, position, id) -> {
            Integer value = tableItems.get(position);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete?")
                    .setMessage("Delete this line?\n" + value)
                    .setPositiveButton("Delete", (dialog, which) -> {
                        tableItems.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Deleted: " + value, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void fillMultiples(int n) {
        tableItems.clear();
        for (int i = 1; i <= 10; i++) tableItems.add(n * i);
        adapter.notifyDataSetChanged();
    }
}