import kotlin.math.round

//val firstName = "Joe"
//val lastName = "Soap"
//val gender = "M"
//val employeeId = 6143
//val grossSalary = 67543.21
//val payePercentage = 38.5
//val prsiPercentage = 5.2
//val annualBonus =  1450.50
//val ctwS = 54.33

var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)
fun main(args: Array<String>){

    var input : Int

    do {
        add()
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPrsi()}")
            3 ->println("Monthly PAYE: ${getMonthlyPaye()}")
            4 -> println("Monthly Gross Pay: ${getGrossPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalDeductions()}")
            6 -> println("Monthly Net Pay: ${getMonthlyNetPay()}")
            7 -> println(getEmployeeInfo())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}

fun menu() : Int {
    print("""
         Employee Menu for ${getFullEmployeeName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
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

    employee = Employee(firstName, lastName, gender, employeeId, grossSalary, payePercentage, prsiPercentage, annualBonus, ctwS)
}
fun getFullEmployeeName() = when (employee.gender){
    'm', 'M' -> "Mr. ${employee.firstName} ${employee.lastName}"
    'f', 'F' -> "Ms. ${employee.firstName} ${employee.lastName}"
    else -> "${employee.firstName} ${employee.lastName}"
}

fun getMonthlySalary() = roundToTwoDecimalPlaces(employee.grossSalary/12)
fun getMonthlyPaye() = roundToTwoDecimalPlaces(getMonthlySalary() * (employee.payePercentage / 100))
fun getMonthlyPrsi() = roundToTwoDecimalPlaces(getMonthlySalary() * (employee.prsiPercentage / 100))
fun getGrossPay() = roundToTwoDecimalPlaces(getMonthlySalary() + (employee.annualBonus/12))
fun getTotalDeductions() = roundToTwoDecimalPlaces(getMonthlyPrsi() + getMonthlyPaye() + employee.ctwS)
fun getMonthlyNetPay() = roundToTwoDecimalPlaces(roundToTwoDecimalPlaces(getGrossPay() - getTotalDeductions()))
fun getEmployeeInfo(){

    println ( """
     |==================================================================|
     |                        Monthly Payslip                           |
     |==================================================================|
     |                                                                  |
     |  Employee Name:  ${getFullEmployeeName()}            Employee ID: ${employee.employeeId}      |
     |                                                                  |
     |==================================================================|
     |                                                                  | 
     | Payment Details:                                                 |
     |                                                                  |
     | Gross: ${getGrossPay()}                                                   |
     | Salary: ${getMonthlySalary()}                                                   |           
     | Bonus:  ${roundToTwoDecimalPlaces(employee.annualBonus / 12)}                                                   |
     |                                                                  |
     |==================================================================|                                                                 | 
     |                                                                  | 
     | Deduction Details:                                               |
     |                                                                  |
     | Total Deductions: ${getTotalDeductions()}                                        |
     | PAYE: ${getMonthlyPaye()}                                                    |     
     | PRSI: ${getMonthlyPrsi()}                                                     |
     | Cycle to work: ${employee.ctwS}                                             |
     |                                                                  | 
     |==================================================================|
     | Net pay:  ${getMonthlyNetPay()}                                                |
     |==================================================================|
     """)

}

fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

