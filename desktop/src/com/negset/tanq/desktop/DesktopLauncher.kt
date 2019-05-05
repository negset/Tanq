package com.negset.tanq.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.negset.tanq.Tanq

object DesktopLauncher
{
    @JvmStatic
    fun main(arg: Array<String>)
    {
        val config = LwjglApplicationConfiguration().apply {
            width = 1280
            height = 720
            resizable = false
            title = "TANQ"
        }
        LwjglApplication(Tanq(), config)
    }
}
