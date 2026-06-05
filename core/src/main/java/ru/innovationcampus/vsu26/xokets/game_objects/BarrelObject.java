package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

public class BarrelObject extends GameObject {
    public BarrelObject(float x, float y, float width, float height, Shape shape, @NotNull Texture texture, @NotNull World world, short cBits) {
        super(x, y, width, height, shape, texture, world, cBits);
        hitPoint = 1;
        body.setLinearVelocity(0, -9);
    }
}
