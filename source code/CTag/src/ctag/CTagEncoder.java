package ctag;

import ctag.tags.ITag;

/**
 * Encodes a tag into a CTag code
 * @since 1.0
 */
public class CTagEncoder {
    private ITag tag;

    /**
     * @param tag The tag to encode
     * @since 1.0
     */
    public CTagEncoder( ITag tag ) {
        this.tag = tag;
    }

    /**
     * Returns the tag to encode
     * @return The tag to encode
     * @since 1.0
     */
    public ITag getTag() {
        return tag;
    }

    /**
     * Encodes the tag
     * @return The encoded tag as a {@link Binary}
     * @since 1.0
     */
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( tag.getPrefixByte() );
        builder.append( tag.encode() );
        return builder.build();
    }
}
