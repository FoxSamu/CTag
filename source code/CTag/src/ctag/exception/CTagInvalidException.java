package ctag.exception;

import ctag.CTagDecoder;

/**
 * Thrown when the {@link CTagDecoder} fails parsing the binary.
 * @since 1.0
 */
public class CTagInvalidException extends CTagException {
    public CTagInvalidException( String message ) {
        super( message );
    }
}
