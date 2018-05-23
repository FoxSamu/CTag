package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a signed 32bit integer array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001110 - 0E</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>262146 bytes</td>
 * </tr>
 * </table>
 * The integer array binary starts with two bytes holding the length, followed
 * by a series of 4-byte strings holding the values respectively.
 * <br/>
 * <pre>
 * Prefix        Length           Value 1                          ...
 * 00001110      0000000000000001 00000000000000000000000000001001
 * INTEGER_ARRAY length = 1       = 9
 * </pre>
 * @since 1.0
 */
public class TagIntegerArray implements ITag<int[]> {
    private int[] array;

    public TagIntegerArray( int... ints ) {
        array = ints;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( int s : array ) {
            builder.append( new TagInteger( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public int[] getValue() {
        return array;
    }

    @Override
    public void setValue( int[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1110 );
    }

    /**
     * Parses a CTag code as an integer array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              integer array data.
     * @return The parsed {@link TagIntegerArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagIntegerArray parse( CTagInput input ) throws IOException {
        short len = TagShort.parse( input ).getValue();
        int[] ints = new int[ len ];
        for( int i = 0; i < len; i++ ) {
            ints[ i ] = TagInteger.parse( input ).getValue();
        }
        return new TagIntegerArray( ints );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "INTEGER_ARRAY [\n" );

        int i = 0;
        for( int b : array ) {
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
