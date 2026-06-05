package ru.innovationcampus.vsu26.xokets.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

import ru.innovationcampus.vsu26.xokets.Resources;
import ru.innovationcampus.vsu26.xokets.Utils;

public class AssetsManager implements Disposable {
    private Texture bulletTexture;
    private Texture truckTexture;
    private Texture barrelTexture;
    private Texture millitaryTruckTexture;
    private Texture textButtonBGTexture;
    private TextureAtlas truckDriveAtlas;
    private TextureAtlas mTruckDriveAtlas;
    private TextureAtlas cargoLostAtlas;
    private Animation<TextureRegion> truckDriveAnimation;
    private Animation<TextureRegion> mTruckDriveAnimation;
    private Animation<TextureRegion> cargoLostAnimation;
    private Texture cargoTexture;
    private Texture roadTexture;
    private Texture blackoutFullTexture;

    private BitmapFont commonWhiteFont;

    public AssetsManager() {
        barrelTexture = new Texture(Resources.BARREL_ITP);
        bulletTexture = new Texture(Resources.BULLET_ITP);
        truckTexture = new Texture(Resources.TRUCK_ITP);
        textButtonBGTexture = new Texture(Resources.TEXT_BUTTON_BG_ITP);
        millitaryTruckTexture = new Texture(Resources.MILLITARY_TRUCK_ITP);
        truckDriveAtlas = new TextureAtlas(Resources.TRUCK_DRIVE_ATLAS_ITP);
        mTruckDriveAtlas = new TextureAtlas(Resources.MTRUCK_DRIVE_ATLAS_ITP);

        truckDriveAnimation = new Animation<>(0.1f, truckDriveAtlas.findRegions("Drive"));
        mTruckDriveAnimation = new Animation<>(0.1f, mTruckDriveAtlas.findRegions("Drive"));

        roadTexture = new Texture(Resources.ROAD_ITP);
        blackoutFullTexture = new Texture(Resources.BLACKOUT_FULL_ITP);
        cargoTexture = new Texture(Resources.CARGO_ITP);
        cargoLostAtlas = new TextureAtlas(Resources.CARGO_LOST_ATLAS_ITP);
        cargoLostAnimation = new Animation<>(0.1f, cargoLostAtlas.findRegions("Lost"));

        commonWhiteFont = Utils.generateFont(64, Color.WHITE, Resources.COMMON_IFP);
    }

    @Override
    public void dispose() {
        truckTexture.dispose();
        roadTexture.dispose();
        blackoutFullTexture.dispose();
        truckDriveAtlas.dispose();
        millitaryTruckTexture.dispose();
        bulletTexture.dispose();
        cargoTexture.dispose();
        cargoLostAtlas.dispose();
        barrelTexture.dispose();
        commonWhiteFont.dispose();
        textButtonBGTexture.dispose();
    }

    public Texture truckTexture() {
        return truckTexture;
    }

    public Texture millitaryTruckTexture() {
        return millitaryTruckTexture;
    }

    public Texture roadTexture() {
        return roadTexture;
    }

    public Animation<TextureRegion> truckDriveAnimation() {
        return truckDriveAnimation;
    }
    public Animation<TextureRegion> mTruckDriveAnimation() {
        return mTruckDriveAnimation;
    }

    public Animation<TextureRegion> cargoLostAnimation() {
        return cargoLostAnimation;
    }

    public Texture bulletTexture() {
        return bulletTexture;
    }

    public Texture cargoTexture() {
        return cargoTexture;
    }

    public Texture barrelTexture() {
        return barrelTexture;
    }

    public Texture blackoutFullTexture() {
        return blackoutFullTexture;
    }

    public BitmapFont commonWhiteFont() {
        return commonWhiteFont;
    }

    public Texture textButtonBGTexture() {
        return textButtonBGTexture;
    }
}
