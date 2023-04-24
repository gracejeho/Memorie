package pack;

import org.newdawn.slick.AppGameContainer;

import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) throws SlickException {
		// TODO Auto-generated method stub
		AppGameContainer app = new AppGameContainer(new Moteur("jeu MemoriesCards"));
		app.setTargetFrameRate(60);
		app.setShowFPS(false);
		app.setDisplayMode(1000, 1000, false);
		app.start();

	}

}
