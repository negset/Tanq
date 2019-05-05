package com.negset.tanq

import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.controllers.Controllers
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.freetype.registerFreeTypeFontLoaders

const val WIDTH = 1280f
const val HEIGHT = 720f

class Tanq : KtxGame<KtxScreen>()
{
    val asset = AssetManager().apply {
        registerFreeTypeFontLoaders()
    }
    val controller: Controller by lazy { Controllers.getControllers()[0] }

    override fun create()
    {
        addScreen(TitleScreen(this))
        setScreen<TitleScreen>()
    }
}
