package net.humanity_game.server.exceptions;

public class IllegalGameStateException extends Exception{

    public IllegalGameStateException() {
        super();
    }

    public IllegalGameStateException(String message) {
        super(message);
    }

    public IllegalGameStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalGameStateException(Throwable cause) {
        super(cause);
    }

    public IllegalGameStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
