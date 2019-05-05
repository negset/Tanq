package com.negset.tanq.play

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.MathUtils.*
import com.negset.tanq.filtered

class Enemy01(field: Field) : Tank(field)
{
    private var moveDir = 180f

    init
    {
        body = TextureRegion(asset.get<Texture>("enemy01_body.png").filtered())
        head = TextureRegion(asset.get<Texture>("enemy01_head.png").filtered())
        headDir = 180f
        bodyDir = 180f
    }

    override fun act(delta: Float)
    {
        moveDir += random(-10, 10)

        vx = speed * cosDeg(moveDir)
        vy = speed * sinDeg(moveDir)

        super.act(delta)
    }
}
