public class Main {
    interface Operation{
        int Operation3355(int a, int b);
    }
    interface MassageProvider{
        void getMassage();
    }
    class Operation1 implements Operation , MassageProvider{
        @Override
        public int Operation3355(int a, int b) {
            return a+b;
        }
        @Override
        public void getMassage() {
            System.out.println("Zdravo");
        }
    }
    public static void main(String[] args) {

        Operation Addition = (a,b) -> {
            return a+b;
        };
        System.out.println("Addition Operation");
        System.out.println(Addition.Operation3355(1, 2));
        Operation Subtraction = new Operation(){
            @Override
            public int Operation3355(int a, int b) {
                return a-b;
            }

        };
        System.out.println(Subtraction.Operation3355(1, 2));
    }

}