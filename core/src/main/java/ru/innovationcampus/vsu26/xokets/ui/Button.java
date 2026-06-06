package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Button extends Interactive {
    private BitmapFont font;
    private TextUI text;

    public Button(float x, float y, float width, float height, @NotNull Texture texture, @Nullable String text, @Nullable BitmapFont font) {
        super(x, y, width, height, texture);
        if (text != null && font != null) {
            this.text = new TextUI(x, y, font, text);
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        text.draw(batch);
    }
}
