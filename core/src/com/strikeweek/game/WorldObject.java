package com.strikeweek.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

// This class is basically the same as player but is solid and doesn't move
public class WorldObject {
    float x, y;
    Texture texture;
    Sprite sprite;
    BodyDef bodyDef = new BodyDef();
    Body body;

    WorldObject(float x, float y, String spriteImage){
        texture = new Texture(spriteImage);
        sprite  = new Sprite(texture);
        sprite.setPosition(x, y);

        bodyDef.position.set(sprite.getX(), sprite.getY());
        body = WorldHolder.getWorld().createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);
        body.createFixture(shape, 0.0f);
        shape.dispose();
    }
}
