package ie.setu.controllers
import ie.setu.models.Employee
import ie.setu.utils.Utilities.isValidListIndex

var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()
    fun findAll(): List<Employee> {
        return employees
    }
    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeId == id }
    }
    fun create(employee: Employee) {
        employee.employeeId = getId()
        employees.add(employee)
    }
    fun updateEmployee(indexToUpdate: Int, employee: Employee?): Boolean {
        // find the note object by the index number
        val foundEmployee = findEmployee(indexToUpdate)

        // if the note exists, use the note details passed as parameters to update the found note in the ArrayList.
        if ((foundEmployee != null) && (employee != null)) {
            foundEmployee.firstName = employee.firstName
            foundEmployee.lastName= employee.lastName
            foundEmployee.gender = employee.gender
            return true
        }

        // if the note was not found, return false, indicating that the update was not successful
        return false
    }

    fun findEmployee(index: Int): Employee? {
        return if (isValidListIndex(index, employees)) {
            employees[index]
        } else null
    }
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, employees)
    }
    fun numberOfEmployees(): Int = employees.size

}