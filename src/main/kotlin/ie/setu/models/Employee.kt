package ie.setu.models

import ie.setu.employee
import ie.setu.logger
import ie.setu.roundToTwoDecimalPlaces

class Employee(var firstName: String, var lastName: String, var gender: Char, var employeeId: Int,
               var grossSalary: Double, var payePercentage: Double, var prsiPercentage: Double,
               var annualBonus: Double, var ctwS: Double) {

    fun getFullEmployeeName() = when (gender){
        'm', 'M' -> "Mr. ${firstName} ${lastName}"
        'f', 'F' -> "Ms. ${firstName} ${lastName}"
        else -> "${firstName} ${lastName}"
    }
    fun getMonthlySalary() = roundToTwoDecimalPlaces(employee.grossSalary / 12)
    fun getMonthlyPaye() = roundToTwoDecimalPlaces(getMonthlySalary() * (employee.payePercentage / 100))
    fun getMonthlyPrsi() = roundToTwoDecimalPlaces(getMonthlySalary() * (employee.prsiPercentage / 100))
    fun getGrossPay() = roundToTwoDecimalPlaces(getMonthlySalary() + (employee.annualBonus / 12))
    fun getTotalDeductions() = roundToTwoDecimalPlaces(getMonthlyPrsi() + getMonthlyPaye() + employee.ctwS)
    fun getMonthlyNetPay() = roundToTwoDecimalPlaces(roundToTwoDecimalPlaces(getGrossPay() - getTotalDeductions()))
    fun getEmployeeInfo() : String {
        val blue = "\u001b[34m"
        val black = "\u001b[30m"
        val backgroundBlue = "\u001b[44m"
        val bold = "\u001b[1m"
        val reset = "\u001b[0m"
        return """
     $blue|==================================================================|$reset
     $blue|$reset                        $blue$bold Monthly Payslip $reset                         $blue|$reset
     $blue|==================================================================|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|$reset  $backgroundBlue$black Employee Name: $reset  ${getFullEmployeeName()}        $backgroundBlue$black Employee ID: $reset ${employeeId}        $blue|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|==================================================================|$reset 
     $blue|$reset                                                                  $blue|$reset  
     $blue|$reset  $backgroundBlue$black Payment Details: $reset                                              $blue|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|  Monthly Salary:$reset ${getGrossPay()}                                         $blue|$reset            
     $blue|  Bonus:$reset ${roundToTwoDecimalPlaces(employee.annualBonus / 12)}                                                   $blue|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|==================================================================|$reset                                                                   
     $blue|$reset                                                                  $blue|$reset  
     $blue|$reset  $backgroundBlue$black Deduction Details: $reset                                            $blue|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|  Total Deductions:$reset ${getTotalDeductions()}                                       $blue|$reset 
     $blue|  PAYE:$reset ${getMonthlyPaye()}                                                   $blue|$reset      
     $blue|  PRSI:$reset ${getMonthlyPrsi()}                                                    $blue|$reset 
     $blue|  Cycle to work:$reset ${employee.ctwS}                                            $blue|$reset 
     $blue|$reset                                                                  $blue|$reset 
     $blue|==================================================================|$reset 
     $blue|$reset                                                                  $blue|$reset  
     $blue|$reset  $backgroundBlue$black Net pay: $reset ${getMonthlyNetPay()}                                              $blue|$reset 
     $blue|$reset                                                                  $blue|$reset  
     $blue|==================================================================|$reset 
     """
    }

    override fun toString(): String {
        val blue = "\u001b[34m"
        val bold = "\u001b[1m"
        val reset = "\u001b[0m"
        return "\n  $blue$bold Employee ID: $reset $employeeId \t $blue$bold Name: $reset  $firstName $lastName ($gender)" +
                "\n   -----------------------------------------" + " \n  $blue Gross Salary: $reset $grossSalary " +
                "|$blue$bold PAYE Percentage: $reset $payePercentage |$blue$bold PRSI Percentage: $reset $prsiPercentage " +
                "|$blue$bold Annual Bonus: $reset $annualBonus |$blue$bold Cycle To Work MonthlyDeduction: $reset $ctwS \n "
    }
}

