package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;

import java.io.IOException;

/**
 * The tag that represents a signed 64bit integer array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001111 - 0F</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>524290 bytes</td>
 * </tr>
 * </table>
 * The long array binary starts with two bytes holding the length, followed by a
 * series of 8-byte strings holding the values respectively.
 * <br/>
 * <pre>
 * Prefix     Length           Value 1                                                          ...
 * 00001111   0000000000000001 0000000000000000000000000000000000000000000000000000000000001001
 * LONG_ARRAY length = 1       = 9
 * </pre>
 * @since 1.0
 */
public class TagLongArray implements ITag<long[]> {
    private long[] array;

    public TagLongArray( long... longs ) {
        array = longs;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( long s : array ) {
            builder.append( new TagLong( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public long[] getValue() {
        return array;
    }

    @Override
    public void setValue( long[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1111 );
    }

    /**
     * Parses a CTag code as a long array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              long array data.
     * @return The parsed {@link TagLongArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagLongArray parse( CTagInput input ) throws IOException, EndException, NegativeLengthException {
        short len = TagShort.parse( input ).getValue();
        if( len < 0 ) throw new NegativeLengthException( "Found array with negative length" );
        long[] longs = new long[ len ];
        for( int i = 0; i < len; i++ ) {
            longs[ i ] = TagLong.parse( input ).getValue();
        }
        return new TagLongArray( longs );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "LONG_ARRAY [\n" );

        int i = 0;
        for( long b : array ) {
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
