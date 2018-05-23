package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a double precision floating point array.
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
 * <td>524290 bytes</td>
 * </tr>
 * </table>
 * The double array binary exists of 2 bytes holding it's length, followed by
 * 8-byte strings of bits holding the double values.
 * <br/>
 * <pre>
 * Prefix       Length           Value 1                                                          ...
 * 00010001     0000000000000001 0100000000000000111111011111001110110110010001011010000111001011
 * DOUBLE_ARRAY = 1              = 2.124
 * </pre>
 * @since 1.0
 */
public class TagDoubleArray implements ITag<double[]> {
    private double[] array;

    public TagDoubleArray( double... doubles ) {
        array = doubles;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( double s : array ) {
            builder.append( new TagDouble( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public double[] getValue() {
        return array;
    }

    @Override
    public void setValue( double[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b10001 );
    }

    /**
     * Parses a CTag code as a double array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              double array data.
     * @return The parsed {@link TagDoubleArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying
     *                        stream throws an IOException.
     * @since 1.0
     */
    public static TagDoubleArray parse( CTagInput input ) throws IOException {
        short len = TagShort.parse( input ).getValue();
        double[] doubles = new double[ len ];
        for( int i = 0; i < len; i++ ) {
            doubles[ i ] = TagDouble.parse( input ).getValue();
        }
        return new TagDoubleArray( doubles );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "DOUBLE_ARRAY [\n" );

        int i = 0;
        for( double b : array ) {
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
