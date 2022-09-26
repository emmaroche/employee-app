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
fun main(args: Array<String>) {
    println(getFullEmployeeName())
    println("Monthly Salary: ${getMonthlySalary()}")
    println("Monthly PRSI: ${getMonthlyPrsi()}")
    println("Monthly PAYE: ${getMonthlyPaye()}")
    println("Monthly Gross Pay: ${getGrossPay()}")
    println("Monthly Total Deductions: ${getTotalDeductions()}")
    println("Monthly Net Pay: ${getMonthlyNetPay()}")
    println(getEmployeeInfo())
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

//Initial two decimal place payslip code
//fun employeeInfo2(){
//
//    val firstName = "Joe"
//    val lastName = "Soap"
//    val gender = "M"
//    val employeeId = 6143
//    val grossSalary = 67543.21
//    val payePercentage = 38.5
//    val prsiPercentage = 5.2
//    val annualBonus =  1450.50
//    val ctwS = 54.33
//
//    val monthlySalary = (grossSalary/12)
//    val monthlyPrsi = monthlySalary * (prsiPercentage / 100)
//    val monthlyPaye = monthlySalary * (payePercentage / 100)
//    val grossPay = (monthlySalary + (annualBonus/12))
//    val totalDeductions = (monthlyPrsi + monthlyPrsi + ctwS)
//
//    println ("\n|==================================================================|")
//    println ("|                    Monthly Payslip                               |")
//    println ("|==================================================================|")
//    println ("|                                                                  |" )
//    println ("|  Employee Name: ${firstName.uppercase()} ${lastName.uppercase()} ($gender)            Employee ID: $employeeId        |")
//    println ("|                                                                  |" )
//    println ("|==================================================================|")
//    println ("|                                                                  |" )
//    println ("| Payment Details  " + "\t\t\t\tDeduction Details                  |" )
//    println ("|                                                                  |" )
//    println ("|==================================================================|")
//    println ("|                                                                  |" )
//    println("| Salary: " + "%.2f".format(monthlySalary) + "  " + "\t\t\t\tPAYE:" + " " + "%.2f".format(monthlyPaye) + "                      |")
//    println("| Bonus: " + (annualBonus / 12) + "  " + "\t\t\t\tPRSI:" + " " +"%.2f".format(monthlyPrsi) + "                       |")
//    println( "| \t\t\t\t\t\t\t\tCycle to work:" + " " + ctwS + "               |")
//    println ("|                                                                  |" )
//    println ("|==================================================================|")
//    println("| Gross: " + "%.2f".format(grossPay) + "  " + "\tTotal Deductions:" + "%.2f".format(totalDeductions) + "                        |" )
//    println ("|==================================================================|")
//    println("| \t\t\t\tNet pay: " + "%.2f".format(grossPay - totalDeductions) + "                                   |")
//    println ("|==================================================================|")
//
//}
