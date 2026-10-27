import java.util.Scanner;

public class Main {
    interface Operation {
        double operation(double result, double number);
    }
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

        public Operation addition= (a,b) -> {
            result=a+b;
            return a+b;
        };
        public Operation substitution= (a,b) ->{
            result=a-b;
            return a-b;
        };
        public Operation multiplication= (a,b) -> {
            result=a*b;
            return a*b;
        };
        public Operation division= (a,b) ->{
            result=a/b;
            return a/b;
        };
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
                if(parts[0].equals("+")){
                    calc.addition.operation(calc.getResult(), Double.parseDouble(parts[1]));
                    System.out.println("Result is: " + calc.getResult());}
                else if(parts[0].equals("-")) {
                    calc.substitution.operation(calc.getResult(), Double.parseDouble(parts[1]));
                    System.out.println("Result is: " + calc.getResult());
                }
                else if(parts[0].equals("*")) {
                    calc.multiplication.operation(calc.getResult(), Double.parseDouble(parts[1]));
                    System.out.println("Result is: " + calc.getResult());
                }
                else if(parts[0].equals("/")) {
                    calc.division.operation(calc.getResult(), Double.parseDouble(parts[1]));
                    System.out.println("Result is: " + calc.getResult());
                }
                else throw new UnknownOperatorException(parts[0]+" is an unknown operation.");
            }catch(UnknownOperatorException e){
                System.out.println(e.getMessage());
                System.out.println("Reenter your last line");
            }
        }
    }
}