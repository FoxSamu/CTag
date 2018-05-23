package ctag.exception;

import ctag.Binary;

/**
 * Thrown when the {@link Binary} class fails parsing a base-64 string.
 * @since 1.0
 */
public class Base64Exception extends RuntimeException {
    public Base64Exception( String message ) {
        super( message );
    }
}
