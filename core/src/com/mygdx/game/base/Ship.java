package com.mygdx.game.base;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.game.exception.GameException;
import com.mygdx.game.math.Rect;
import com.mygdx.game.pool.BulletPool;
import com.mygdx.game.pool.ExplosionPool;
import com.mygdx.game.sprites.Bullet;
import com.mygdx.game.sprites.Explosion;

public abstract class Ship extends Sprite {

    private static final float DAMAGE_ANIMATE_INTERVAL = 0.1f;
    private static final float DELTA_COEFF = 1.2f;
    private float savedDelta = 0f;

    protected Rect worldBounds;
    protected BulletPool bulletPool;
    protected ExplosionPool explosionPool;
    protected TextureRegion bulletRegion;
    protected Vector2 bulletV;
    protected Vector2 bulletPos;
    protected Vector2 bulletPosLeft;
    protected Vector2 bulletPosRight;
    protected float bulletHeight;
    protected int damage;
    protected Sound shootSound;
    protected int hp;

    protected Vector2 velocity0;
    protected Vector2 velocity;

    protected float reloadInterval;
    protected float reloadTimer;
    protected float damageAnimateTimer = DAMAGE_ANIMATE_INTERVAL;

    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) throws GameException {
        super(region, rows, cols, frames);
    }

    @Override
    public void update(float delta) {
        // возвращает delta(time) после паузы
        if (savedDelta == 0f) {
            savedDelta = delta;
        }
        if (delta > savedDelta*DELTA_COEFF) {
            delta = savedDelta;
        }

        pos.mulAdd(velocity, delta);
        damageAnimateTimer += delta;
        if (damageAnimateTimer >= DAMAGE_ANIMATE_INTERVAL) {
            frame = 0;
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        boom();
    }

    public void damage(int damage) {
        damageAnimateTimer = 0f;
        frame = 1;
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }

    protected void autoShoot(float delta) {
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    protected void autoShootDual(float delta) {
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            dualShoot();
        }
    }

    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, bulletPos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    protected void dualShoot() {
        Bullet bulletLeft = bulletPool.obtain();
        Bullet bulletRight = bulletPool.obtain();
        bulletLeft.set(this, bulletRegion, bulletPosLeft, bulletV, bulletHeight, worldBounds, damage);
        bulletRight.set(this, bulletRegion, bulletPosRight, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }

    private void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(pos, getHeight());
    }
}
