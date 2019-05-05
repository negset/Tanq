package com.negset.tanq

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport
import com.negset.tanq.play.Field
import ktx.assets.load
import ktx.assets.unloadSafely

class PlayScreen(game: Tanq) : MyScreen(game)
{
    private val stage = Stage(FitViewport(WIDTH, HEIGHT))
    private val field by lazy { Field(game) }

    init
    {
        asset.load<Texture>("bg.png")
        asset.load<Texture>("player_body.png")
        asset.load<Texture>("player_head.png")
        asset.load<Texture>("enemy01_body.png")
        asset.load<Texture>("enemy01_head.png")
        asset.load<Texture>("cursor.png")
        asset.load<Texture>("bullet.png")
        asset.load<Texture>("obstacle.png")
    }

    override fun onFinishLoading()
    {
        stage.addActor(Image(asset.get<Texture>("bg.png")))
        stage.addActor(field)
    }

    override fun draw()
    {
        stage.draw()
    }

    override fun update(delta: Float)
    {
        stage.act(delta)

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            game.run {
                setScreen<TitleScreen>()
                removeScreen<PlayScreen>()
            }
            dispose()
        }
    }

    override fun resize(width: Int, height: Int)
    {
        stage.viewport.update(width, height)
    }

    override fun dispose()
    {
        stage.dispose()
        asset.unloadSafely("bg.png")
        asset.unloadSafely("player_base.png")
        asset.unloadSafely("player_head.png")
        asset.unloadSafely("enemy01_base.png")
        asset.unloadSafely("enemy01_head.png")
        asset.unloadSafely("cursor.png")
        asset.unloadSafely("bullet.png")
        asset.unloadSafely("obstacle.png")
    }
}
