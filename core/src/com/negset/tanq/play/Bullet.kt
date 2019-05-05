package com.negset.tanq.play

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils.cosDeg
import com.badlogic.gdx.math.MathUtils.sinDeg
import com.negset.tanq.filtered

class Bullet(field: Field) : MyActor(field)
{
    private val region =
            TextureRegion(asset.get<Texture>("bullet.png").filtered())

    var lastX = 0f
        private set
    var lastY = 0f
        private set
    var lastBounced: Obstacle? = null

    private val speed = 5f
    private var deg: Float = 0f
    private var bouncedCount = 0

    init
    {
        width = 16f
        height = 16f
    }

    fun init(x: Float, y: Float, deg: Float)
    {
        this.x = x
        this.y = y
        this.deg = deg
        lastX = x
        lastY = y
    }

    override fun act(delta: Float)
    {
        lastX = x
        lastY = y
        x += speed * cosDeg(deg)
        y += speed * sinDeg(deg)
    }

    fun bounceX()
    {
        deg = 180 - deg
        if (++bouncedCount > 1) collided()
    }

    fun bounceY()
    {
        deg = -deg
        if (++bouncedCount > 1) collided()
    }

    fun collided()
    {
        remove()
    }

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        batch?.draw(region, leftEdge, bottomEdge,
                width / 2, height / 2, width, height, 1f, 1f, deg)
    }
}
