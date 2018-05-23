package ctag.exception;

import ctag.Array;
import ctag.Compound;

/**
 * Thrown when the {@link Array} or the {@link Compound} are asked for a
 * specific type but found another one.
 * @since 1.0
 */
public class WrongTagException extends CTagException {

    public WrongTagException( String message ) {
        super( message );
    }

}
