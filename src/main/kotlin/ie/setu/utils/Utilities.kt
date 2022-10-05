package ie.setu.utils

//Code Reference: https://github.com/sdrohan/notes-app/blob/master/src/main/kotlin/utils/Utilities.kt
object Utilities {

    @JvmStatic
    fun validRange(numberToCheck: Int, min: Int, max: Int): Boolean {
        return numberToCheck in min..max
    }

    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}