import java.util.Random;

public class Animal {
    String nom;
    char sexe;
    int poids;
    String espece;
    int age;
    int faim;

    public Animal (String n, char s, int p, String e, int a){
        this.nom = n;
        if (s != 'm' && s != 'f'){
            System.out.println("Erreur de sexe");
        }
        else {
            this.sexe = s;
        }
        this.poids = p;
        this.espece = e;
        this.age = a;
    }
    
    public Animal(){}

    public static String description(Animal a){
        String genre = "";
        if (a.sexe == 'm'){
            genre += "male";
        }
        else if (a.sexe == 'f'){
            genre += "femelle";
        }
        String desc = "Je m'appele " +a.nom+ ", je suis un " +a.espece+ " " +genre+ ", j'ai " +a.age+ " ans et je pese " +a.poids+ " kg.";
        return desc;
    }

    public static void nourrir (Animal a){
        a.faim -= 5;
        if (a.faim < 0){
            a.faim = 0;
        }
    }

    public static int plusGros(Animal[] tab){
        int max = tab[0].poids;
        for(int i=0; i<tab.length; i++) {
            if (tab[i].poids > max){
                max = tab[i].poids;
            }
        }
        return max;
    }

    public Animal reproduction(Animal a){
        // on cree un nouvel animal :
        Animal bebe = new Animal();
        int ind1, ind2, min = a.poids, max = this.poids;
        Random random = new Random();
        char[] choix = {'m','f'};
        if (a.sexe != this.sexe && a.faim<5 && this.faim<5 && a.espece.equals(this.espece)){
            bebe.espece = a.espece;
            ind1 = random.nextInt(2);
            bebe.sexe = choix[ind1];
            if (a.poids > this.poids){
                min = this.poids;
                max = a.poids;
            }
            ind2 = min + random.nextInt(max-min);
            bebe.poids = ind2;
        }
        return bebe;
    }




}