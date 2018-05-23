package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

import java.io.IOException;

/**
 * The tag that represents a signed 8bit integer array.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001100 - 0C</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>65538 bytes</td>
 * </tr>
 * </table>
 * The byte array binary starts with two bytes holding the length, followed by a
 * series of bytes holding the values respectively.
 * <br/>
 * <pre>
 * Prefix     Length           Value 1  Value 2  ...
 * 00001100   0000000000000010 00001001 00000001
 * BYTE_ARRAY length = 2       = 9      = 1
 * </pre>
 * @since 1.0
 */
public class TagByteArray implements ITag<byte[]> {
    private byte[] array;

    public TagByteArray( byte... bytes ) {
        array = bytes;
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        builder.append( new TagShort( ( short ) array.length ).encode() );
        builder.append( array );
        return builder.build();
    }

    @Override
    public byte[] getValue() {
        return array;
    }

    @Override
    public void setValue( byte[] value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1100 );
    }

    /**
     * Parses a CTag code as a byte array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              byte array data.
     * @return The parsed {@link TagByteArray} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagByteArray parse( CTagInput input ) throws IOException {
        short len = TagShort.parse( input ).getValue();
        Binary bytes = input.read( len );
        return new TagByteArray( bytes.getBytes() );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder( "BYTE_ARRAY [\n" );

        int i = 0;
        for( byte b : array ) {
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
