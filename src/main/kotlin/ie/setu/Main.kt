package ie.setu

import kotlin.math.round
import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging
import ie.setu.utils.ScannerInput.readNextInt

val logger = KotlinLogging.logger {}
var employees = EmployeeAPI()

fun main(args: Array<String>){
    logger.info { "Launching Employee App\n" }
    start()
}

//employee menu
fun menu() : Int {

    //code reference for adding colour to improve UI: https://www.lihaoyi.com/post/BuildyourownCommandLinewithANSIescapecodes.html#deletion

    // displays the colour
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"

   // resets colour back to what it previously was
    val reset = "\u001b[0m"

    print(""" 
         |   $backgroundBlue$black ️         $bold$black  Employee Menu              $reset 
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
            1 -> addEmployee()
            2 -> updateEmployee()
            3 -> deleteEmployee()
            4 -> listEmployees()
            5 -> searchByID()
            6 -> searchByName()
            7 -> sortMenuInput()
            8 -> paySlip()
            -99 -> dummyData()
            0 -> logger.info { "Exiting App, thank you for using!\n" }
            else -> logger.info{"Invalid Option\n"}
        }
        println()
    } while (input != 0)
}

//add employee
fun addEmployee(){
    logger.info { "You are adding a new Employee to the list\n" }
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

//code reference for updateEmployee() and deleteEmployee(): https://github.com/sdrohan/notes-app/blob/master/src/main/kotlin/Main.kt

//update employee
fun updateEmployee() {
    logger.info { "Updating an employee\n" }
    listEmployees()
    if (employees.numberOfEmployees() > 0) {
        // only ask the user to choose the employee if employee exists
        val indexToUpdate = readNextInt("   Enter the ID of the Employee you wish to update: ")
        if (employees.isValidIndex(indexToUpdate)) {
            print("   Enter First name: ")
            val firstName = readLine().toString()
            print("   Enter Surname: ")
            val lastName = readLine().toString()
            print("   Enter Gender (m/f): ")
            val gender = readLine()!!.toCharArray()[0]
            print("   Enter Employee ID: ")
            val employeeId = readLine()!!.toInt()
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

            // pass the index of the employee and the new employee details to EmployeeAPI for updating and check for success.
            if (employees.updateEmployee(indexToUpdate, Employee(firstName, lastName, gender, employeeId, grossSalary,payePercentage, prsiPercentage, annualBonus, ctwS))) {
                logger.info{"Employee information updated successfully\n"}
            } else {
                logger.info { "Update Failed\n" }
            }
        } else {
            logger.info{"There are no employees matching this ID\n"}
        }
    }
}

//delete employee
fun deleteEmployee() {
    logger.info { "Deleting an employee\n" }
    listEmployees()
    if (employees.numberOfEmployees() > 0) {
        // only ask the user to choose the employee to delete if the employee exist
        val indexToDelete = readNextInt("   Enter the ID of the Employee you wish to delete: ")
        // pass the ID of the employee to EmployeeAPI for deleting and check for success.
        val employeeToDelete = employees.deleteEmployee(indexToDelete)
        if (employeeToDelete != null) {

            logger.info{"\nDelete Successful! Deleted Employee: ${employeeToDelete.getFullEmployeeName()}\n"}

        } else {
            logger.info{"Delete NOT Successful\n"}
        }
    }
}

//list all employees
fun listEmployees(){
    logger.info{"Listing all employees\n"}
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"
    val reset = "\u001b[0m"
    print("\n   $backgroundBlue$black$bold Employee List $reset\n")
        employees.findAll()
            .forEach{ println(it) }

}

//search employee by ID
fun searchByID() {
    logger.info{"You are searching for an employee by ID\n"}
    print("\n")
    val employee = getEmployeeById()
    if (employee == null)
        logger.info{"No employee found\n"}
    else
        println(employee)
}

internal fun getEmployeeById(): Employee? {
    print("   Enter the employee ID to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

//search employee by name
fun searchByName() {
    logger.info{"You are searching for an employee by first name\n"}
    print("\n")
    val employeeName = getEmployeeByName()

    if (employeeName == null)
        logger.info{"No employee found\n"}
    else
        println(employeeName)
}

internal fun getEmployeeByName(): Employee? {
    print("   Enter the employee name to search by: ")
    val employeeName = readLine()!!.toString()
    return employees.findName(employeeName)
}

//sorting & filtering menu
fun sortMenu() : Int {
    val backgroundBlue = "\u001b[44m"
    val black = "\u001b[30m"
    val bold = "\u001b[1m"
    val reset = "\u001b[0m"
    print("\n")
    print(""" 
         |   $backgroundBlue$black ️   $bold$black      Sort & Filter Employee Menu           $reset 
         |   $backgroundBlue   $reset 1 →$bold 💰Sort salaries by:$reset high to low       $backgroundBlue   $reset
         |   $backgroundBlue   $reset 2 →$bold 💰Sort salaries by:$reset low to high       $backgroundBlue   $reset
         |   $backgroundBlue   $reset 3 → 📝Sort names in alphabetical order    $backgroundBlue   $reset
         |   $backgroundBlue   $reset 4 → ☑️Filter surnames beginning with B    $backgroundBlue   $reset
         |   $backgroundBlue   $reset 5 → ☑️Filter names with the surname Roche $backgroundBlue   $reset
         |   $backgroundBlue   $reset 0 → 👋Exit                                $backgroundBlue   $reset
         |   $backgroundBlue                       💼                        $reset
         |   
         |   Enter Option: """.trimMargin())

    return readLine()!!.toInt()

}

fun sortMenuInput() {
    var input: Int

    do {
        input = sortMenu()
        when (input) {
            1 -> sortSalariesHighToLow()
            2 -> sortSalariesLowToHigh()
            3 -> sortEmployeeNames()
            4 -> filterNames()
            5 -> filterNames2()
            -99 -> dummyData()
            0 -> logger.info { "Exiting Sort & Filter Employee Menu\n" } //println("Exiting App, thank you for using!")
            else -> logger.info{"Invalid Option\n"}
        }
        println()
    } while (input != 0)
}

/*code reference for sorting & filtering: https://reader.tutors.dev/#/lab/sdt-sept-2022.netlify.app/topic-03-kotlin-and-gradle/unit-02-labs/book-01-classes-and-collections/05
 & https://www.codevscolor.com/kotlin-5-ways-sort-list-ascending-descending */

//sort employee names in alphabetical order
fun sortEmployeeNames(){
    logger.info{"Sorting employees first names in alphabetical order\n"}
    return employees.sortEmployeeNames().forEach{println(it)}

}

//sort employees by lowest to highest gross salary earnings
fun sortSalariesLowToHigh(){
    logger.info{"Sorting employees by lowest to highest Gross Salary earnings\n"}
    return employees.sortSalary().forEach{println(it)}
}

//sort employees by highest to lowest gross salary earnings
fun sortSalariesHighToLow(){
    logger.info{"Sorting employees by highest to lowest Gross Salary earnings\n"}
    return employees.sortSalary2().forEach{println(it)}
}

//filter and find employees with surnames that begin with the letter B
fun filterNames(){
    logger.info{"Filtering employees by surnames that begin with the letter B\n"}
    employees.filterName()
        .filter {it.lastName.contains("B" )}
        .forEach { println(it) }
}

//filter and find employees with the surname Roche
fun filterNames2(){
    logger.info{"Filtering employees with the surname Roche\n"}
    employees.filterName()
        .filter {it.lastName.contains("Roche" )}
        .forEach { println(it) }
}

//print employee payslips
fun paySlip(){
    logger.info{"Printing employee payslip\n"}
    print("\n")
    val employee = getEmployeePayslip()
    if (employee != null)
        println(employee.getEmployeeInfo())
    else
        logger.info{"No employee found linked to that ID\n"}
}

internal fun getEmployeePayslip(): Employee? {
    print("   Enter the employee ID to view their payslip: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}

fun dummyData() {
    employees.create(Employee("Cian", "Burns", 'M', 0, 105655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Emma", "Roche", 'F', 1, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("John", "Walsh", 'M', 2, 75685.41, 40.0, 8.3, 4500.0, 0.0))
    employees.create(Employee("Ann", "Brophy", 'F', 3, 209782.35, 50.0, 9.4, 12000.0, 3.6))
    employees.create(Employee("Philip", "Roche", 'M', 4, 35467.87, 27.8, 6.4, 500.0, 1.2))
}

fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

