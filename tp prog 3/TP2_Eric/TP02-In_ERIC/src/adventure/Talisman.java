package adventure;

/**
 * La classe de talisman. On aurait pu décider que pour l'instant, une String aurait suffit
 * mais dès qu'on va changer quelque chose (ajouter une force, par exemple) le Talisman
 * va devenir un objet... 
 */

class Talisman {
    private String name;
    private int force;   //correspond à la force du Talisman et la force necessaire pour vaincre l'enemi.
    
    /**
     * Le constructeur "private" pour éviter les abus
     * @param name
     */
    private Talisman(String name, int force) {
        this.name=name;
        // on rajoute l'attribut force 
        this.force = force;
    }
    
    String getName() {
        return name;
    }
    
    int getForce() {
    	return force;
    }
    
    public boolean equals(Object o) {
        if (o instanceof Talisman && ((Talisman) o).name.equals(name)) {
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "{-"+name+"-"+force+"}";
    }
    
    /**
     * Crée un Talisman à partir d'une String. Si dans l'implémentation actuelle
     * la String est prise telle quelle, dans une implémentation future on pourrait
     * effectuer un découpage comme dans les autres classes.
     * @param s
     * @return
     */
    public static Talisman from(String s, int f) {
        return new Talisman(s,f);
    }
    
    public static Talisman from(String s) {
        return new Talisman(s,0);
    }    
}
