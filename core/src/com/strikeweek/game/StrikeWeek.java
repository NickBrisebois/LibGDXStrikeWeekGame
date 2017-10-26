package com.strikeweek.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class StrikeWeek extends ApplicationAdapter {
	SpriteBatch batch; // Contains the sprites
	World world;       // The physics world
	Player player;	   // The player object
	WorldObject[] worldObjects = new WorldObject[8]; // Temporary world objects acting as a floor

	@Override
	public void create () {
		// The sprite batch contains all of the sprites
		batch  = new SpriteBatch();

		// Create the objects used to draw the sprites and their physics bodies
		player = new Player(100, 400);
		for(int i=0; i<worldObjects.length; i++){
			worldObjects[i] = new WorldObject(70*i, 12, "boxAlt.png");
		}

		// World is contained inside the WorldHolder singleton
		world = WorldHolder.getWorld();
	}

	@Override
	public void render () {
		// Clear the screen and then paint it blue
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Steps the physics world. Needs to be changed in the future
		// https://gafferongames.com/post/fix_your_timestep/
		WorldHolder.getWorld().step(1/60f, 6, 2);

		// player.move contains the keyboard checks for controlling the player
		player.move();

		// Update the world objects by setting their positions using their body positions
		player.sprite.setPosition(player.body.getPosition().x, player.body.getPosition().y);
		for(int i=0; i<worldObjects.length; i++) {
			worldObjects[i].sprite.setPosition(worldObjects[i].body.getPosition().x, worldObjects[i].body.getPosition().y);
		}

		// Draws the sprites
		batch.begin();
		player.sprite.draw(batch);
		for(int i=0; i<worldObjects.length; i++) {
			worldObjects[i].sprite.draw(batch);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		// https://github.com/libgdx/libgdx/wiki/Memory-management
		batch.dispose();
		player.texture.dispose();
	}
}
