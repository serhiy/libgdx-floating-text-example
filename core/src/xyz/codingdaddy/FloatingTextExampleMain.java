package xyz.codingdaddy;

import java.util.concurrent.TimeUnit;

import xyz.codingdaddy.hud.FloatingText;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class FloatingTextExampleMain extends ApplicationAdapter {

	private Stage stage;
	private FloatingText floatingText;

	@Override
	public void create() {
		stage = new Stage();

		floatingText = new FloatingText("Beep beep!!!", TimeUnit.SECONDS.toMillis(2));
		floatingText.setPosition(10, 10);
		floatingText.setDeltaY(200);
		
		stage.addActor(floatingText);
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (!floatingText.isAnimated()) {
			floatingText.animate();
		}
		
		stage.act();
		stage.draw();
	}

	@Override
	public void dispose() {
		stage.dispose();
	}
}
