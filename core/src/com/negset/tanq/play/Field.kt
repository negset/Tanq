package com.negset.tanq.play

import com.badlogic.gdx.scenes.scene2d.Group
import com.negset.tanq.HEIGHT
import com.negset.tanq.Tanq
import com.negset.tanq.WIDTH
import ktx.actors.isShown
import ktx.assets.file
import java.util.*

class Field(game: Tanq) : Group()
{
    val asset = game.asset
    val controller = game.controller

    lateinit var player: Player
    private val obstacles = mutableListOf<Obstacle>()
    private val bullets = mutableListOf<Bullet>()
    private val tanks = mutableListOf<Tank>()

    private val collision = Collision(tanks, obstacles, bullets)

    private var shootable = true

    val gameOver
        get() = !player.isShown()
    val gameClear
        get() = player.isShown() && tanks.size == 1

    init
    {
        val sizeX = 26
        val sizeY = 15
        val sprites = Array(sizeY) { List(sizeX) { "" } }
        val sc = Scanner(file("stage01.field").read())
        var y = 0
        while (sc.hasNext()) sprites[y++] = sc.nextLine().chunked(2)

        val offsetX = (WIDTH - 48f * sizeX) / 2 + 48f / 2
        val offsetY = HEIGHT - (HEIGHT - 48f * sizeY) / 2 - 48f / 2
        for ((spY, line) in sprites.withIndex())
        {
            for ((spX, sprite) in line.withIndex())
            {
                val actorX = offsetX + spX * 48f
                val actorY = offsetY - spY * 48f
                when (sprite)
                {
                    "01" -> Obstacle(this).let {
                        it.init(actorX, actorY)
                        obstacles.add(it)
                        addActorAt(0, it)
                    }
                    "11" -> Player(this).let {
                        it.init(actorX, actorY)
                        tanks.add(it)
                        player = it
                    }
                    "21" -> Enemy01(this).let {
                        it.init(actorX, actorY)
                        tanks.add(it)
                        addActor(it)
                    }
                }
            }
        }

        // playerは一番上に描画する.
        addActor(player)
    }

    override fun act(delta: Float)
    {
        super.act(delta)

        if (!shootable && !controller.getButton(5))
            shootable = true
        if (shootable && controller.getButton(5))
        {
            player.shootBullet()
            shootable = false
        }

        bullets.removeAll { !it.isShown() }
        tanks.removeAll { !it.isShown() }

        collision.update()
    }

    fun createBullet(x: Float, y: Float, dir: Float)
    {
        Bullet(this).let {
            it.init(x, y, dir)
            addActor(it)
            bullets.add(it)
        }
    }
}

