package com.negset.tanq.play

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils.*
import com.negset.tanq.filtered
import kotlin.math.atan2
import kotlin.math.round

class Player(field: Field) : Tank(field)
{
    private val cursor = asset.get<Texture>("cursor.png").filtered()

    private val bodyAxisX
        get() = round(100 * controller.getAxis(1)) / 100
    private val bodyAxisY
        get() = -round(100 * controller.getAxis(0)) / 100
    private val headAxisX
        get() = round(100 * controller.getAxis(3)) / 100
    private val headAxisY
        get() = -round(100 * controller.getAxis(2)) / 100

    init
    {
        body = TextureRegion(asset.get<Texture>("player_body.png").filtered())
        head = TextureRegion(asset.get<Texture>("player_head.png").filtered())
    }

    override fun act(delta: Float)
    {
        if (headAxisX != 0f || headAxisY != 0f)
        {
            val to = atan2(headAxisY, headAxisX) * radDeg
            headDir = lerpAngleDeg(headDir, to, 0.3f)
        }

        vx = speed * bodyAxisX
        vy = speed * bodyAxisY

        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        super.draw(batch, parentAlpha)

        for (i in 64 until 36 * 8 step 36)
        {
            batch?.draw(cursor,
                    x - 16 / 2 + i * cosDeg(headDir),
                    y - 16 / 2 + i * sinDeg(headDir))
        }
    }
}
