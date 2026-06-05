package ru.innovationcampus.vsu26.xokets;

import java.util.ArrayList;

import ru.innovationcampus.vsu26.xokets.game_objects.BulletObject;
import ru.innovationcampus.vsu26.xokets.game_objects.ShootingCarObject;

public class GameSession {
    private long time = 0;
    private long nextTrashSpawnTime;
    private long mTruckDeathTime;

    public GameSession() {
        nextTrashSpawnTime = 0;
        mTruckDeathTime = 0;
    }

    public void update(float delta) {
        time += delta * 1000;
    }

    public boolean shouldSpawnTrash() {
        if (nextTrashSpawnTime <= time) {
            nextTrashSpawnTime += Settings.TRASH_SPAWN_COOL_DOWN;
            return true;
        }
        return false;
    }

    public void pointMTruckDeathTime() {
        mTruckDeathTime = time;
    }

    public boolean shouldReviveMTruck() {
        return mTruckDeathTime + Settings.MTRUCK_REVIVE_COOL_DOWN <= time;
    }

    public long time() {
        return time;
    }
}
