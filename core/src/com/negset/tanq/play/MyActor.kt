package com.negset.tanq.play

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

open class MyActor(field: Field) : Actor()
{
    val asset = field.asset
    val controller = field.controller

    var texture: Texture? = null
        set(value)
        {
            field = value
            field?.let {
                width = it.width.toFloat()
                height = it.height.toFloat()
            }
        }

    val leftEdge: Float
        get() = x - width / 2
    val rightEdge: Float
        get() = x + width / 2
    val bottomEdge: Float
        get() = y - height / 2
    val topEdge: Float
        get() = y + height / 2

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        batch?.setColor(color.r, color.g, color.b, color.a * parentAlpha)
        batch?.draw(texture, leftEdge, bottomEdge, width, height)
    }
}
