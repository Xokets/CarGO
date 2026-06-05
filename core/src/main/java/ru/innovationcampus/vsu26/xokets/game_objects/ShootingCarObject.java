package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

public class ShootingCarObject extends CarObject {
    private long shootingCoolDown;
    private long lastShootTime;
    public ShootingCarObject(float x, float y, float width, float height, PolygonShape shape, @NotNull Texture texture, @NotNull World world, long shootingCoolDown, short cBits) {
        super(x, y, width, height, shape, texture, world, cBits);
        this.shootingCoolDown = shootingCoolDown;
        hitPoint = 3;
    }

    public boolean canShoot(long time) {
        if (time - lastShootTime >= shootingCoolDown) {
            lastShootTime = time;
            return true;
        }
        return false;
    }
}
