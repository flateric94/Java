
/**
 * L'interface des objets qui pourront etre utilisées sur une Ardoise
 * @author pierrecharbit
 */
public interface Dessinable {
    /**
     * La méthode appelée par une Ardoise pour afficher un Dessinable 
     * @param a l'ardoise qui va afficher l'objet Dessinable
     */
    void dessineSur(Ardoise a);
}
