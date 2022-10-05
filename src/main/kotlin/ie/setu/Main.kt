package ie.setu

import kotlin.math.round
import ie.setu.controllers.EmployeeAPI
import ie.setu.models.Employee
import mu.KotlinLogging
import ie.setu.utils.ScannerInput.readNextInt

val logger = KotlinLogging.logger {}
var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)
var employees = EmployeeAPI()

fun main(args: Array<String>){
    logger.info { "Launching Employee App" }
    start()
}
fun menu() : Int {
    print(""" 
         |Employee Menu
         |   1. Add Employee
         |   2. Update Employee
         |   3. Delete Employee
         |   4. List All Employees
         |   5. Sort Employees
         |   6. Search Employees 
         |   7. Print Payslip for Employee
         |  -1. Exit
         |       
         |Enter Option : """.trimMargin())
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
            5 -> sort()
            6 -> search()
            7 -> paySlip()
            -99 -> dummyData()
            -1 -> logger.info { "Exiting App, thank you for using!" } //println("Exiting App, thank you for using!")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}
fun add(){
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
    logger.info { "deleteEmployee() function invoked\n" }
    list()
    if (employees.numberOfEmployees() > 0) {
        // only ask the user to choose the note to delete if notes exist
        val indexToDelete = readNextInt("Enter the ID of the Employee to delete: ")
        // pass the index of the note to NoteAPI for deleting and check for success.
        val employeeToDelete = employees.deleteEmployee(indexToDelete)
        if (employeeToDelete != null) {
            println("Delete Successful! Deleted Employee: ${employeeToDelete.firstName}")
        } else {
            logger.info{"Delete NOT Successful"}
        }
    }
}
fun list(){
    employees.findAll()
        .forEach{ println(it) }
}
fun sort(){
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
internal fun getEmployeeById(): Employee? {
    print("Enter the employee id to search by: ")
    val employeeID = readLine()!!.toInt()
    return employees.findOne(employeeID)
}
fun paySlip(){
    val employee = getEmployeeById()
    if (employee != null)
        println(employee.getEmployeeInfo())
}
fun dummyData() {
    employees.create(Employee("Cian", "Burns", 'M', 1, 35655.43, 31.0, 7.5, 2000.0, 25.6))
    employees.create(Employee("Emma", "Roche", 'F', 2, 54255.13, 32.5, 7.0, 1500.0, 55.3))
    employees.create(Employee("John", "Walsh", 'M', 3, 75685.41, 40.0, 8.5, 4500.0, 0.0))
}
fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

