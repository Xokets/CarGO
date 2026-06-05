package ru.innovationcampus.vsu26.xokets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Utils {
    public static Vector3 getCorrectTouch(@NotNull Viewport viewport, @Nullable Vector3 touch) {
        return viewport.unproject(touch);
    }

    public static BitmapFont generateFont(int size, @NotNull Color color, @NotNull String internalFontPath) {

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(internalFontPath));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = size;
        fontParameter.color = color;

        try {
            return fontGenerator.generateFont(fontParameter);
        } finally {
            fontGenerator.dispose();
        }
    }
}
