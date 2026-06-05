package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

import ru.innovationcampus.vsu26.xokets.Settings;

public abstract class GameObject {
    protected Texture texture;
    protected Body body;
    protected float width, height;
    protected short cBits;
    protected int hitPoint;

    public GameObject(float x, float y, float width, float height, Shape shape, @NotNull Texture texture, @NotNull World world, short cBits) {
        this.texture = texture;
        this.width = width;
        this.height = height;
        this.cBits = cBits;
        body = createBody(x, y, shape, world);
    }
    public boolean isInWorld() {
        float hw = width / 2f;
        float hh = height / 2f;
        return (getX() - hw >= 0 || getX() + hw <= Settings.WORLD_WIDTH) || (getY() - hh >= 0 || getY() + hh <= Settings.WORLD_HEIGHT);
    }

    public void draw(Batch batch) {
        batch.draw(texture, getX() - width / 2, getY() - height / 2, width, height);
    }

    protected Body createBody(float x, float y, Shape shape, @NotNull World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        if (shape.getType() == Shape.Type.Polygon) {
            PolygonShape polygonShape = (PolygonShape) shape;
            polygonShape.setAsBox(width / 2f, height / 2f);
        } else {
            shape.setRadius(Math.min(width, height) * Settings.SCALE / 2f);
        }
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.getFilterData().categoryBits = cBits;
        fixture.setUserData(this);
        shape.dispose();

        body.setTransform(x, y, 0);
        return body;
    }

    public void hit() {
        hitPoint--;
    }

    public boolean isAlive() {
        return hitPoint > 0;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setX(float x) {
        body.setTransform(x, body.getPosition().y, 0);
    }

    public void setY(float y) {
        body.setTransform(body.getPosition().x, y, 0);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Body getBody() {
        return body;
    }
}
