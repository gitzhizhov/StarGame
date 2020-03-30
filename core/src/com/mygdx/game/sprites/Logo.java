package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;

import java.util.PriorityQueue;

public class Logo extends Sprite {

    float dt = Gdx.graphics.getDeltaTime();
    // для скорости
    private static final float V_LEN = 0.005f; // устанавливаем относительно размера экрана, а то скорость слишком большая

    private Vector2 positionNew = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 tmp = new Vector2();

    public Logo(Texture texture) throws GameException {
        super(new TextureRegion(texture));
    }

    // куда ткнулись в мировых координатах
    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        //
        this.positionNew.set(touch);
        // расчет скорости
        velocity.set(positionNew.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }

    @Override
    public void update(float delta) {
        tmp.set(positionNew);
        if (tmp.sub(pos).len() > V_LEN)
            pos.add(velocity);
        else
            pos.set(positionNew);
    }

//    @Override
//    public void draw(SpriteBatch batch) {
//        super.draw(batch);
//    }

    @Override
    public void resize(Rect worldBounds) {
        // размер относительно размера экрана
        setHeightProportion(0.2f);
        // позиция по центру
        //pos.set(worldBounds.pos);
    }
}
