package com.mygdx.game.math;

import com.mygdx.game.base.SpritesPool;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.sprites.Enemy;

public class EnemyPool extends SpritesPool<Enemy> {

    private BulletPool bulletPool;
    private Rect worldBounds;

    public EnemyPool(BulletPool bulletPool, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
    }

    @Override
    protected Enemy newObject() {
        return new Enemy(bulletPool, worldBounds);
    }
}
