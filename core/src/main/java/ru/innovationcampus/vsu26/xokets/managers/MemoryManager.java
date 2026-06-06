package ru.innovationcampus.vsu26.xokets.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


public class MemoryManager {
    private static final String BEST_RECORD_KEY = "best_record";

    private Preferences prefs;

    public MemoryManager() {
        prefs = Gdx.app.getPreferences("data");
    }

    public void addRecord(int point) {
        prefs.putInteger(BEST_RECORD_KEY, Math.max(prefs.getInteger(BEST_RECORD_KEY, 0), point));
        prefs.flush();
    }

    public int bestRecord() {
        return prefs.getInteger(BEST_RECORD_KEY, 0);
    }
}
