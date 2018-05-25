package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a single precision floating point number.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000101 - 06</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>4 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>4 bytes</td>
 * </tr>
 * </table>
 * The double binary exists of 4 bytes holding it's value
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000101 01000000000001111110111110011110
 * FLOAT    = 2.124
 * </pre>
 * @since 1.0
 */
public class TagFloat implements ITag<Float> {
    private float value;
    private int bits;

    public TagFloat() {
        value = 0;
        bits = Float.floatToRawIntBits( value );
    }

    public TagFloat( float value ) {
        this.value = value;
        bits = Float.floatToRawIntBits( value );
    }

    private byte get1() {
        return ( byte ) ( bits & 0xff );
    }

    private byte get2() {
        return ( byte ) ( bits >>> 8 & 0xff );
    }

    private byte get3() {
        return ( byte ) ( bits >>> 16 & 0xff );
    }

    private byte get4() {
        return ( byte ) ( bits >>> 24 & 0xff );
    }

    @Override
    public Binary encode() {
        return new Binary( new byte[] {
                get4(), get3(),
                get2(), get1()
        } );
    }

    @Override
    public Float getValue() {
        return value;
    }

    @Override
    public void setValue( Float value ) {
        this.value = value;
        bits = Float.floatToRawIntBits( value );
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b101 );
    }

    /**
     * Parses a CTag code as a float.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              float data.
     * @return The parsed {@link TagFloat} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying
     *                        stream throws an IOException.
     * @since 1.0
     */
    public static TagFloat parse( CTagInput input ) throws IOException, EndException {
        Binary bytes = input.read( 4 );
        int byte1 = bytes.getByte( 3 ) & 0xff;
        int byte2 = bytes.getByte( 2 ) & 0xff;
        int byte3 = bytes.getByte( 1 ) & 0xff;
        int byte4 = bytes.getByte( 0 ) & 0xff;
        int value = ( byte4 << 24 ) + ( byte3 << 16 ) + ( byte2 << 8 ) + byte1;
        return new TagFloat( Float.intBitsToFloat( value ) );
    }

    public String toString() {
        return "FLOAT " + value;
    }
}
