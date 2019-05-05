package com.negset.tanq.play

import com.badlogic.gdx.math.Rectangle

class Obstacle(field: Field) : MyActor(field)
{
    val bounds = Rectangle()

    init
    {
        texture = asset.get("obstacle.png")
    }

    fun init(x: Float, y: Float)
    {
        this.x = x
        this.y = y
    }

    override fun positionChanged()
    {
        bounds.set(x - width / 2, y - height / 2, width, height)
    }
}
