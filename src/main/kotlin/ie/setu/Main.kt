package ie.setu

import kotlin.math.round
import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging
import ie.setu.utils.ScannerInput.readNextInt


val logger = KotlinLogging.logger {}
//var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)
var employees = EmployeeAPI()
fun main(args: Array<String>){
    logger.info { "Launching Employee App\n" }
    start()
}
fun menu() : Int {

    /* Code Reference for what I used to add colour to parts of my functions:
    https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#deletion
    To improve the UX/UI of my employee app, I added colours to parts of my menu, payslip and list functions
    by using and adapting the referenced code to suit my project
    */

    // Displays the colour
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"
    val backgroundBrightGreen = "\u001b[42;1m"

   // Resets back to previous colour
    val reset = "\u001b[0m"

    print(""" 
         |   $backgroundBlue$black ️         $bold$black   Employee Menu             $reset 
         |   $backgroundBlue   $reset 1 → 👩‍💼Add Employee               $backgroundBlue   $reset
         |   $backgroundBlue   $reset 2 → ✏️Update Employee            $backgroundBlue   $reset
         |   $backgroundBlue   $reset 3 → 🗑Delete Employee            $backgroundBlue   $reset
         |   $backgroundBlue   $reset 4 → 📝List All Employees         $backgroundBlue   $reset
         |   $backgroundBlue   $reset 5 → 🔎Search Employees by ID     $backgroundBlue   $reset
         |   $backgroundBlue   $reset 6 → 🔎Search Employees by Name   $backgroundBlue   $reset
         |   $backgroundBlue   $reset 7 → 📂Sort & Filter Employees    $backgroundBlue   $reset
         |   $backgroundBlue   $reset 8 → 🖨Print Employee Payslip     $backgroundBlue   $reset
         |   $backgroundBlue   $reset 0 → 👋Exit                       $backgroundBlue   $reset
         |   $backgroundBlue                   💼                   $reset
         |   
         |   Enter Option: """.trimMargin())

    return readLine()!!.toInt()

}
fun start() {
    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> add()
            2 -> updateEmployee()
            3 -> deleteEmployee()
            4 -> list()
            5 -> search()
            6 -> searchByName()
            7 -> sortMenuInput()
            8 -> paySlip()
            -99 -> dummyData()
            0 -> logger.info { "Exiting App, thank you for using!\n" } //println("Exiting App, thank you for using!")
            else -> logger.info{"Invalid Option\n"}
        }
        println()
    } while (input != 0)
}
fun add(){
    logger.info { "\nYou are adding a new Employee to the list\n" }
    print("\n" )
    print("   Enter First name: ")
    val firstName = readLine().toString()
    print("   Enter Surname: ")
    val lastName = readLine().toString()
    print("   Enter Gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("   Enter Gross Salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("   Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("   Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("   Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("   Enter Cycle to Work Deduction: ")
    val ctwS= readLine()!!.toDouble()

    employees.create(Employee(firstName, lastName, gender, 0, grossSalary, payePercentage, prsiPercentage, annualBonus, ctwS))
}

/*
Code Reference for updateEmployee() and deleteEmployee(): https://github.com/sdrohan/notes-app/blob/master/src/main/kotlin/Main.kt
To add the functionality to update and delete employees from the list, I took inspiration from certain sections of the referenced code
and changed the code around in order to make it work for what I needed it to do
*/
fun updateEmployee() {
    logger.info { "updateEmployee() function invoked\n" }
    list()
    if (employees.numberOfEmployees() > 0) {
        // only ask the user to choose the employee if employee exists
        val indexToUpdate = readNextInt("Enter the ID of the Employee to update: ")
        if (employees.isValidIndex(indexToUpdate)) {
            print("Enter First name: ")
            val firstName = readLine().toString()
            print("Enter Surname: ")
            val lastName = readLine().toString()
            print("Enter Gender (m/f): ")
            val gender = readLine()!!.toCharArray()[0]
            print("Enter Employee ID: ")
            val employeeId = readLine()!!.toInt()
            print("Enter Gross Salary: ")
            val grossSalary = readLine()!!.toDouble()
            print("Enter PAYE %: ")
            val payePercentage = readLine()!!.toDouble()
            print("Enter PRSI %: ")
            val prsiPercentage = readLine()!!.toDouble()
            print("Enter Annual Bonus: ")
            val annualBonus= readLine()!!.toDouble()
            print("Enter Cycle to Work Deduction: ")
            val ctwS= readLine()!!.toDouble()

            // pass the index of the employee and the new employee details to EmployeeAPI for updating and check for success.
            if (employees.updateEmployee(indexToUpdate, Employee(firstName, lastName, gender, employeeId, grossSalary,payePercentage, prsiPercentage, annualBonus, ctwS))) {
                println("Employee information updated successfully")
            } else {
                logger.info { "Update Failed\n" }
            }
        } else {
            logger.info{"There are no employees for this index number\n"}
        }
    }
}
fun deleteEmployee() {
    list()
    if (employees.numberOfEmployees() > 0) {
        // only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the ID of the Employee to delete: ")
        // pass the index of the note to EmployeeAPI for deleting and check for success.
        val employeeToDelete = employees.deleteEmployee(indexToDelete)
        if (employeeToDelete != null) {
            logger.info{"Delete Successful! Deleted Employee: ${employeeToDelete.getFullEmployeeName()}\n"}
        } else {
            logger.info{"Delete NOT Successful"}
        }
    }
}
fun list(){
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"
    val reset = "\u001b[0m"
    print("\n   $backgroundBlue$black$bold Employee List $reset\n")
        employees.findAll()
            .forEach{ println(it) }

}
fun search() {
    val employee = getEmployeeById()
    if (employee == null)
        logger.info{"No employee found\n"}
    else
        println(employee)
}
fun searchByName()
{
    val employeeName = getEmployeeByName()

    if (employeeName == null)
        logger.info{"No employee found\n"}
    else
        println(employeeName)
}

fun sortMenu() : Int {
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"
    val reset = "\u001b[0m"
    print("\n")
    print(""" 
         |   $backgroundBlue$black ️   $bold$black  Sort & Filter Employee Menu      $reset 
         |   $backgroundBlue   $reset 1 → 💰Sort by salary             $backgroundBlue   $reset
         |   $backgroundBlue   $reset 2 → 📝Sort by name               $backgroundBlue   $reset
         |   $backgroundBlue   $reset 3 → ☑️Filter by last name        $backgroundBlue   $reset
         |   $backgroundBlue   $reset 0 → 👋Exit                       $backgroundBlue   $reset
         |   $backgroundBlue                   💼                   $reset
         |   
         |   Enter Option: """.trimMargin())

    return readLine()!!.toInt()

}

fun sortMenuInput() {
    var input: Int

    do {
        input = sortMenu()
        when (input) {
            1 -> sortSalaries()
            2 -> sortEmployeeNames()
            3 -> filterNames()
            -99 -> dummyData()
            0 -> logger.info { "Exiting Sort & Filter Employee Menu\n" } //println("Exiting App, thank you for using!")
            else -> logger.info{"Invalid Option\n"}
        }
        println()
    } while (input != 0)
}

//Code reference for sort by employee name and salary : https://www.codevscolor.com/kotlin-5-ways-sort-list-ascending-descending
fun sortEmployeeNames(){
    logger.info{"Sorting employees names in alphabetical order\n"}
    return employees.sortEmployeeNames().forEach{println(it)}

}
fun sortSalaries(){
    logger.info{"Sorting employees by lowest to highest Gross Salary\n"}
    return employees.sortSalary().forEach{println(it)}
}

//Filter code reference: https://reader.tutors.dev/#/lab/sdt-sept-2022.netlify.app/topic-03-kotlin-and-gradle/unit-02-labs/book-01-classes-and-collections/05
fun filterNames(){
    logger.info{"Filtering employees with the surname Roche\n"}
    employees.filterName()
        .filter {it.lastName.contains("Roche" )}
        .forEach { println(it) }
}
internal fun getEmployeeById(): Employee? {
    print("   Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}
internal fun getEmployeeByName(): Employee? {
    print("   Enter the employee name to search by: ")
    val employeeName = readLine()!!.toString()
    return employees.findName(employeeName)
}
fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getEmployeeInfo())
    else
        logger.info{"No employee found linked to that ID\n"}
}
fun dummyData() {
    employees.create(Employee("Cian", "Burns", 'M', 0, 105655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Emma", "Roche", 'F', 1, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("John", "Walsh", 'M', 2, 75685.41, 40.0, 8.3, 4500.0, 0.0))
    employees.create(Employee("Anita", "Blogs", 'F', 3, 209782.35, 50.0, 9.4, 12000.0, 3.6))
    employees.create(Employee("Philip", "Roche", 'M', 4, 35467.87, 27.8, 6.4, 500.0, 1.2))
}
fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

