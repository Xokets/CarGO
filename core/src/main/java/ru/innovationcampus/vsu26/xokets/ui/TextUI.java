package ru.innovationcampus.vsu26.xokets.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import org.jetbrains.annotations.NotNull;

public class TextUI {
    protected BitmapFont font;
    protected String text;
    protected float x, y;
    protected float width, height;

    public TextUI(float x, float y, @NotNull BitmapFont font, @NotNull String text) {
        this.font = font;
        this.text = text;
        this.x = x;
        this.y = y;

        GlyphLayout gl = new GlyphLayout(font, text);
        this.width = gl.width;
        this.height = gl.height;
    }

    public void draw(Batch batch) {
        font.draw(batch, text, x - width / 2f, y + height / 2f);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
