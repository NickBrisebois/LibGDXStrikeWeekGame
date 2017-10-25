package com.strikeweek.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class WorldHolder {
    static World world;
    static WorldHolder worldHolder;

    private WorldHolder(){
       world = new World(new Vector2(0, -98f), true);
    }

    public static WorldHolder getWorldHolder(){
        if(worldHolder == null){
            worldHolder = new WorldHolder();
        }
        return worldHolder;
    }

    public static World getWorld(){
        return getWorldHolder().world;
    }
}
