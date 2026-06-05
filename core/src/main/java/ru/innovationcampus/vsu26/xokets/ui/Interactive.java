package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Interactive extends UI {
    public Interactive(float x, float y, float width, float height, @NotNull Texture texture) {
        super(x, y, width, height, texture);
    }

    public boolean isTouch(@Nullable Vector3 touch) {
        if (touch == null) return false;

        return touch.x >= x && touch.x <= x + width && touch.y >= y && touch.y <= y + height;
    }
}
