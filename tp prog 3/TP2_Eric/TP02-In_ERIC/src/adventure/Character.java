package adventure;

class Character {
    private String name;
    private Talisman shield;
    
    /**
     * Le constructeur "private" pour éviter les abus
     * @param name
     */
    private Character(String name,Talisman shield) {
        this.name=name;
        this.shield=shield;
    }
    
    String getName() {
        return name;
    }
    
    Talisman getShield() {
        return shield;
    }
    

    public String toString() {
        return "<["+name+"]>";
    }
    
    /**
     * Crée un Character à partir d'une String. 
     * @param s
     * @return
     */
    
    public static Character from(String line) {
        //String[] contents=line.split(",",2); on a rajout� la force du Talisman (dans contents[2])
    	String[] contents=line.split(",",3);
        if (contents.length !=3) {
            throw new GameInitializationException(String.format("Invalid Character specification: \"%s\"",line));
        }
        return new Character(contents[0],Talisman.from(contents[1],Integer.parseInt(contents[2])));
    }    
}
