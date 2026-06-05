package ru.innovationcampus.vsu26.xokets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Set;

import ru.innovationcampus.vsu26.xokets.managers.AssetsManager;
import ru.innovationcampus.vsu26.xokets.managers.ContactManager;
import ru.innovationcampus.vsu26.xokets.screens.ScreenGame;
import ru.innovationcampus.vsu26.xokets.screens.ScreenMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class CargoGame extends Game {
    public float accumulator;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private World world;
    private FitViewport viewport;
    private Screen screenGame;
    private Screen screenMenu;
    private Vector3 touch;
    private AssetsManager assetsManager;
    private ContactManager contactManager;
    public Box2DDebugRenderer box2DDebugRenderer;

    @Override
    public void create() {
        Box2D.init();
        assetsManager = new AssetsManager();
        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);
        world = new World(new Vector2(0, 0), false);
        batch = new SpriteBatch();
        contactManager = new ContactManager(world);
        camera = new OrthographicCamera();
        viewport = new FitViewport(9f, 16f, camera);
        viewport.apply();
        camera.setToOrtho(false);
        box2DDebugRenderer = new Box2DDebugRenderer();
        setScreen(screenMenu);
    }

    @Override
    public void dispose () {
        batch.dispose();
        screenGame.dispose();
        screenMenu.dispose();
        assetsManager.dispose();
    }

    public Batch batch() {
        return batch;
    }

    public OrthographicCamera camera() {
        return camera;
    }

    public World world() {
        return world;
    }

    public FitViewport viewport() {
        return viewport;
    }

    public Vector3 getTouch() {
        return touch;
    }

    public void setTouch(Vector3 touch) {
        this.touch = touch;
    }

    public void stepWorld(float delta) {
        world.step(delta, Settings.VELOCITY_ITERATIONS, Settings.POSITION_ITERATIONS);
    }

    public Screen screenGame() {
        return screenGame;
    }

    public AssetsManager assetsManager() {
        return assetsManager;
    }

    public Screen screenMenu() {
        return screenMenu;
    }
}
