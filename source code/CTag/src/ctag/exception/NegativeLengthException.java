package ctag.exception;

/**
 * Thrown when an array or string is found where the length header is negative.
 * @since 1.1
 */
public class NegativeLengthException extends CTagException {

    public NegativeLengthException( String message ) {
        super( message );
    }

}
