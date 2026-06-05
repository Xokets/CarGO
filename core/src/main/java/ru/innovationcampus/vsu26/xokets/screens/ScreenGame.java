package ru.innovationcampus.vsu26.xokets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import ru.innovationcampus.vsu26.xokets.CargoGame;
import ru.innovationcampus.vsu26.xokets.GameSession;
import ru.innovationcampus.vsu26.xokets.Settings;
import ru.innovationcampus.vsu26.xokets.Utils;
import ru.innovationcampus.vsu26.xokets.game_objects.BarrelObject;
import ru.innovationcampus.vsu26.xokets.game_objects.BulletObject;
import ru.innovationcampus.vsu26.xokets.game_objects.CarObject;
import ru.innovationcampus.vsu26.xokets.game_objects.CargoObject;
import ru.innovationcampus.vsu26.xokets.game_objects.ShootingCarObject;
import ru.innovationcampus.vsu26.xokets.game_objects.TruckObject;
import ru.innovationcampus.vsu26.xokets.managers.AssetsManager;
import ru.innovationcampus.vsu26.xokets.ui.Button;
import ru.innovationcampus.vsu26.xokets.ui.Image;
import ru.innovationcampus.vsu26.xokets.ui.MovingBackground;

public class ScreenGame extends ScreenAdapter {
    private final ArrayList<BulletObject> bulletArray = new ArrayList<>();
    private final ArrayList<CargoObject> cargoArray = new ArrayList<>();
    private final ArrayList<BarrelObject> barrelArray = new ArrayList<>();
    private MovingBackground roadBackground;
    private GameSession gameSession;
    private boolean isGameOver;
    private int point;
    private final CargoGame cargoGame;
    private TruckObject truck;
    private ShootingCarObject millitaryTruck;
    private Image blackoutFull;
    private Button menuButton;

    public ScreenGame(CargoGame cargoGame) {
        this.cargoGame = cargoGame;
    }

    @Override
    public void show() {
        point = 0;
        isGameOver = false;

//        Body leftWall;
//        Body rightWall;
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.fixedRotation = true;
//        bodyDef.type = BodyDef.BodyType.StaticBody;
//
//        leftWall = cargoGame.world().createBody(bodyDef);
//        rightWall = cargoGame.world().createBody(bodyDef);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        PolygonShape polygonShape = new PolygonShape();
//        polygonShape.setAsBox(Settings.WORLD_WIDTH / 2f, Settings.WORLD_HEIGHT / 2f);
//        fixtureDef.shape = polygonShape;
//        leftWall.createFixture(fixtureDef);
//        rightWall.createFixture(fixtureDef);
//        leftWall.setTransform(-Settings.WORLD_WIDTH / 2f, Settings.WORLD_HEIGHT / 2f, 0);
//        rightWall.setTransform(Settings.WORLD_WIDTH * 1.5f, Settings.WORLD_HEIGHT / 2f, 0);

        truck = new TruckObject(

            Settings.WORLD_WIDTH / 2f,
            Settings.WORLD_HEIGHT / 2f,
            Settings.TRUCK_WIDTH,
            Settings.TRUCK_HEIGHT,
            new PolygonShape(),
            cargoGame.assetsManager().truckTexture(),
            cargoGame.world(),
            Settings.TRUCK_CBITS
        );

        for (int i = 0; i < 4; i++) {
            cargoArray.add(new CargoObject(

                truck.getX() + i * 0.01f,
                truck.getY() - truck.getHeight() / 3f,
                Settings.CARGO_SIZE, Settings.CARGO_SIZE,
                new PolygonShape(),
                cargoGame.assetsManager().cargoTexture(),
                cargoGame.world(),
                Settings.CARGO_CBITS,
                cargoGame.assetsManager().cargoLostAnimation()

            ));
        }

        millitaryTruck = new ShootingCarObject(

            Settings.WORLD_WIDTH / 2f,
            Settings.MILLITARY_TRUCK_HEIGHT / 6f,
            Settings.MILLITARY_TRUCK_WIDTH,
            Settings.MILLITARY_TRUCK_HEIGHT,
            new PolygonShape(),
            cargoGame.assetsManager().millitaryTruckTexture(),
            cargoGame.world(),
            Settings.MILLITARY_TRUCK_SHOOTING_COOL_DOWN,
            Settings.SHOOTING_CAR_CBITS

        );

        roadBackground = new MovingBackground(cargoGame.assetsManager().roadTexture(), 0.2f);
        truck.setDriveAnimation(cargoGame.assetsManager().truckDriveAnimation());
        truck.setAnimated(true);
        millitaryTruck.setDriveAnimation(cargoGame.assetsManager().mTruckDriveAnimation());
        millitaryTruck.setAnimated(true);
        blackoutFull = new Image(

            Settings.WORLD_WIDTH / 2f,
            Settings.WORLD_HEIGHT / 2f,
            Settings.WORLD_WIDTH,
            Settings.WORLD_HEIGHT,
            cargoGame.assetsManager().blackoutFullTexture()

        );
        gameSession = new GameSession();
        menuButton = new Button(

            Settings.WORLD_WIDTH / 2f,
            Settings.WORLD_HEIGHT / 2f,
            Settings.TEXT_BUTTON_WIDTH,
            Settings.TEXT_BUTTON_HEIGHT,
            cargoGame.assetsManager().textButtonBGTexture(),
            "Menu",
            cargoGame.assetsManager().commonWhiteFont()

            );
    }

    @Override
    public void render(float delta) {
        if (!isGameOver) {
            updateObjects(delta);
            cleanObjects();
            cargoGame.stepWorld(delta);
        }
        handleInput();
        cargoGame.camera().update();
        ScreenUtils.clear(Color.BLACK);
        cargoGame.batch().setProjectionMatrix(cargoGame.camera().combined);
        cargoGame.batch().begin();
        roadBackground.draw(cargoGame.batch());
        truck.draw(cargoGame.batch());
        if (millitaryTruck != null) millitaryTruck.draw(cargoGame.batch());
        for (BulletObject bullet : bulletArray) bullet.draw(cargoGame.batch());
        for (CargoObject cargo : cargoArray) cargo.draw(cargoGame.batch());
        for (BarrelObject barrel : barrelArray) barrel.draw(cargoGame.batch());
        if (isGameOver) {
            blackoutFull.draw(cargoGame.batch());
            menuButton.draw(cargoGame.batch());
        }
        BitmapFont bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(0.1f);
        bitmapFont.draw(cargoGame.batch(), "Point " + point, 0, Settings.WORLD_HEIGHT - 1);
        cargoGame.assetsManager().commonWhiteFont().getData().setScale(0.023f);
        cargoGame.batch().end();
        cargoGame.box2DDebugRenderer.render(
            cargoGame.world(),
            cargoGame.camera().combined
        );
    }

    @Override
    public void resize(int width, int height) {
        cargoGame.viewport().update(width, height, true);
    }

    @Override
    public void dispose() {
    }

    private void handleInput() {
        if (Gdx.input.isTouched()) {
            cargoGame.setTouch(Utils.getCorrectTouch(cargoGame.viewport(), new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
        }
        if (isGameOver) {
            if (Gdx.input.justTouched()) {
                cargoGame.setTouch(Utils.getCorrectTouch(cargoGame.viewport(), new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
            }

            if (menuButton.isTouch(cargoGame.getTouch())) cargoGame.setScreen(cargoGame.screenMenu());
        }
    }

    private void updateObjects(float delta) {
        gameSession.update(delta);

        truck.setY(Settings.WORLD_HEIGHT / 2f);
        truck.move(cargoGame.getTouch());
        truck.updateAnimationStateTime(delta);
        if (cargoArray.size() > 0) {
            if (truck.checkHit()) cargoArray.get(ThreadLocalRandom.current().nextInt(cargoArray.size())).drop();
        } else {
            isGameOver = true;
            cargoGame.setTouch(null);
        }


        if (truck.putInWorld()) {
            cargoGame.setTouch(null);
        }
        if (millitaryTruck != null) {
            millitaryTruck.setY(Settings.MILLITARY_TRUCK_HEIGHT / 6f);
            millitaryTruck.move(cargoGame.getTouch(), 2);
            millitaryTruck.updateAnimationStateTime(delta);

            millitaryTruck.putInWorld();

            if (millitaryTruck.canShoot(gameSession.time())) {
                bulletArray.add(new BulletObject(

                    millitaryTruck.getX(),
                    millitaryTruck.getY() + millitaryTruck.getHeight() / 2f,
                    Settings.BULLET_SIZE,
                    Settings.BULLET_SIZE,
                    new PolygonShape(),
                    cargoGame.assetsManager().bulletTexture(),
                    cargoGame.world(),
                    Settings.BULLET_CBITS

                ));
            }
        } else {

            if (gameSession.shouldReviveMTruck()) {

                millitaryTruck = new ShootingCarObject(

                    Settings.WORLD_WIDTH / 2f,
                    Settings.MILLITARY_TRUCK_HEIGHT / 6f,
                    Settings.MILLITARY_TRUCK_WIDTH,
                    Settings.MILLITARY_TRUCK_HEIGHT,
                    new PolygonShape(),
                    cargoGame.assetsManager().millitaryTruckTexture(),
                    cargoGame.world(),
                    Settings.MILLITARY_TRUCK_SHOOTING_COOL_DOWN,
                    Settings.SHOOTING_CAR_CBITS

                );
                millitaryTruck.setDriveAnimation(cargoGame.assetsManager().mTruckDriveAnimation());
                millitaryTruck.setAnimated(true);
            }
        }

        if (gameSession.shouldSpawnTrash()) barrelArray.add(new BarrelObject(

            Settings.TRASH_SPAWN_PADDING + ThreadLocalRandom.current().nextFloat(Settings.WORLD_WIDTH - Settings.TRASH_SPAWN_PADDING * 2),
            Settings.WORLD_HEIGHT,
            Settings.BARREL_WIDTH,
            Settings.BARREL_HEIGHT,
            new PolygonShape(),
            cargoGame.assetsManager().barrelTexture(),
            cargoGame.world(),
            Settings.BARREL_CBITS

        ));

        for (CargoObject cargo : cargoArray) {
            if (cargo.isLost()) {
                cargo.updateAnimationStateTime(delta);
                continue;
            }
            if (cargo.isInBody(truck.getBody().getPosition(), truck.getWidth(), truck.getHeight())) continue;
            cargo.setLost(true);
        }

        //FIXED TIME STEP
        cargoGame.accumulator += delta;
        while (cargoGame.accumulator >= Settings.FIXED_TIME_STEP) {
            roadBackground.move();
            cargoGame.accumulator -= Settings.FIXED_TIME_STEP;
        }
    }

    public void cleanObjects() {
        if (millitaryTruck != null && !millitaryTruck.isAlive()) {
            cargoGame.world().destroyBody(millitaryTruck.getBody());
            millitaryTruck = null;
            gameSession.pointMTruckDeathTime();
            point += 100;
        }
        for (int i = 0; i < bulletArray.size(); i++) {
            if (bulletArray.get(i).isInWorld() && bulletArray.get(i).isAlive()) continue;
            cargoGame.world().destroyBody(bulletArray.get(i).getBody());
            bulletArray.remove(i--);
        }
        for (int i = 0; i < barrelArray.size(); i++) {
            if (barrelArray.get(i).isInWorld() && barrelArray.get(i).isAlive()) continue;
            cargoGame.world().destroyBody(barrelArray.get(i).getBody());
            barrelArray.remove(i--);
            point += 1;
        }
        for (int i = 0; i < cargoArray.size(); i++) {
            if (cargoArray.get(i).isInWorld() && cargoArray.get(i).isAlive()) continue;
            cargoGame.world().destroyBody(cargoArray.get(i).getBody());
            cargoArray.remove(i--);
        }
    }
}
