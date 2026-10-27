import java.util.Scanner;

public class Main {
    static class Snake_Alien extends Alien {
        public Snake_Alien(int health, String name){
            super(health, name);
        }

    }
    static class Ogre_Alien extends Alien {
        public Ogre_Alien(int health, String name){
            super(health, name);
        }

    }
    static class Marshmallow_Alien extends Alien {
        public Marshmallow_Alien(int health, String name){
            super(health, name);
        }

    }
    static class Alien {
        private int health;
        private String name;

        public Alien(int health, String name) {
            this.health = health;
            this.name = name;
        }
        public double getDamage(){
            if (this instanceof Marshmallow_Alien){
                return 1;
            }else if (this instanceof Ogre_Alien){
                return 6;
            }else return 10;
        };
    }

    static class AlienPack {
        private Alien[] aliens;

        public AlienPack(int numAliens) {
            aliens = new Alien[numAliens];
        }

        public void addAlien(Alien newAlien, int index) {
            aliens[index] = newAlien;
        }

        public Alien[] getAliens() {
            return aliens;
        }

        public double calculateDamage() {
            double damage = 0;
            for (int i = 0; i < aliens.length; i++) {
                damage += aliens[i].getDamage();
            }
            return damage;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        AlienPack pack = new AlienPack(n);
        int i=0;
        while (true) {
            String type = sc.nextLine();
            if (type.equals("#")) break;
            int health = sc.nextInt();
            String name = sc.nextLine();
            if (type.equals("O")){
                pack.addAlien(new Ogre_Alien(health,name),i++);
            }
            if (type.equals("S")){
                pack.addAlien(new Snake_Alien(health,name),i++);
            }
            if (type.equals("M")){
                pack.addAlien(new Marshmallow_Alien(health,name),i++);
            }
        }
    }
}