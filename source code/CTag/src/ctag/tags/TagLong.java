package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a signed 64-bits integer.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000100 - 04</code></td>
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
 * The long tag has eight bytes holding the value respectively.
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000100 1111111111111111111111111111111111111111111111111111111111111101
 * INTEGER  = -2
 * </pre>
 * @since 1.0
 */
public class TagLong implements ITag<Long> {
    private long value;

    public TagLong( long value ) {
        this.value = value;
    }

    public TagLong() {
        value = 0;
    }

    private byte get1() {
        return ( byte ) ( value & 0xff );
    }

    private byte get2() {
        return ( byte ) ( value >>> 8 & 0xff );
    }

    private byte get3() {
        return ( byte ) ( value >>> 16 & 0xff );
    }

    private byte get4() {
        return ( byte ) ( value >>> 24 & 0xff );
    }

    private byte get5() {
        return ( byte ) ( value >>> 32 & 0xff );
    }

    private byte get6() {
        return ( byte ) ( value >>> 40 & 0xff );
    }

    private byte get7() {
        return ( byte ) ( value >>> 48 & 0xff );
    }

    private byte get8() {
        return ( byte ) ( value >>> 56 & 0xff );
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
    public Long getValue() {
        return value;
    }

    @Override
    public void setValue( Long value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b100 );
    }

    /**
     * Parses a CTag code as a long.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              long data.
     * @return The parsed {@link TagLong} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagLong parse( CTagInput input ) throws IOException, EndException {
        Binary bytes = input.read( 8 );
        long byte1 = bytes.getByte( 7 ) & 0xff;
        long byte2 = bytes.getByte( 6 ) & 0xff;
        long byte3 = bytes.getByte( 5 ) & 0xff;
        long byte4 = bytes.getByte( 4 ) & 0xff;
        long byte5 = bytes.getByte( 3 ) & 0xff;
        long byte6 = bytes.getByte( 2 ) & 0xff;
        long byte7 = bytes.getByte( 1 ) & 0xff;
        long byte8 = bytes.getByte( 0 ) & 0xff;
        long value = ( byte8 << 56 ) + ( byte7 << 48 ) + ( byte6 << 40 ) + ( byte5 << 32 ) + ( byte4 << 24 ) + ( byte3 << 16 ) + ( byte2 << 8 ) + byte1;
        return new TagLong( value );
    }

    public String toString() {
        return "LONG " + value;
    }
}
