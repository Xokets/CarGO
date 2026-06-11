package ru.innovationcampus.vsu26.xokets.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Vector3;

import ru.innovationcampus.vsu26.xokets.CargoGame;

public abstract class MyScreen extends ScreenAdapter {
    protected final CargoGame cargoGame;
    protected Vector3 touch;
    protected Vector3 uiTouch;


    public MyScreen(CargoGame cargoGame) {
        this.cargoGame = cargoGame;
    }
}
