import kotlin.math.round
fun main(args: Array<String>) {
    println("Pay slip printer")

    employeeInfo()
    println("\n")
//    employeeInfo2()

}

fun employeeInfo(){

    val firstName = "Joe"
    val lastName = "Soap"
    val gender = "M"
    val employeeId = 6143
    val grossSalary = 67543.21
    val payePercentage = 38.5
    val prsiPercentage = 5.2
    val annualBonus =  1450.50
    val ctwS = 54.33

    val monthlySalary = (grossSalary/12)
    val monthlyPrsi = monthlySalary * (prsiPercentage / 100)
    val monthlyPaye = monthlySalary * (payePercentage / 100)
    val grossPay = (monthlySalary + (annualBonus/12))
    val totalDeductions = (monthlyPrsi + monthlyPrsi + ctwS)

    println ("\n|==================================================================|")
    println ("|                    Monthly Payslip                               |")
    println ("|==================================================================|")
    println ("|                                                                  |" )
    println ("|  Employee Name:  ${firstName.uppercase()}  ${lastName.uppercase()} ($gender)           Employee ID: $employeeId       |")
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println ("|                                                                  |" )
    println ("| Payment Details  " + "\t\t\t   Deduction Details                   |" )
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println ("|                                                                  |" )
    println("| Salary: ${roundToTwoDecimalPlaces(monthlySalary)}  \t\t\t   PAYE: ${roundToTwoDecimalPlaces(monthlyPaye)}                       |")
    println("| Bonus:  ${roundToTwoDecimalPlaces(annualBonus / 12)}     \t\t   PRSI: ${roundToTwoDecimalPlaces(monthlyPrsi)}                        |")
    println( "| \t\t\t\t\t\t\t   Cycle to work: $ctwS                |")
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println("| Gross: ${roundToTwoDecimalPlaces(grossPay)} \t\t\t   Total Deductions: ${roundToTwoDecimalPlaces(totalDeductions)}             |" )
    println ("|==================================================================|")
    println("| \t\t\t\tNet pay:  ${roundToTwoDecimalPlaces(grossPay - totalDeductions)}                                  |")
    println ("|==================================================================|")

}

fun roundToTwoDecimalPlaces(number: Double) = round(number * 100) / 100

//Initial two decimal place payslip
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
