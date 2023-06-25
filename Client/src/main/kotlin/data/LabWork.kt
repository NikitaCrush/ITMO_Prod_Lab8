package data

import java.time.LocalDateTime
import kotlinx.serialization.Serializable
import utils.LocalDateTimeSerializer

/**
 * A data class representing a lab work with various attributes.
 *
 * @property id The unique ID of the lab work.
 * @property name The name of the lab work.
 * @property coordinates The coordinates of the lab work.
 * @property creationDate The creation date of the lab work.
 * @property minimalPoint The minimal point of the lab work.
 * @property personalQualitiesMinimum The minimum personal qualities value.
 * @property difficulty The difficulty level of the lab work.
 * @property discipline The discipline associated with the lab work.
 */
@Serializable
data class LabWork(
    private var id: Long,
    private val name: String,       //Поле не может быть null, Строка не может быть пустой
    private val coordinates: Coordinates,//Поле не может быть null
    @Serializable(with = LocalDateTimeSerializer::class)
    private val creationDate: LocalDateTime,//Значение поля должно быть больше 0
    private val minimalPoint: Int,//Значение поля должно быть больше 0
    private val personalQualitiesMinimum: Int,//Поле не может быть null, Значение поля должно быть больше 0
    private val difficulty: Difficulty?,//Поле может быть null
    private val discipline: Discipline, //Поле не может быть null
    private var owner: String
) : Comparable<LabWork>, java.io.Serializable {

    fun getId(): Long{
        return id
    }
    fun getName(): String{
        return name
    }
    fun getMinimalPoint(): Int{
        return minimalPoint
    }
    fun getDifficulty(): Difficulty?{
        return difficulty
    }
    fun getPersonalQualitiesMinimum(): Int{
        return personalQualitiesMinimum
    }
    fun getCoordinates(): Coordinates{
        return coordinates
    }
    fun getDiscipline(): Discipline{
        return discipline
    }
    fun getOwner(): String{
        return owner
    }
    fun getCreationDate(): LocalDateTime{
        return creationDate
    }



    /**
     * Compares this LabWork object with another LabWork object by their IDs.
     *
     * @param other The LabWork object to compare with.
     * @return A negative value if this object is less than the other object, 0 if they are equal, and a positive value if this object is greater than the other object.
     */
    override fun compareTo(other: LabWork): Int {
        return id.compareTo(other.id)
    }
}




