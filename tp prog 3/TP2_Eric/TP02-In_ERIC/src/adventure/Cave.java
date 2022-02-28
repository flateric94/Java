package adventure;

import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * La classe principale du jeu. Techniquement, c'est la seule classe qui aurait besoin d'être
 * publique si on veut pouvoir lancer le jeu depuis une autre application java.
 * 
 * Dans un implémentation du pattern MVC, ce serait le Controller, et pour être parfait on aurait
 * une classe séparée pour gérer la grille de salles.   
 * 
 * @author bd
 *
 */


public class Cave {
    
    Position entryRoomPosition;
    Room[][] rooms; 
    
    /**
     * Classe représentant les commandes. L'intérêt des enum ici est de générer implicitement 
     * et facilement une map entre des lettres (les inputs) et des fonctions qui implémentent l'action. 
     * Les alternatives seraient: 
     *  - une map explicite (mais il faudrait une initialisation statique)
     *  - Un gros switch/case
     *  
     *  Par contre, les inputs doivent être des noms de variables Java syntaxiquement valides
     *  
     * @author bd
     *
     */
    
    enum Command {
        D {
            void execute(Player player, Room room, String arg) {
                room.describe();
            }
        },
        B {
        	void execute(Player player, Room room, String arg) {
        		player.talisman_description();
        	}
        },
        M {
            void execute(Player player, Room room, String arg) {
                Position.Move direction = Position.moveFrom(arg);
                if (direction != null) {
                    if (room.hasExit(direction)) {
                        player.move(direction);
                        return;
                    } else {
                        System.out.println("*** Rams wall ***");
                    }
                } else {
                    notUnderstood();
                }
                return;
            }
        },
        T {
            void execute(Player player, Room room, String arg) {
                if (arg == null) {
                    notUnderstood();
                    return;
                }
                Talisman t = Talisman.from(arg);
                if (room.hasTalisman(t)) {
                	// on r�cup�re le talisman enlev�
                	t = room.takeTalisman(t);
                    player.addTalisman(t);
                    if (player.addTalisman(t) == false) { // si on a pas pu mettre le talisman dans notre inventaire
                    	room.talismans.add(t);
                    }
                } else {
                    System.out.printf("*** No %s in %s\n ***", t, room.getName());
                }
            }
        },
        P {
            void execute(Player player, Room room, String arg) {
                if (arg == null) {
                    notUnderstood();
                    return;
                }
                Talisman t = Talisman.from(arg);
                if (player.hasTalisman(t)) {
                    t = player.takeTalisman(t);
                    room.addTalisman(t);
                } else {
                    System.out.printf("*** Player is not carrying any %s\n ***", t);
                }
            }
        };
       
        /**
         * Methode abstraite pour laquelle chaque instance fournit sa propre
         * implémentation. Les instances jouent donc le role de foncteurs,
         * et la classe se comporte comme une map. 
         *  
         * @param player
         * @param room
         * @param arg
         */
        abstract void execute(Player player, Room room, String arg);
    }
   
    /*
     * Quelques methodes "helper" pour aider à la gestion des salles
     */
    
    /**
     * Retrouve une salle par son nom. Evidemment, on pourrait avoir une Map à la place
     * Mais dans une évolution, on pourrait avoir besoin des coordonnées de la salle.
     * 
     * @param name
     * @return
     */
    private Room roomWithName(String name) {
        for (int x=0;x<rooms.length;x++)
            for (int y=0;y<rooms[0].length;y++) 
                if (rooms[x][y].getName().equals(name))
                    return rooms[x][y];
       return null;
    }

    /**
     * Une salle à partir d'une Position
     * @param p
     * @return
     */
    private Room roomAt(Position p) {
        if (p.x>=0 && p.x<rooms.length && p.y>=0 && p.y<rooms[0].length) {
            return rooms[p.x][p.y];
        }
        return null;
    }
    
    /** 
     * Initialisation du tableau des salles 
     * @param line
     */
    private void allocateRooms(String line) {
        String[] sizes=line.split(",");
        if (sizes.length !=2) {
            throw new GameInitializationException(String.format("Invalid grid specification: \"%s\"",line));
        }
        try {
            int roomsX=Integer.parseUnsignedInt(sizes[0]);
            int roomsY=Integer.parseUnsignedInt(sizes[1]);
            rooms=new Room[roomsX][roomsY];
        }
        catch (NumberFormatException e) {
            // Conversion d'une exception standard en exception contextualisée
            throw new GameInitializationException(String.format("Invalid grid dimension in \"%s\"",line),e);
        }
    }

    /**
     * Chargement des Characters
     * @param charactersStreams
     */
    @SuppressWarnings("resource") // Bug Eclipse...
    private void loadCharacters(InputStream charactersStreams) {
        final Scanner s=new Scanner(charactersStreams,"utf-8").useDelimiter("\n");
        try (s) {
            while (s.hasNext()) {
                String line=s.next().trim();
                if (line.startsWith("#")) continue; // comment
                /*
                 * En limitant split(), on se laisse la possibilité de changer la spec de Character plus tard
                 * en passant tout ce qui se trouve après la première virgule, autres virgules comprises
                 */
                String[] contents=line.split(",",2);
                if (contents.length !=2) { // En pratique 0 ou 1, donc...
                    throw new GameInitializationException(String.format("Invalid Character specification: \"%s\"",line));
                }
                Room room=roomWithName(contents[0]);
                if (room==null) {
                    throw new GameInitializationException(String.format("No room named \"%s\"",contents[0]));
                }
                Character character=Character.from(contents[1]);
                room.addCharacter(character);
            }
        }
    }
    
    /**
     * Chargement des talismans
     * @param talismansStream
     */
    @SuppressWarnings("resource")
    private void loadTalismans(InputStream talismansStream) {
        try (Scanner s=new Scanner(talismansStream,"utf-8").useDelimiter("\n")) {
            while (s.hasNext()) {
                String line=s.next().trim();
                if (line.startsWith("#")) continue; // comment
                /*
                 * En limitant split(), on se laisse la possibilité de changer la spec de Talisman plus tard
                 * en passant tout ce qui se trouve après la première virgule, autres virgules comprises
                 */
                String[] contents=line.split(",",3);
                //if (contents.length !=2) { // Maintenant c'est 3
                if (contents.length !=3) { 
                    throw new GameInitializationException(String.format("Invalid talisman specification: \"%s\"",line));
                }
                Room room=roomWithName(contents[0]);
                if (room==null) {
                    throw new GameInitializationException(String.format("No room named \"%s\"",contents[0]));
                }
                // Ici, on v�rifie la condition sur les 
                if (Integer.parseInt(contents[2]) < 1 || Integer.parseInt(contents[2]) > 100) {
                	throw new GameInitializationException(String.format("Pas la bonne taille ! \"%s\"",contents[2]));
                }
                Talisman talisman=Talisman.from(contents[1], Integer.parseInt(contents[2]));
                room.addTalisman(talisman);
            }
        }
    }
    
    /**
     * Chargement des Room
     * @param roomsStream
     */
    @SuppressWarnings("resource")
    private void loadRooms(InputStream roomsStream) {
        boolean gridSizeKnown=false;
        int roomCount=0;
        try (Scanner s=new Scanner(roomsStream,"utf-8").useDelimiter("\n")) {
            while (s.hasNext()) {
                String line=s.next().trim();
                if (line.startsWith("#")) continue; // comment
                else if (line.startsWith("!") && !gridSizeKnown) {
                    allocateRooms(line.substring(1));
                    gridSizeKnown=true;
                    continue;
                }
                else if (gridSizeKnown) {
                    Room room=Room.from(line);
                    int roomX=roomCount % rooms.length;
                    int roomY=roomCount / rooms.length;
                    rooms[roomX][roomY]=room;
                    if (room.isEntry()) {
                        entryRoomPosition=Position.at(roomX,roomY);
                    }
                    roomCount++;
                }
                else {
                    throw new GameInitializationException(String.format("Room specification before grid size: %s\n",line));
                }
            }
        }
        if (!gridSizeKnown) {
            throw new GameInitializationException("Missing room grid size specification");
        }
        if (roomCount!=rooms.length*rooms[0].length) {
            throw new GameInitializationException("Incorrect number of rooms in data file");
        }
    }
    
    static void notUnderstood() {
        System.out.println("Uh?");
    }
    
    /**
     * Exécution des commandes. Après identification de la commande et de son argument éventuel, 
     * le reste de l'exécution est sous-traité à l'instance de Command
     * @param input
     * @param player
     */
    void execute(String input, Player player) {
        if (input.startsWith("#")) return; // commentaire
        Command command = null;
        String argument = null;
        try (Scanner s = new Scanner(input)) {
            if (s.hasNext()) {
                //TODO: ici on peut ajouter une traduction préalable input->enum 
                command = Command.valueOf(s.next());
            }
            else {
                return; // Rien à exécuter
            }

            s.useDelimiter("\n"); // On ne s'arrête plus sur les espaces
            if (s.hasNext()) {
                argument = s.next().trim();
            }
        }
        catch (NoSuchElementException|IllegalArgumentException e) {
            notUnderstood();
            return;
        }

        // Exécution de la command par l'instance d'enum
        System.out.printf("Executing command \"%s\"\n",input);
        command.execute(player,roomAt(player.position),argument);
    }    
    
    void checkPlayerSurvival(Player player,Room room) {
        for (Iterator<Character> ic=room.getCharacters();ic.hasNext();) { 
            Character c=ic.next();
            if (player.force_totale(c.getShield().getName()) < c.getShield().getForce()) {
                System.out.printf("+++ Arrgh! No talisman against %s\n",c);
                player.dies();
            }
            else {
                System.out.printf("+++ %s wards off %s\n" ,c.getShield(),c);
            }
        }
    }
    
    void play() {
        try (Scanner commands = new Scanner(System.in, "UTF-8")) {
            Player player = new Player(entryRoomPosition);
            Position previousPosition = Position.OUT;
            Room room = roomAt(player.position);
            while (room != null && player.isAlive()) {
                if (player.position != previousPosition) { // Entered new room
                    System.out.printf("-----------------------------\n");
                    room.describe();
                    checkPlayerSurvival(player,room);
                }
                previousPosition = player.position;
//                System.out.printf(">>>");
                String command = commands.nextLine();
                execute(command, player);
                room = roomAt(player.position);
            } 
            if (player.isAlive()) {
                System.out.println("Congratulations, you survived the class!");
            }
            else {
                System.out.println("Too bad, better luck next time...");
            }
        }
    }

    public void showRooms() {
        for (int y = 0; y < rooms[0].length; y++) {
            for (int x = 0; x < rooms.length; x++) {
                System.out.printf("[%d][%d]:%s\n", x, y, rooms[x][y]);
            }
        }
    }
    
    public static void main(String[] args) {
        Cave cave=new Cave();
        /*
         * Les méthodes load...() prennent un Stream. Cela permet d'isoler l'implémentation
         * des fichier de données. Seul main() sait si on utilise des fichiers ressources (comme ici)
         * ou des fichiers de données généraux, ou même un fichier lu directement depuis une URL...
         * Les méthodes load...() voient un InputStream et ça leur suffit.
         */
        // on teste pour voir si on arrive � voir les args
        //cave.loadRooms(Cave.class.getResourceAsStream("Rooms2.dat"));
        cave.loadRooms(Cave.class.getResourceAsStream(args[0]));
        //cave.loadTalismans(Cave.class.getResourceAsStream("Talismans.dat"));
        cave.loadTalismans(Cave.class.getResourceAsStream(args[2]));
        //cave.loadCharacters(Cave.class.getResourceAsStream("Characters.dat"));
        cave.loadCharacters(Cave.class.getResourceAsStream(args[1]));
        cave.showRooms();
        System.out.println("Cave loaded");
        cave.play();
    }
}
