package com.strikeweek.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
    int x, y;
    Texture texture;
    Sprite sprite;

    Player(int x, int y) {
        this.x = x;
        this.y = y;
        texture = new Texture("p1_stand.png");
        sprite = new Sprite(texture);
    }
}
