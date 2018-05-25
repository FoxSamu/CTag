package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;

import java.io.IOException;

/**
 * The tag that represents a string array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00010011 - 13</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>4295098370 bytes</td>
 * </tr>
 * </table>
 * The string array just holds a prefix, followed by unprefixed string binaries.
 * <br/>
 * <pre>
 * Prefix       Length           Value 1                  ...
 * 00001101     0000000000000001 000000000000000101001011
 * STRING_ARRAY length = 1
 * </pre>
 * @since 1.1
 */
public class TagStringArray implements ITag<String[]> {
    private String[] array;

    public TagStringArray( String... shorts ) {
        array = shorts;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        for( String s : array ) {
            builder.append( new TagString( s ).encode() );
        }
        return builder.build();
    }

    @Override
    public String[] getValue() {
        return array;
    }

    @Override
    public void setValue( String[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b10011 );
    }

    /**
     * Parses a CTag code as a string array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              string array data.
     * @return The parsed {@link TagStringArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.1
     */
    public static TagStringArray parse( CTagInput input ) throws IOException, EndException, NegativeLengthException {
        short len = TagShort.parse( input ).getValue();
        if( len < 0 ) throw new NegativeLengthException( "Found array with negative length" );
        String[] strings = new String[ len ];
        for( int i = 0; i < len; i++ ) {
            strings[ i ] = TagString.parse( input ).getValue();
        }
        return new TagStringArray( strings );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "STRING_ARRAY [\n" );

        int i = 0;
        for( String b : array ) {
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
