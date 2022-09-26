import kotlin.math.round

val firstName = "Joe"
val lastName = "Soap"
val gender = "M"
val employeeId = 6143
val grossSalary = 67543.21
val payePercentage = 38.5
val prsiPercentage = 5.2
val annualBonus =  1450.50
val ctwS = 54.33
fun main(args: Array<String>){

    var input : Int

    do {
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
fun getFullEmployeeName() = when (gender){
     "M" -> "Mr. $firstName $lastName"
     "F" -> "Ms. $firstName $lastName"
    else -> "$firstName $lastName"
}

fun getMonthlySalary() = roundToTwoDecimalPlaces(grossSalary/12)
fun getMonthlyPaye() = roundToTwoDecimalPlaces(getMonthlySalary() * (payePercentage / 100))
fun getMonthlyPrsi() = roundToTwoDecimalPlaces(getMonthlySalary() * (prsiPercentage / 100))
fun getGrossPay() = roundToTwoDecimalPlaces(getMonthlySalary() + (annualBonus/12))
fun getTotalDeductions() = roundToTwoDecimalPlaces(getMonthlyPrsi() + getMonthlyPaye() + ctwS)
fun getMonthlyNetPay() = roundToTwoDecimalPlaces(roundToTwoDecimalPlaces(getGrossPay() - getTotalDeductions()))
fun getEmployeeInfo(){

    println ( """
     |==================================================================|
     |                        Monthly Payslip                           |
     |==================================================================|
     |                                                                  |
     |  Employee Name:  ${getFullEmployeeName()}            Employee ID: $employeeId       |
     |                                                                  |
     |==================================================================|
     |                                                                  | 
     | Payment Details:                                                 |
     |                                                                  |
     | Gross: ${getGrossPay()}                                                   |
     | Salary: ${getMonthlySalary()}                                                   |           
     | Bonus:  ${roundToTwoDecimalPlaces(annualBonus / 12)}                                                   |
     |                                                                  |
     |==================================================================|                                                                 | 
     |                                                                  | 
     | Deduction Details:                                               |
     |                                                                  |
     | Total Deductions: ${getTotalDeductions()}                                        |
     | PAYE: ${getMonthlyPaye()}                                                    |     
     | PRSI: ${getMonthlyPrsi()}                                                     |
     | Cycle to work: $ctwS                                             |
     |                                                                  | 
     |==================================================================|
     | Net pay:  ${getMonthlyNetPay()}                                                |
     |==================================================================|
     """)

}

fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

