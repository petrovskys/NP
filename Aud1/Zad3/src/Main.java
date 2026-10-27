import java.util.Scanner;

public class Main {
    static class UnknownOperatorException extends Exception {
        public UnknownOperatorException(String message) {
            super(message);
        }
    }
    static class Calculator{
        private double result = 0.0;
        public double getResult() {
            return result;
        }
        public void resetResult() {
            result = 0.0;
        }
        public void addition(double n){
            result += n;
            System.out.println("Result + "+ n + " = " + result);
        }
        public void subtraction(double n){
            result -= n;
            System.out.println("Result - "+ n + " = " + result);
        }
        public void multiplication(double n){
            result *= n;
            System.out.println("Result * "+ n + " = " + result);
        }
        public void division(double n){
            result /= n;
            System.out.println("Result / "+ n + " = " + result);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        System.out.println("Calculator is on.");
        while(true) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            if (parts.length==1 && parts[0].equalsIgnoreCase("R")) {
                System.out.println("Final result: " + calc.getResult());
                System.out.println("Would you like another calculation?");
                String yn = sc.nextLine();
                if (yn.equalsIgnoreCase("yes")){
                    calc.resetResult();
                }else{
                    System.out.println("End of program.");
                    break;
                }
            }
            try{
                if(parts[0].equals("+"))
                    calc.addition(Double.parseDouble(parts[1]));
                else if(parts[0].equals("-"))
                    calc.subtraction(Double.parseDouble(parts[1]));
                else if(parts[0].equals("*"))
                    calc.multiplication(Double.parseDouble(parts[1]));
                else if(parts[0].equals("/"))
                    calc.division(Double.parseDouble(parts[1]));
                else throw new UnknownOperatorException(parts[0]+" is an unknown operation.");
            }catch(UnknownOperatorException e){
                System.out.println(e.getMessage());
                System.out.println("Reenter your last line");
            }
        }
    }
}