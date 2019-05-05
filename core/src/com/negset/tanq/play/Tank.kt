package com.negset.tanq.play

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.lerpAngleDeg
import com.badlogic.gdx.math.Rectangle
import kotlin.math.atan2

open class Tank(private val field: Field) : MyActor(field)
{
    lateinit var body: TextureRegion
    lateinit var head: TextureRegion

    val speed = 2.5f
    var vx = 0f
    var vy = 0f

    var bodyDir = 0f
    var headDir = 0f

    var hasLeftObstacle = false
    var hasRightObstacle = false
    var hasBottomObstacle = false
    var hasTopObstacle = false

    val bounds = Rectangle()

    init
    {
        width = 64f
        height = 64f
    }

    fun init(x: Float, y: Float)
    {
        this.x = x
        this.y = y
    }

    override fun act(delta: Float)
    {
        if (vx != 0f || vy != 0f)
        {
            val to = atan2(vy, vx) * MathUtils.radDeg
            bodyDir = lerpAngleDeg(bodyDir, to, 0.1f)
        }

        if ((vx < 0 && !hasLeftObstacle) || (vx > 0 && !hasRightObstacle))
            x += vx
        if ((vy < 0 && !hasBottomObstacle) || (vy > 0 && !hasTopObstacle))
            y += vy
    }

    fun shootBullet()
    {
        val bx = x + 40 * MathUtils.cosDeg(headDir)
        val by = y + 40 * MathUtils.sinDeg(headDir)
        field.createBullet(bx, by, headDir)
    }

    fun collided()
    {
        remove()
    }

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        batch?.setColor(color.r, color.g, color.b, color.a * parentAlpha)
        batch?.draw(body, leftEdge, bottomEdge,
                width / 2, height / 2, width, height, 1f, 1f, bodyDir)
        batch?.draw(head, leftEdge, bottomEdge,
                width / 2, height / 2, width, height, 1f, 1f, headDir)
    }

    override fun positionChanged()
    {
        bounds.set(x - 48f / 2, y - 48f / 2, 48f, 48f)
    }
}
