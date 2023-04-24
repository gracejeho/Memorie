package pack;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Button {   // images manipulés pendant le programme

	private int x;
	private int y;

	private String name;

	public Button(String name) {
		x = 0;
		y = 0;
		this.name = name;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image init() throws SlickException {
		Image i = new Image(name);
		return i;
	}

	public void draw(int a, int b, Graphics g) throws SlickException {
		x = a;
		y = b;
		g.drawImage(init(), x, y);
	}

	public boolean appartenir(int a, int b, int l, int h) {
		return (a <= x + l && a >= x && b <= h + y && b >= y);
	}

	public boolean click(int l, int h, Input inp, boolean c) {
		int a = inp.getMouseX();
		int b = inp.getMouseY();

		return (c && appartenir(a, b, l, h));

	}

}
