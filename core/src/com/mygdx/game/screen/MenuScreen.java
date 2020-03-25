package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture background;
    private Texture img;
    private Vector2 position; // текущая позиция
    private Vector2 positionNew; // новая позиция
    private Vector2 velocity; // скорость
//    private float rotate;

    @Override
    public void show() {
        super.show();
        background = new Texture("cosmos.jpg");
        img = new Texture("badlogic.jpg");
        position = new Vector2();
        positionNew = new Vector2();
        //velocity = new Vector2(0.3f, 0.3f);
        velocity = new Vector2();
//        rotate = 0;
    }

    @Override
    public void render(float delta) {
       update(delta);
       draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //position.set(screenX, Gdx.graphics.getHeight() - screenY);
        positionNew.set(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }

    private void update(float delta) {
        float dt = Gdx.graphics.getDeltaTime();
        Vector2 tmp = new Vector2(positionNew);

        // скорость зависит от растояния до место клика )))
        // замедляется при приближении
        //velocity.set(positionNew.cpy().sub(position).scl(dt));
        // установливаем скорость основываясь на длинне вектора между точками
        // по факту setLength(x) задает скорость перемещения
        velocity.set(positionNew.cpy().sub(position).setLength(100 * dt));
        // проверяем длинну разницы векторов
        if (tmp.sub(position).len() > 2)
            // если больше, то движимся
            position.add(velocity);
        else
            // иначе устанавливаем начальное положение в точку клика
            position.set(positionNew);
//        rotate += 1;
    }

    private void draw() {
        Gdx.gl.glClearColor(0.5f, 0.7f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, position.x, position.y);
//        batch.draw(new TextureRegion(img), pos.x, pos.y, pos.x, pos.y, 250, 250, 1, 1, rotate);
        batch.end();
    }

}
