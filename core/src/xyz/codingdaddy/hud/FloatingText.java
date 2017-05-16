package xyz.codingdaddy.hud;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Floating text GUI component with fading text.
 * 
 * @author serhiy
 */
public class FloatingText extends Actor {

	private final String text;
	private final long animationDuration;
	private boolean animated = false;
	private long animationStart;
	private float deltaX;
	private float deltaY;

	private BitmapFont font = new BitmapFont();

	public FloatingText(String text, long animationDuration) {
		this.text = text;
		this.animationDuration = animationDuration;
	}

	public void setDeltaX(float deltaX) {
		this.deltaX = deltaX;
	}

	public void setDeltaY(float deltaY) {
		this.deltaY = deltaY;
	}

	public void animate() {
		animated = true;
		animationStart = System.currentTimeMillis();
	}

	public boolean isAnimated() {
		return animated;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		if (animated) {
			// The component will auto-destruct when animation is finished.
			if (isDisposable()) {
				dispose();
				return;
			}

			float elapsed = System.currentTimeMillis() - animationStart;

			// The text will be fading.
			font.setColor(getColor().r, getColor().g, getColor().b, parentAlpha
					* (1 - elapsed / animationDuration));

			font.draw(batch, text, getX() + deltaX * elapsed / 1000f, getY()
					+ deltaY * elapsed / 1000f);
		}
	}

	/**
	 * @return true is the animation has finished.
	 */
	private boolean isDisposable() {
		return animationStart + animationDuration < System.currentTimeMillis();
	}

	/**
	 * Dispose the component. <b>Note that all the children components also should
	 * be disposed otherwise a memory leak will occur.</b>
	 */
	private void dispose() {
		font.dispose();
		remove();
	}
}