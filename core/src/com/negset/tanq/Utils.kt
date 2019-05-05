package com.negset.tanq

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.graphics.Texture

fun Controller.isAnyButton(): Boolean
{
    for (i in 0 until 10)
        if (getButton(i)) return true
    return false
}

fun Texture.filtered(): Texture = apply {
    setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
}
