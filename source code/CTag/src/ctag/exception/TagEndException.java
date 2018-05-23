package ctag.exception;

import ctag.tags.TagCompound;
import ctag.tags.TagEnd;

/**
 * Thrown when the {@link TagEnd} is misused. The {@link TagEnd} must not be
 * used manually. The {@link TagCompound} uses it to mark the end of the
 * compound.
 * @since 1.0
 */
public class TagEndException extends RuntimeException {

    public TagEndException( String message ) {
        super( message );
    }

}
