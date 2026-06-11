package ru.innovationcampus.vsu26.xokets.game_objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import org.jetbrains.annotations.NotNull;

public class TruckObject extends CarObject {
    boolean wasHit = false;
    public TruckObject(float x, float y, float width, float height, PolygonShape shape, @NotNull Texture texture, @NotNull World world, short cBits) {
        super(x, y, width, height, shape, texture, world, cBits);
    }

    public boolean checkHit() {
        if (wasHit) {
            wasHit = false;
            return true;
        }
        return false;
    }

    @Override
    public void hit() {
        wasHit = true;
    }

    @Override
    protected Body createBody(float x, float y, Shape shape, @NotNull World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.fixedRotation = true;

        Body body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape polygonShape = (PolygonShape) shape;

        float cabinHWidth = width / 2f;
        float cabinHHeight = (height / 1.3f) / 2f;

        polygonShape.setAsBox(cabinHWidth, cabinHHeight, new Vector2(0, 0.34f), 0);
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 0.1f;
        fixtureDef.friction = 1f;

        Fixture fixture = body.createFixture(fixtureDef);
        fixture.getFilterData().categoryBits = cBits;
        fixture.setUserData(this);

        float wallHWidth = (width / 6.08f) / 2f;
        float wallHHeight = (height / 3.7f) / 2f;
        polygonShape.setAsBox(wallHWidth, wallHHeight, new Vector2(-cabinHWidth + wallHWidth, -cabinHHeight), 0);

        Fixture fixture2 = body.createFixture(fixtureDef);
        fixture2.getFilterData().categoryBits = cBits;
        fixture2.setUserData(this);
        polygonShape.setAsBox(wallHWidth, wallHHeight, new Vector2(cabinHWidth - wallHWidth, -cabinHHeight), 0);
        Fixture fixture3 = body.createFixture(fixtureDef);
        fixture3.getFilterData().categoryBits = cBits;
        fixture3.setUserData(this);

        polygonShape.dispose();

        body.setTransform(x, y, 0);
        return body;
    }
}
