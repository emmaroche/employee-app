class Employee(var firstName: String, var lastName: String, var gender: Char, var employeeId: Int,
               var grossSalary: Double, var payePercentage: Double, var prsiPercentage: Double,
               var annualBonus: Double, var ctwS: Double) {

    fun getFullEmployeeName() = when (gender){
        'm', 'M' -> "Mr. ${firstName} ${lastName}"
        'f', 'F' -> "Ms. ${firstName} ${lastName}"
        else -> "${firstName} ${lastName}"
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

}
