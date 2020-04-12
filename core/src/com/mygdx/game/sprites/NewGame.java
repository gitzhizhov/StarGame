package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.mygdx.game.base.Sprite;
import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;

public class NewGame extends Sprite {

    public NewGame(TextureAtlas atlas) throws GameException {
        super(atlas.findRegion("button_new_game"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.07f);
        setBottom(-0.3f);
        //setTop(0.00001f);
    }
}
