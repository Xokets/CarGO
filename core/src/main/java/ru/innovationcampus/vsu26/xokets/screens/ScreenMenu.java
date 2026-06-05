package ru.innovationcampus.vsu26.xokets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import ru.innovationcampus.vsu26.xokets.CargoGame;
import ru.innovationcampus.vsu26.xokets.Settings;
import ru.innovationcampus.vsu26.xokets.Utils;
import ru.innovationcampus.vsu26.xokets.ui.Button;
import ru.innovationcampus.vsu26.xokets.ui.MovingBackground;

public class ScreenMenu extends ScreenAdapter {
    private final CargoGame cargoGame;
    private MovingBackground background;
    private Button startButton;

    public ScreenMenu(CargoGame cargoGame) {
        this.cargoGame = cargoGame;
    }

    @Override
    public void show() {
        background = new MovingBackground(cargoGame.assetsManager().roadTexture(), 0);
        startButton = new Button(

            Settings.WORLD_WIDTH / 2f,
            Settings.WORLD_HEIGHT / 2f,
            Settings.TEXT_BUTTON_WIDTH,
            Settings.TEXT_BUTTON_HEIGHT,
            cargoGame.assetsManager().textButtonBGTexture(),
            "Start",
            cargoGame.assetsManager().commonWhiteFont()

        );
    }

    @Override
    public void render(float delta) {
        handleInput();
        cargoGame.camera().update();
        ScreenUtils.clear(Color.BLACK);
        cargoGame.batch().setProjectionMatrix(cargoGame.camera().combined);
        cargoGame.batch().begin();
        background.draw(cargoGame.batch());
        startButton.draw(cargoGame.batch());
        cargoGame.batch().end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            cargoGame.setTouch(Utils.getCorrectTouch(cargoGame.viewport(), new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        }

        if (startButton.isTouch(cargoGame.getTouch())) {
            cargoGame.setScreen(cargoGame.screenGame());
        }
    }

    @Override
    public void resize(int width, int height) {
        cargoGame.viewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
