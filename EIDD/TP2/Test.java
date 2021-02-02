public class Test {
    
    public static void main(String[] args){

        /* Test classe Animal
         * 2 manieres possible :
         *      - sans constructeur
         *      - constructeur         
         */ 
        // Animal a1 = new Animal();
        // a1.nom = "Marti";
        // a1.sexe = 'm';
        // a1.poids = 300;
        // a1.espece = "zebre";
        // a1.age = 5;
        Animal a1 = new Animal("Marti", 'm', 300, "zebre", 5);              //avec constructeur
        Animal a2 = new Animal("Gloria", 'f', 1500, "hippopotame", 7);
        a2.poids -= 50;
        System.out.println("poids gloria :" +a2.poids);
        System.out.println(Animal.description(a1));
        Animal a3 = new Animal("Melman", 'm', 1000, "girafe", 4);
        Animal[] t = {a1, a2, a3};
        System.out.println(Animal.plusGros(t));
        // test reproduction :
        Animal a4 = new Animal("Nicolas", 'm', 234, "hippopotame", 8);
        Animal a5 = a2.reproduction(a4);
        System.out.println("je suis un(e) " +a5.sexe+ " et je pese " +a5.poids+ " kg");



    }

}