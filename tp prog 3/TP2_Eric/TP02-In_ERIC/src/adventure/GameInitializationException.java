package adventure;

/**
 * Notre propre classe d'exception pour remonter les erreurs lors de l'initialisation.
 * @author bd
 *
 */

@SuppressWarnings("serial")
class GameInitializationException extends RuntimeException {
    GameInitializationException(String message) {
        super(message);
    }
    GameInitializationException(String message,Throwable cause) {
        super(message,cause);
    }
}
