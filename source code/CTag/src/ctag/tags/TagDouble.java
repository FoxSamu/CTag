package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a double precision floating point number.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000110 - 06</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>8 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>8 bytes</td>
 * </tr>
 * </table>
 * The double binary exists of 8 bytes holding it's value
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000110 0100000000000000111111011111001110110110010001011010000111001011
 * DOUBLE   = 2.124
 * </pre>
 * @since 1.0
 */
public class TagDouble implements ITag<Double> {
    private double value;
    private long bits;

    public TagDouble() {
        value = 0;
        bits = Double.doubleToRawLongBits( value );
    }

    public TagDouble( double value ) {
        this.value = value;
        bits = Double.doubleToRawLongBits( value );
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

    private byte get5() {
        return ( byte ) ( bits >>> 32 & 0xff );
    }

    private byte get6() {
        return ( byte ) ( bits >>> 40 & 0xff );
    }

    private byte get7() {
        return ( byte ) ( bits >>> 48 & 0xff );
    }

    private byte get8() {
        return ( byte ) ( bits >>> 56 & 0xff );
    }

    @Override
    public Binary encode() {
        return new Binary( new byte[] {
                get8(), get7(),
                get6(), get5(),
                get4(), get3(),
                get2(), get1()
        } );
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue( Double value ) {
        this.value = value;
        bits = Double.doubleToRawLongBits( value );
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b110 );
    }

    /**
     * Parses a CTag code as a double.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              double data.
     * @return The parsed {@link TagDouble} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying
     *                        stream throws an IOException.
     * @since 1.0
     */
    public static TagDouble parse( CTagInput input ) throws IOException {
        Binary bytes = input.read( 8 );
        long byte1 = bytes.getByte( 7 ) & 0xff;
        long byte2 = bytes.getByte( 6 ) & 0xff;
        long byte3 = bytes.getByte( 5 ) & 0xff;
        long byte4 = bytes.getByte( 4 ) & 0xff;
        long byte5 = bytes.getByte( 3 ) & 0xff;
        long byte6 = bytes.getByte( 2 ) & 0xff;
        long byte8 = bytes.getByte( 0 ) & 0xff;
        long byte7 = bytes.getByte( 1 ) & 0xff;
        long value = ( byte8 << 56 ) + ( byte7 << 48 ) + ( byte6 << 40 ) + ( byte5 << 32 ) + ( byte4 << 24 ) + ( byte3 << 16 ) + ( byte2 << 8 ) + byte1;
        return new TagDouble( Double.longBitsToDouble( value ) );
    }

    public String toString() {
        return "DOUBLE " + value;
    }
}
