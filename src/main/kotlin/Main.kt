

fun main(args: Array<String>) {
    println("Pay slip printer")

    employeeInfo()

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
    println ("|  Employee Name: " + firstName.uppercase() + " " + lastName.uppercase() + " " + "(" + gender + ")" + "        " + "\tEmployee ID:" + " " + employeeId + "          |")
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println ("|                                                                  |" )
    println ("| Payment Details  " + "\t\t\t\tDeduction Details                  |" )
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println ("|                                                                  |" )
    println("| Salary: " + monthlySalary + "  " + "\tPAYE:" + " " + monthlyPaye + "           |")
    println("| Bonus: " + (annualBonus / 12) + "  " + "\t\t\t\tPRSI:" + " " + monthlyPrsi + "            |")
    println( "| \t\t\t\t\t\t\t\tCycle to work:" + " " + ctwS + "               |")
    println ("|                                                                  |" )
    println ("|==================================================================|")
    println("| Gross: " + grossPay + "  " + "\tTotal Deductions:" + totalDeductions + " |" )
    println ("|==================================================================|")
    println("| \t\t\t\tNet pay: " + (grossPay - totalDeductions) + "                         |")
    println ("|==================================================================|")

}
