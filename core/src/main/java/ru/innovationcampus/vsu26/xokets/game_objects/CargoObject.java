package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CargoObject extends GameObject implements Animated {
    private float animationStateTime;
    private boolean isLost;
    private Animation<TextureRegion> lostAnimation;
    public CargoObject(float x, float y, float width, float height, Shape shape, @NotNull Texture texture, @NotNull World world, short cBits, Animation<TextureRegion> lostAnimation) {
        super(x, y, width, height, shape, texture, world, cBits);
        this.lostAnimation = lostAnimation;
        hitPoint = 1;
        animationStateTime = 0;
        isLost = false;
    }

    @Override
    public void draw(Batch batch) {
        if (isLost) {
            batch.draw(lostAnimation.getKeyFrame(animationStateTime, true), getX(), getY(), width, height);
            if (animationStateTime >= 2) hit();
            return;
        }
        super.draw(batch);
    }

    public boolean isInBody(Vector2 bodyPos, float width, float height) {
        return getY() + this.height / 2f >= bodyPos.y - height / 2f &&
            getY() - this.height / 2f <= bodyPos.y + height / 2f &&
            getX() + this.width / 2f >= bodyPos.x - width / 2f &&
            getX() - this.width / 2f <= bodyPos.x + width / 2f;
    }

    public void drop() {
        int i = ThreadLocalRandom.current().nextInt(1, 5);
        switch (i) {
            case 1 -> body.applyForceToCenter(4.5f, 0, false);
            case 2 -> body.applyForceToCenter(-4.5f, 0, false);
            case 3 -> body.applyForceToCenter(0, 4.5f, false);
            case 4 -> body.applyForceToCenter(0, -4.5f, false);
        }
    }

    public void setLost(boolean isLost) {
        this.isLost = isLost;
    }

    public boolean isLost() {
        return isLost;
    }

    @Override
    public void updateAnimationStateTime(float delta) {
        if (!isLost) return;
        animationStateTime += delta;
    }
}
