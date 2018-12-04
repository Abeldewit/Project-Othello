package com.group11.othello.desktop;

import com.group11.othello.Game.Othello;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Othello.WIDTH;
		config.height = Othello.HEIGHT;
		new LwjglApplication(new Othello(), config);
	}
}
