import java.util.Scanner;
public class exo4 {
    public static int compteChar(String entree, char c) {
        int cpt = 0;
        for (int i = 0; i < entree.length(); i++) {
            if (entree.charAt(i) == c) {
                cpt++;
            }
        }
        return cpt;
    }

    public static String supprimeChar(String texte, char c) {
        String sansc = "";
        for (int i = 0; i < texte.length(); i++) {
            if (texte.charAt(i) != c) {
                sansc += texte.charAt(i);
            }
        }
        return sansc;
    }

    public static String remplChar(String texte, char c, String rempl) {
        String result = "";
        for (int i = 0; i < texte.length(); i++){
            if (texte.charAt(i) == c) {
                result += texte.charAt(i);
            }
        }
        return result;
    }

    public static boolean avant(String s, String t) {
        s = s.toLowerCase();
        t = t.toLowerCase();
        int ns = s.length();
        int nt = t.length();
        int n = ns>nt ? nt : ns;
        int i = 0;
        while (i<n && s.charAt(i) == t.charAt(i)) {
            i++;
        }
        if (i == n){
            // le cas où un des mots est prefixe de l'autre
            return (ns < nt);
        }
        else{
            // le cas où on est sorti de la boucle car 2 lettres
            return (s.charAt(i) < t.charAt(i));
        }

    }
    
    public static void main (String []args) {
        System.out.println("====question 1)====");
        int res1 = compteChar("programme",'e');
        System.out.println(res1);
        System.out.println("====question 2)====");
        String res2 = supprimeChar("programme", 'm');
        System.out.println(res2);
        System.out.println("====question 3)====");
        String res3 = remplChar("programme", 'mm', "");
        System.out.println(res3);
    }
}

