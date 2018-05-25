package ctag.exception;

import ctag.CTagInput;

/**
 * Thrown when the {@link CTagInput} is asked for for bytes while the end is
 * already reached.
 *
 * @since 1.1
 */
public class EndException extends CTagException {

    public EndException( String message ) {
        super( message );
    }
    
}
