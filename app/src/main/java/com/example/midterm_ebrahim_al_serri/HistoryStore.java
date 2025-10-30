package com.example.midterm_ebrahim_al_serri;

import java.util.ArrayList;
import java.util.List;

public class HistoryStore {
    private static final ArrayList<Integer> numbers = new ArrayList<>();
    public static Integer lastNumber = null;

    public static void addIfNotExists(int n) {
        if (!numbers.contains(n)) numbers.add(n);
        lastNumber = n;
    }

    public static List<Integer> getAll() {
        return numbers;
    }

    public static void clearAllNumbers() {
        numbers.clear();
        lastNumber = null;
    }
}
