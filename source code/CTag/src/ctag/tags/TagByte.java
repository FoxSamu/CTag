package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a signed 8-bits integer.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000001 - 01</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>1 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>1 bytes</td>
 * </tr>
 * </table>
 * The byte tag has one byte holding it's value respectively.
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000001 11111101
 * BYTE     = -2
 * </pre>
 * @since 1.0
 */
public class TagByte implements ITag<Byte> {
    private byte value;

    public TagByte( byte value ) {
        this.value = value;
    }

    public TagByte() {
        value = 0;
    }

    @Override
    public Binary encode() {
        return new Binary( value );
    }

    @Override
    public Byte getValue() {
        return value;
    }

    @Override
    public void setValue( Byte value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1 );
    }

    /**
     * Parses a CTag code as a byte.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              byte data.
     * @return The parsed {@link TagByte} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagByte parse( CTagInput input ) throws IOException {
        Binary bytes = input.read( 1 );
        return new TagByte( bytes.getByte( 0 ) );
    }

    public String toString() {
        return "BYTE " + value;
    }
}
