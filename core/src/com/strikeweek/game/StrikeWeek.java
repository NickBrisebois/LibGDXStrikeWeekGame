package com.strikeweek.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class StrikeWeek extends ApplicationAdapter {
	SpriteBatch batch;
	World world;
	Player player;

	@Override
	public void create () {
		batch  = new SpriteBatch();
		player = new Player(100, 100);
		// This is the world the physics are contained inside for box2d. The vector is the gravity
		world = WorldHolder.getWorld();
	}

	@Override
	public void render () {
		// Clear the screen and then paint it blue
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//Super basic X axis movement for the player
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			player.moveX(-5f);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			player.moveX(5f);
		}

		WorldHolder.getWorld().step(1/60f, 6, 2);
		player.sprite.setPosition(player.body.getPosition().x, player.body.getPosition().y);

		// Draws the sprites
		batch.begin();
		player.sprite.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		// https://github.com/libgdx/libgdx/wiki/Memory-management
		batch.dispose();
		player.texture.dispose();
	}
}
