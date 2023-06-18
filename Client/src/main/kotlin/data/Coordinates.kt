package data

import kotlinx.serialization.Serializable

/**
 * A data class representing coordinates with x and y values.
 *
 * @property x The x coordinate value.
 * @property y The y coordinate value.
 */
@Serializable
data class Coordinates(
    private val x: Long,
    private val y: Double,
){
    fun getX(): Long{
        return x
    }
    fun getY(): Double{
        return y
    }
}

