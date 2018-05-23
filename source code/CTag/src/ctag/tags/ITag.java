package ctag.tags;

import ctag.Binary;
import ctag.exception.CTagInvalidException;

/**
 * Base of all tag types. Do not make and use custom tags. The decoder will
 * throw a {@link CTagInvalidException} if it finds an unknown type.
 * @param <TType> The type this tag encodes.
 * @since 1.0
 */
public interface ITag <TType> {
    /**
     * Encodes the data.
     * @return A {@link Binary} instance containing the encoded bytes.
     * @since 1.0
     */
    Binary encode();

    /**
     * Returns the value to encode.
     * @return The value to encode.
     * @since 1.0
     */
    TType getValue();

    /**
     * Sets the value to encode. Some tags may not support this operation and
     * do nothing.
     * @param value The value to encode.
     * @since 1.0
     */
    void setValue( TType value );

    /**
     * Returns the byte where this type should be prefixed with. This is used to
     * tell the decoder which type to read here.
     * @return A binary containing exactly one byte holding the prefix.
     * @since 1.0
     */
    Binary getPrefixByte();
}
