package com.negset.tanq.play

import ktx.actors.isShown
import kotlin.math.abs

class Collision(private val tanks: List<Tank>,
                private val obstacles: List<Obstacle>,
                private val bullets: List<Bullet>)
{
    fun update()
    {
        tankAndObstacle()
        bulletAndTank()
        bulletAndObstacle()
    }

    private fun tankAndObstacle()
    {
        tanks.forEach { tank ->
            tank.run {
                hasLeftObstacle = false
                hasRightObstacle = false
                hasBottomObstacle = false
                hasTopObstacle = false
                for (obstacle in obstacles)
                {
                    val distX = obstacle.x - x
                    val distY = obstacle.y - y
                    if (abs(distX) > 48 || abs(distY) > 48) continue
                    if (abs(distX) + abs(distY) > 48 + 24) continue
                    if (abs(distX) > abs(distY))
                        if (distX < 0) hasLeftObstacle = true
                        else hasRightObstacle = true
                    else
                        if (distY < 0) hasBottomObstacle = true
                        else hasTopObstacle = true
                }
            }
        }
    }

    private fun bulletAndTank()
    {
        for (bullet in bullets)
        {
            for (tank in tanks)
            {
                if (bullet.isShown() && tank.isShown()
                        && tank.bounds.contains(bullet.x, bullet.y))
                {
                    bullet.collided()
                    tank.collided()
                }
            }
        }
    }

    private fun bulletAndObstacle()
    {
        loop@ for (bullet in bullets)
            for (obstacle in obstacles)
                if (obstacle != bullet.lastBounced &&
                        obstacle.bounds.contains(bullet.x, bullet.y))
                {
                    if (bullet.lastX in obstacle.x - 24..obstacle.x + 24)
                        bullet.bounceY()
                    else
                        bullet.bounceX()
                    bullet.lastBounced = obstacle
                    continue@loop
                }
    }
}
