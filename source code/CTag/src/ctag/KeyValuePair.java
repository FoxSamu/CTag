package ctag;

import ctag.tags.ITag;
import ctag.tags.TagCompound;
import ctag.tags.TagString;

/**
 * Used by the {@link TagCompound} to represent a key-value pair.
 */
public class KeyValuePair {
    public final String key;
    public final ITag value;

    public KeyValuePair( String key, ITag value ) {
        this.key = key;
        this.value = value;
    }

    /**
     * Encodes this pair of a compound
     * @return The encoded data as a {@link Binary}
     */
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( value.getPrefixByte() );
        builder.append( new TagString( key ).encode() );
        builder.append( value.encode() );
        return builder.build();
    }
}
