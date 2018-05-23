package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a signed 16bit integer array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001101 - 0D</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>131074 bytes</td>
 * </tr>
 * </table>
 * The short array binary starts with two bytes holding the length, followed
 * by a series of 2-byte strings holding the values respectively.
 * <br/>
 * <pre>
 * Prefix      Length           Value 1          ...
 * 00001101    0000000000000001 0000000000001001
 * SHORT_ARRAY length = 1       = 9
 * </pre>
 * @since 1.0
 */
public class TagShortArray implements ITag<short[]> {
    private short[] array;

    public TagShortArray( short... shorts ) {
        array = shorts;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( short s : array ) {
            builder.append( new TagShort( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public short[] getValue() {
        return array;
    }

    @Override
    public void setValue( short[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1101 );
    }

    /**
     * Parses a CTag code as a short array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              short array data.
     * @return The parsed {@link TagShortArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagShortArray parse( CTagInput input ) throws IOException {
        short len = TagShort.parse( input ).getValue();
        short[] shorts = new short[ len ];
        for( int i = 0; i < len; i++ ) {
            shorts[ i ] = TagShort.parse( input ).getValue();
        }
        return new TagShortArray( shorts );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "SHORT_ARRAY [\n" );

        int i = 0;
        for( short b : array ) {
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
