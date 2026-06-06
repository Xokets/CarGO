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
import ru.innovationcampus.vsu26.xokets.ui.TextUI;

public class ScreenMenu extends MyScreen {
    private MovingBackground background;
    private Button startButton;
    private TextUI bestRecordTextUI;

    public ScreenMenu(CargoGame cargoGame) {
        super(cargoGame);
    }

    @Override
    public void show() {
        background = new MovingBackground(cargoGame.assetsManager().roadTexture(), 0);
        startButton = new Button(

            Settings.UI_WORLD_WIDTH / 2f,
            Settings.UI_WORLD_HEIGHT / 2f,
            Settings.TEXT_BUTTON_WIDTH,
            Settings.TEXT_BUTTON_HEIGHT,
            cargoGame.assetsManager().textButtonBGTexture(),
            "Start",
            cargoGame.assetsManager().commonWhiteFont()

        );
        bestRecordTextUI = new TextUI(

            startButton.getX(),
            startButton.getY() - startButton.getHeight() / 2f - 100,
            cargoGame.assetsManager().commonBlackFont(),
            "Best record: " + cargoGame.memoryManager().bestRecord()

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
        cargoGame.batch().end();

        cargoGame.uiViewport().getCamera().update();
        cargoGame.batch().setProjectionMatrix(cargoGame.uiViewport().getCamera().combined);
        cargoGame.batch().begin();
        startButton.draw(cargoGame.batch());
        bestRecordTextUI.draw(cargoGame.batch());
        cargoGame.batch().end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            uiTouch = Utils.getCorrectTouch(cargoGame.uiViewport(), new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        }



        if (startButton.isTouch(uiTouch)) {
            uiTouch = null;
            cargoGame.setScreen(cargoGame.screenGame());
        }
    }

    @Override
    public void resize(int width, int height) {
        cargoGame.viewport().update(width, height, true);
        cargoGame.uiViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
