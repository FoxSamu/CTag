package ctag.exception;

import ctag.Compound;

/**
 * Thrown when a {@link Compound} is asked for a key that does not exist.
 * @since 1.0
 */
public class NoSuchKeyException extends CTagException {

    public NoSuchKeyException( String message ) {
        super( message );
    }

}
