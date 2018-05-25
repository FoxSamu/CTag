package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a signed 16-bits integer.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000010 - 02</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * </table>
 * The short tag has two bytes holding the value respectively.
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000011 1111111111111101
 * INTEGER  = -2
 * </pre>
 * @since 1.0
 */
public class TagShort implements ITag<Short> {
    private short value;

    public TagShort( short value ) {
        this.value = value;
    }

    public TagShort() {
        value = 0;
    }

    private byte get1() {
        return ( byte ) ( value & 0xff );
    }

    private byte get2() {
        return ( byte ) ( value >>> 8 & 0xff );
    }

    @Override
    public Binary encode() {
        return new Binary( new byte[] {
                get2(), get1()
        } );
    }

    @Override
    public Short getValue() {
        return value;
    }

    @Override
    public void setValue( Short value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b10 );
    }

    /**
     * Parses a CTag code as a short.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              short data.
     * @return The parsed {@link TagShort} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagShort parse( CTagInput input ) throws IOException, EndException {
        Binary bytes = input.read( 2 );
        int byte1 = bytes.getByte( 1 ) & 0xff;
        int byte2 = bytes.getByte( 0 ) & 0xff;
        short value = ( short ) ( ( byte2 << 8 ) + byte1 );
        return new TagShort( value );
    }

    public String toString() {
        return "SHORT " + value;
    }
}
