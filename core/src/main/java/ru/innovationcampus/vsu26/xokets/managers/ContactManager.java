package ru.innovationcampus.vsu26.xokets.managers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

import ru.innovationcampus.vsu26.xokets.Settings;
import ru.innovationcampus.vsu26.xokets.game_objects.BulletObject;
import ru.innovationcampus.vsu26.xokets.game_objects.CargoObject;
import ru.innovationcampus.vsu26.xokets.game_objects.GameObject;

public class ContactManager {
    private World world;

    public ContactManager(World world) {
        this.world = world;
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixA = contact.getFixtureA();
                Fixture fixB = contact.getFixtureB();

                if (fixA.getFilterData().categoryBits == Settings.BULLET_CBITS && fixB.getFilterData().categoryBits == Settings.CARGO_CBITS) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((CargoObject) fixB.getUserData()).drop();
                }
                if (fixB.getFilterData().categoryBits == Settings.BULLET_CBITS && fixA.getFilterData().categoryBits == Settings.CARGO_CBITS) {
                    ((CargoObject) fixA.getUserData()).drop();
                    ((GameObject) fixB.getUserData()).hit();
                }
                if (fixA.getFilterData().categoryBits == Settings.BARREL_CBITS && fixB.getFilterData().categoryBits == Settings.SHOOTING_CAR_CBITS
                    || fixA.getFilterData().categoryBits == Settings.SHOOTING_CAR_CBITS && fixB.getFilterData().categoryBits == Settings.BARREL_CBITS) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                }
                if (fixA.getFilterData().categoryBits == Settings.BARREL_CBITS && fixB.getFilterData().categoryBits == Settings.BULLET_CBITS
                    || fixA.getFilterData().categoryBits == Settings.BULLET_CBITS && fixB.getFilterData().categoryBits == Settings.BARREL_CBITS) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                }
                if (fixA.getFilterData().categoryBits == Settings.TRUCK_CBITS && fixB.getFilterData().categoryBits == Settings.BULLET_CBITS
                    || fixA.getFilterData().categoryBits == Settings.BULLET_CBITS && fixB.getFilterData().categoryBits == Settings.TRUCK_CBITS) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                }
                if (fixA.getFilterData().categoryBits == Settings.TRUCK_CBITS && fixB.getFilterData().categoryBits == Settings.BARREL_CBITS
                    || fixA.getFilterData().categoryBits == Settings.BARREL_CBITS && fixB.getFilterData().categoryBits == Settings.TRUCK_CBITS) {
                    ((GameObject) fixA.getUserData()).hit();
                    ((GameObject) fixB.getUserData()).hit();
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }
}
