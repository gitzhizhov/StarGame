package com.mygdx.game.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.base.Ship;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;

public class Enemy extends Ship {

    private Vector2 approx = new Vector2(0, 0.7f);

    public Enemy(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        velocity = new Vector2();
        velocity0 = new Vector2();
        bulletV = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        // "быстрый" вылет сверху
        // стрельба только при полном вылете сверху
        if (this.getTop() > worldBounds.getTop()) {
            velocity.set(approx).rotate(180);
        }
        else
        {
            velocity.set(velocity0);
            autoShoot(delta);
        }

        if (getBottom() <= worldBounds.getBottom()) {
            destroy();
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int damage,
            float reloadInterval,
            Sound shootSound,
            int hp,
            float height
    ) {
        this.regions = regions;
        this.velocity0.set(v0);
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.damage = damage;
        this.reloadInterval = reloadInterval;
        this.reloadTimer = reloadInterval;
        this.shootSound = shootSound;
        this.hp = hp;
        this.velocity.set(velocity0);
        setHeightProportion(height);
    }
}
