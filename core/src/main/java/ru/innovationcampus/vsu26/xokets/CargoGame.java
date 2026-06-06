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
import ru.innovationcampus.vsu26.xokets.managers.MemoryManager;
import ru.innovationcampus.vsu26.xokets.screens.ScreenGame;
import ru.innovationcampus.vsu26.xokets.screens.ScreenMenu;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class CargoGame extends Game {
    public float accumulator;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private World world;
    private FitViewport viewport;
    private FitViewport uiViewport;
    private Screen screenGame;
    private Screen screenMenu;
    private AssetsManager assetsManager;
    private MemoryManager memoryManager;
    private ContactManager contactManager;
    public Box2DDebugRenderer box2DDebugRenderer;

    @Override
    public void create() {
        Box2D.init();
        screenMenu = new ScreenMenu(this);
        screenGame = new ScreenGame(this);

        world = new World(new Vector2(0, 0), false);
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(Settings.WORLD_WIDTH, Settings.WORLD_HEIGHT, camera);
        uiViewport = new FitViewport(Settings.UI_WORLD_WIDTH, Settings.UI_WORLD_HEIGHT);
        viewport.apply();
        camera.setToOrtho(false);

        assetsManager = new AssetsManager();
        contactManager = new ContactManager(world);
        memoryManager = new MemoryManager();

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

    @Override
    public void resize(int width, int height) {
        screenGame.resize(width, height);
        screenMenu.resize(width, height);
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

    public FitViewport uiViewport() {
        return uiViewport;
    }

    public MemoryManager memoryManager() {
        return memoryManager;
    }
}
