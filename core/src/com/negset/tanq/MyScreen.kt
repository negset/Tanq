package com.negset.tanq

import ktx.app.KtxScreen

open class MyScreen(val game: Tanq) : KtxScreen
{
    private var loading = true
    val asset = game.asset
    val controller = game.controller

    override fun render(delta: Float)
    {
        if (loading)
        {
            drawLoading()
            updateLoading()
            if (asset.update())
            {
                onFinishLoading()
                loading = false
            }
        }
        else
        {
            draw()
            update(delta)
        }
    }

    open fun drawLoading()
    {
    }

    open fun updateLoading()
    {
    }

    open fun onFinishLoading()
    {
    }

    open fun draw()
    {
    }

    open fun update(delta: Float)
    {
    }
}
