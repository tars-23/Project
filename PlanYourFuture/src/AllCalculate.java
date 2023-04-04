public class AllCalculate {

    public static double calculateSimpleInterest(double amount, double rate, int years) {
        double interest = amount * rate * years;
        return amount + interest;
    }

    public static double calculateCompoundInterest(double amount, double rate, int years) {
        return amount * Math.pow(1 + rate, years);
    }

    public static double calculateIncomeTax(double amount) {
        double tax = 0;

        if (amount > 150000 && amount <= 300000) {
            tax = (amount - 150000) * 0.05;
        } else if (amount > 300000 && amount <= 500000) {
            tax = 10000 + (amount - 300000) * 0.1;
        } else if (amount > 500000 && amount <= 750000) {
            tax = 35000 + (amount - 500000) * 0.15;
        } else if (amount > 750000 && amount <= 1000000) {
            tax = 75000 + (amount - 750000) * 0.2;
        } else if (amount > 1000000 && amount <= 2000000) {
            tax = 175000 + (amount - 1000000) * 0.25;
        } else if (amount > 2000000 && amount <= 5000000) {
            tax = 475000 + (amount - 2000000) * 0.3;
        } else if (amount > 5000000 && amount <= 10000000) {
            tax = 1475000 + (amount - 5000000) * 0.35;
        } else if (amount > 10000000) {
            tax = 3475000 + (amount - 10000000) * 0.35;
        }
        return tax;
    }
    //เงินที่ต้องการเพิ่มเข้าไปต่อปี
    public static double calculateFutureValue(double amount, double annualContribution, int years,double interestRate) {
        return amount * Math.pow((1 + interestRate), years) +
                annualContribution * (((Math.pow((1 + interestRate), years) - 1)) / interestRate);
    }

    public static double impactOfInflation(double presentValue, double years, double annualInflationRate) {
        double futureValue = presentValue * Math.pow((1 + annualInflationRate), years);
        return futureValue - presentValue;
    }
}