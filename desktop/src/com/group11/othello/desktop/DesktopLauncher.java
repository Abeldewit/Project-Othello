package com.group11.othello.desktop;

import com.group11.othello.AI.Testing;
import com.group11.othello.Game.Othello;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import java.io.FileNotFoundException;
import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Othello.WIDTH;
		config.height = Othello.HEIGHT;
		new LwjglApplication(new Othello(), config);
		Testing test = null;

	}
}
