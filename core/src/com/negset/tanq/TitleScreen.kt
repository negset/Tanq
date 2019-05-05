package com.negset.tanq

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.assets.getValue
import ktx.assets.load
import ktx.assets.unloadSafely
import ktx.freetype.loadFreeTypeFont
import ktx.graphics.use

class TitleScreen(game: Tanq) : MyScreen(game)
{
    private val stage = Stage(FitViewport(WIDTH, HEIGHT))

    private val font by asset.loadFreeTypeFont("font.ttf") {
        size = 40
        color = Color.WHITE
        borderColor = Color.DARK_GRAY
        borderWidth = 3f
        magFilter = Texture.TextureFilter.Linear
        minFilter = Texture.TextureFilter.Linear
        incremental = true
    }

    init
    {
        asset.load<Texture>("bg.png")
    }

    override fun onFinishLoading()
    {
        stage.addActor(Image(asset.get<Texture>("bg.png")))
    }

    override fun draw()
    {
        stage.draw()
        stage.batch.use {
            font.draw(it, "TANQ", WIDTH / 2 - 64, 450f)
            font.draw(it, "© 2019 negset", WIDTH / 2 - 160, 150f)
        }
    }

    override fun update(delta: Float)
    {
        stage.act(delta)

        if (controller.isAnyButton())
        {
            game.run {
                addScreen(PlayScreen(this))
                setScreen<PlayScreen>()
            }
        }
    }

    override fun dispose()
    {
        asset.unloadSafely("font.ttf")
        asset.unloadSafely("bg.png")
    }
}
