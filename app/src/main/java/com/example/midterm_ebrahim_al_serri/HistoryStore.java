package com.example.midterm_ebrahim_al_serri;

public class HistoryStore {
    private static final ArrayList<Integer> numbers = new ArrayList<>();
    public static Integer lastNumber = null; // remember last generated number

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
