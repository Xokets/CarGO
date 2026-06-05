package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import org.jetbrains.annotations.NotNull;

import ru.innovationcampus.vsu26.xokets.Settings;

public class MovingBackground extends UI {
    private float speed;
    public MovingBackground(@NotNull Texture texture, float speed) {
        super(0, 0, Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT, texture);
        this.speed = speed;
    }

    public void move() {
        y -= speed;
        if (y <= -height) y = 0;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, x, y, width, height);
        batch.draw(texture, x, y + height, width, height);
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
