package com.example.midterm_ebrahim_al_serri;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etNumber;
    private Button btnGenerate, btnHistory;
    private ListView lvTable;

    private final ArrayList<String> tableList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = findViewById(R.id.entNumber);
        btnGenerate = findViewById(R.id.btnGenerate);
        btnHistory = findViewById(R.id.btnHistory);
        lvTable = findViewById(R.id.lvTable);

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tableList);
        lvTable.setAdapter(adapter);

        btnGenerate.setOnClickListener(v -> {
            String s = etNumber.getText().toString().trim();
            if (s.isEmpty()) {
                Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
                return;
            }
            int n = Integer.parseInt(s);
            generateTable(n);
            HistoryStore.addIfNotExists(n);
        });

        btnHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class)));

        lvTable.setOnItemClickListener((parent, view, position, id) -> {
            String row = tableList.get(position);
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Delete Row")
                    .setMessage("Delete this line?\n" + row)
                    .setPositiveButton("Delete", (dialog, which) -> {
                        tableList.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Deleted: " + row, Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }

    private void generateTable(int n) {
        tableList.clear();
        for (int i = 1; i <= 10; i++) {
            tableList.add(n + " × " + i + " = " + (n * i));
        }
        adapter.notifyDataSetChanged();
    }
}