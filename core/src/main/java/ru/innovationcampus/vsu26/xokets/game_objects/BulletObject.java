package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

public class BulletObject extends GameObject {

    public BulletObject(float x, float y, float width, float height, PolygonShape shape, @NotNull Texture texture, @NotNull World world, short cBits) {
        super(x, y, width, height, shape, texture, world, cBits);
        hitPoint = 1;
        body.setBullet(true);
        body.setLinearVelocity(0, 8.5f);
    }
}
