package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;

public class ShipMain extends Sprite {

    private static final float HEIGHT = 0.1f;
    private static final float V_LEN = 0.2f;

    private Vector2 positionNew = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 tmpV = new Vector2(V_LEN, 0);
    private Vector2 tmp = new Vector2();
    private Rect worldBounds;

    public ShipMain(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("main_ship"));
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.positionNew.set(touch);
        if (touch.x > worldBounds.pos.x) {
            velocity.set(tmpV);
        }
        else {
            velocity.set(tmpV).rotate(180);
        }
        return false;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(velocity, delta);
        if (this.getRight() > worldBounds.getRight()) {
            this.setRight(worldBounds.getRight());
            velocity.set(0, 0);
        }
        if (this.getLeft() < worldBounds.getLeft()) {
            this.setLeft(worldBounds.getLeft());
            velocity.set(0, 0);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        this.pos.set(0f, worldBounds.getBottom() + HEIGHT);
    }

}
