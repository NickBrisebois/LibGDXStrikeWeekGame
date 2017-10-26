package com.strikeweek.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Player {
    float x, y;
    Texture texture;
    Sprite sprite;
    BodyDef bodyDef = new BodyDef();
    Body body;

    Player(float x, float y) {
        this.x = x;
        this.y = y;

        // Sets the texture of the player's sprite and then sets a position for it
        texture = new Texture("p1_stand.png");
        sprite = new Sprite(texture);
        sprite.setPosition(x, y);

        // Sets the type of physics of the player and then sets the position to the sprites position
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(sprite.getX(), sprite.getY());

        // Creates a physics body in the physics world using bodyDef
        body = WorldHolder.getWorld().createBody(bodyDef);

        // Creates a box with physics and set it to over top of the player's sprite
        PolygonShape shape = new PolygonShape();
        // This line is fucked. For some reason the collision works better with the height and width swapped
        shape.setAsBox(sprite.getHeight()/2, sprite.getWidth()/2);

        // Sets the physics details of the sprite box
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        //fixtureDef.density = 1f;
        body.createFixture(fixtureDef);

        // Delete the shape from memory as it is no longer needed
        shape.dispose();
    }

    public void move() {
        Vector2 pos = body.getPosition();
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            body.applyLinearImpulse(-10f, 0, pos.x, pos.y, true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            body.applyLinearImpulse(10f, 0, pos.x, pos.y, true);
        }
    }
}
