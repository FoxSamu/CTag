package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;

import java.io.IOException;

/**
 * The tag that represents a single precision floating point array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00010001 - 11</code></td>
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
 * The float array binary exists of 2 bytes holding it's length, followed by
 * 4-byte strings of bits holding the float values.
 * <br/>
 * <pre>
 * Prefix      Length           Value 1                          ...
 * 00010000    0000000000000001 01000000000001111110111110011110
 * FLOAT_ARRAY = 1              = 2.124
 * </pre>
 * @since 1.0
 */
public class TagFloatArray implements ITag<float[]> {
    private float[] array;

    public TagFloatArray( float... floats ) {
        array = floats;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( float s : array ) {
            builder.append( new TagFloat( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public float[] getValue() {
        return array;
    }

    @Override
    public void setValue( float[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b10000 );
    }

    /**
     * Parses a CTag code as a float array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              float array data.
     * @return The parsed {@link TagFloatArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying
     *                        stream throws an IOException.
     * @since 1.0
     */
    public static TagFloatArray parse( CTagInput input ) throws IOException, EndException, NegativeLengthException {
        short len = TagShort.parse( input ).getValue();
        if( len < 0 ) throw new NegativeLengthException( "Found array with negative length" );
        float[] floats = new float[ len ];
        for( int i = 0; i < len; i++ ) {
            floats[ i ] = TagFloat.parse( input ).getValue();
        }
        return new TagFloatArray( floats );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "FLOAT_ARRAY [\n" );

        int i = 0;
        for( float b : array ) {
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
