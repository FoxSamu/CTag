package ctag.exception;

/**
 * Main of all not-{@link RuntimeException}s that are thrown in the CTag encode
 * or decode processes.
 * @since 1.0
 */
public class CTagException extends Exception {
    public CTagException( String message ) {
        super( message );
    }
}
