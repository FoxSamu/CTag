package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a signed 32-bits integer.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000011 - 03</code></td>
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
 * The integer tag has four bytes holding the value respectively.
 * <br/>
 * <pre>
 * Prefix   Value
 * 00000011 11111111111111111111111111111101
 * INTEGER  = -2
 * </pre>
 * @since 1.0
 */
public class TagInteger implements ITag<Integer> {
    private int value;

    public TagInteger( int value ) {
        this.value = value;
    }

    public TagInteger() {
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

    @Override
    public Binary encode() {
        return new Binary( new byte[] {
                get4(), get3(),
                get2(), get1()
        } );
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue( Integer value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b11 );
    }

    /**
     * Parses a CTag code as an integer.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              integer data.
     * @return The parsed {@link TagInteger} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagInteger parse( CTagInput input ) throws IOException, EndException {
        Binary bytes = input.read( 4 );
        int byte1 = bytes.getByte( 3 ) & 0xff;
        int byte2 = bytes.getByte( 2 ) & 0xff;
        int byte3 = bytes.getByte( 1 ) & 0xff;
        int byte4 = bytes.getByte( 0 ) & 0xff;
        int value = ( byte4 << 24 ) + ( byte3 << 16 ) + ( byte2 << 8 ) + byte1;
        return new TagInteger( value );
    }

    public String toString() {
        return "INTEGER " + value;
    }
}
