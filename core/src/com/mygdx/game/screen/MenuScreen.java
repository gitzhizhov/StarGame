package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.BaseScreen;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.sprites.Background;
import com.mygdx.game.sprites.Logo;

public class MenuScreen extends BaseScreen {

    private Texture bg; // задний фон
    private Texture lg; // "логотип" летающий
    private Background background;
    private Logo logo;
    //private Vector2 pos;

    @Override
    public void show() {
        super.show();
        lg = new Texture("badlogic.jpg");
        bg = new Texture("textures/cosmos.jpg");
        //pos = new Vector2();

        try {
            background = new Background(bg);
            logo = new Logo(lg);
        } catch (GameException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        try {
//            logo = new Logo(lg);
//        } catch (GameException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
        lg.dispose();
        super.dispose();
    }

    @Override
    public void resize(Rect worldBounds) {
        background.resize(worldBounds);
        logo.resize(worldBounds);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        //pos.set(touch);
        logo.touchDown(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        logo.update(delta); // блин забыл обновлять
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch); // сначало задний фон
        logo.draw(batch); // потом "логотип"
        batch.end();
    }

}
