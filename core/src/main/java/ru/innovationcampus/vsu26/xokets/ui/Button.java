package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Button extends Interactive {
    private BitmapFont font;
    private String text;
    private float textX, textY;
    private float textWidth, textHeight;

    public Button(float x, float y, float width, float height, @NotNull Texture texture, @Nullable String text, @Nullable BitmapFont font) {
        super(x, y, width, height, texture);
        if (text != null || font != null) {
            this.text = text;
            this.font = font;
            font.getData().setScale(0.023f);


            GlyphLayout gl = new GlyphLayout(font, text);
            textWidth = gl.width;
            textHeight = gl.height;

            textX = x;
            textY = y;
        }
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        font.draw(batch, text, textX - textWidth / 2f, textY + textHeight / 2f);
    }
}
