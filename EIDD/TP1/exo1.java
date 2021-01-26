public class exo1 {
    public static void main(String[] args) {
        int taille = 10;
        System.out.println("Premier Drapeau");
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++)
                System.out.print("*");
            System.out.println();
        }
        System.out.println("Second Drapeau");
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < i; j++)
                System.out.print("*");
            System.out.println();
        }
    }
}
