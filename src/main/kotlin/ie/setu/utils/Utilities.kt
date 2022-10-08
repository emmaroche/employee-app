package ie.setu.utils

//code reference: https://github.com/sdrohan/notes-app/blob/master/src/main/kotlin/utils/Utilities.kt
object Utilities {
    @JvmStatic
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }
}