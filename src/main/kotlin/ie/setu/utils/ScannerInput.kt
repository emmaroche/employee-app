package ie.setu.utils

import java.lang.NumberFormatException
import java.util.Scanner

//code reference: https://reader.tutors.dev/#/lab/sdt-sept-2022.netlify.app/topic-04-kotlin-and-junit/unit-02-labs/book-01-notes-app-v1/05

/**
 * This class provides methods for the robust handling of I/O using Scanner.
 * It creates a new Scanner object for each read from the user, thereby eliminating the Scanner bug
 * (where the buffers don't flush correctly after an int read).
 *
 * The methods also parse the numeric data entered to ensure it is correct. If it isn't correct,
 * the user is prompted to enter it again.
 *
 * @author Siobhan Drohan, Mairead Meagher
 * @since 1.0
 */

object ScannerInput {
    /**
     * Read an int from the user.
     * If the entered data isn't actually an int the user is prompted again to enter the int.
     *
     * @param prompt  The information printed to the console for the user to read
     * @return The number read from the user and verified as an int.
     */
    @JvmStatic
    fun readNextInt(prompt: String?): Int {
        do {
            try {
                print(prompt)
                return Scanner(System.`in`).next().toInt()
            } catch (e: NumberFormatException) {
                System.err.println("\tEnter a number please.")
            }
        } while (true)
    }
}