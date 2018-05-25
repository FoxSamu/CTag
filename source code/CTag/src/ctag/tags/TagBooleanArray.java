package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;

import java.io.IOException;

/**
 * The tag that represents a boolean array, and order is preserved.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00010010 - 12</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>8194 bytes</td>
 * </tr>
 * </table>
 * The array binary starts with two bytes holding the length, followed by a
 * series of bits holding the boolean values (1 = true, 0 = false). A bytefill
 * of zeroes is added if the last byte is not filled.
 * <br/>
 * <pre>
 * Prefix        Length           Values                  Bytefill
 * 00010010      0000000000001011 110100101101            0000
 * BOOLEAN_ARRAY length = 12      true, true, false, etc.
 * </pre>
 * @since 1.0
 */
public class TagBooleanArray implements ITag<boolean[]> {
    private boolean[] array;

    public TagBooleanArray( boolean... bools ) {
        array = bools;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        int i = 7;
        byte bits = 0;
        for( boolean b : array ) {
            if( i == -1 ) {
                builder.append( new byte[] { bits } );
                bits = 0;
                i = 7;
            }
            if( b ) {
                bits |= 1 << i;
            }
            i--;
        }
        builder.append( new byte[] { bits } );
        return builder.build();
    }

    @Override
    public boolean[] getValue() {
        return array;
    }

    @Override
    public void setValue( boolean[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b10010 );
    }

    /**
     * Parses a CTag code as a boolean array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              boolean array data.
     * @return The parsed {@link TagBooleanArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagBooleanArray parse( CTagInput input ) throws IOException, EndException, NegativeLengthException {
        short len = TagShort.parse( input ).getValue();
        if( len < 0 ) throw new NegativeLengthException( "Found array with negative length" );
        boolean[] bools = new boolean[ len ];
        byte current = 0;
        for( int i = 0; i < len; i++ ) {
            int bit = 7 - ( i & 7 );
            if( bit == 7 ) {
                current = input.read( 1 ).getByte( 0 );
            }
            bools[ i ] = ( current & 1 << bit ) != 0;
        }
        return new TagBooleanArray( bools );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "INTEGER_ARRAY [\n" );

        int i = 0;
        for( boolean b : array ) {
            builder.append( "    " );
            builder.append( i );
            builder.append( ": " );
            builder.append( b );
            builder.append( "\n" );
            i++;
        }

        builder.append( "]" );
        return builder.toString();
    }
}
