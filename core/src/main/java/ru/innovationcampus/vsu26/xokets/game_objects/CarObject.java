package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import ru.innovationcampus.vsu26.xokets.Settings;

public class CarObject extends GameObject implements Animated {
    private boolean isAnimated;
    private float animationStateTime;
    Animation<TextureRegion> driveAnimation;
    public CarObject(float x, float y, float width, float height, PolygonShape shape, @NotNull Texture texture, @NotNull World world, short cBits) {
        super(x, y, width, height, shape, texture, world, cBits);
        hitPoint = 1;
        body.setLinearDamping(2f);
        isAnimated = false;
    }

    public void setDriveAnimation(Animation<TextureRegion> animation) {
        driveAnimation = animation;
    }
    public boolean putInWorld() {
        float hw = width / 2f;
        if (getX() + hw > Settings.WORLD_WIDTH) {
            body.setTransform(new Vector2(Settings.WORLD_WIDTH - hw, getY()), 0);
            return true;
        }
        if (getX() - hw < 0)  {
            body.setTransform(new Vector2(hw, getY()), 0);
            return true;
        }
        return false;
    }

    public void setAnimated(boolean animated) {
        isAnimated = animated;
    }

    public void move(@Nullable Vector3 vector3) {
        if (vector3 == null) return;

        body.setLinearVelocity(new Vector2((vector3.x - body.getPosition().x) * 8, 0));
    }
    public void move(@Nullable Vector3 vector3, float error) {
        if (vector3 == null) return;

        body.setLinearVelocity(new Vector2((vector3.x - body.getPosition().x) * error, 0));
    }

    public void updateAnimationStateTime(float delta) {
        animationStateTime += delta;
    }

    @Override
    public void draw(Batch batch) {
        if (isAnimated) {
            batch.draw(driveAnimation.getKeyFrame(animationStateTime, true), getX() - width / 2f, getY() - height / 2f, width, height);
            return;
        }
        super.draw(batch);
    }
}
