package ru.samsung.gamestudio.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

public class MemoryManager {

    private static final Preferences preferences = Gdx.app.getPreferences("User saves");

    public static void saveSoundSettings(boolean isOn) {
        preferences.putBoolean("isSoundOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsSoundOn() {
        return preferences.getBoolean("isSoundOn", true);
    }

    public static void saveMusicSettings(boolean isOn) {
        preferences.putBoolean("isMusicOn", isOn);
        preferences.flush();
    }

    public static boolean loadIsMusicOn() {
        return preferences.getBoolean("isMusicOn", true);
    }

    public static void saveTableOfRecords(ArrayList<Integer> table) {

        Json json = new Json();
        String tableInString = json.toJson(table);
        preferences.putString("recordTable", tableInString);
        preferences.flush();
    }

    public static ArrayList<Integer> loadRecordsTable() {
        if (!preferences.contains("recordTable"))
            return null;

        String scores = preferences.getString("recordTable");
        Json json = new Json();
        ArrayList<Integer> table = json.fromJson(ArrayList.class, scores);
        return table;
    }

    // ========== НОВЫЕ МЕТОДЫ ДЛЯ СЧЁТЧИКА СБИТЫХ ПРЕПЯТСТВИЙ ==========

    private static final String BEST_DESTROYED_KEY = "best_destroyed";

    public static void saveBestDestroyed(int count) {
        int currentBest = loadBestDestroyed();
        System.out.println("=== SAVE ===");
        System.out.println("Current best: " + currentBest);
        System.out.println("New count: " + count);

        if (count > currentBest) {
            preferences.putInteger(BEST_DESTROYED_KEY, count);
            preferences.flush();
            System.out.println("NEW BEST SAVED: " + count);
        } else {
            System.out.println("NOT A RECORD");
        }
    }

    public static int loadBestDestroyed() {
        int value = preferences.getInteger(BEST_DESTROYED_KEY, 0);
        System.out.println("=== LOAD === Best destroyed: " + value);
        return value;
    }
}