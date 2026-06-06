package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;

import org.jetbrains.annotations.NotNull;

public abstract class UI {
    protected Texture texture;
    protected float x, y;
    protected float width, height;

    public UI (float x, float y, float width, float height, @NotNull Texture texture) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(Batch batch) {
        batch.draw(texture, x - width / 2f, y - height / 2f, width, height);
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
