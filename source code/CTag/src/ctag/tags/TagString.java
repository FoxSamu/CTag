package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a string.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000111 - 07</code></td>
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
 * The string tag starts with a two bytes holding the length, followed by
 * one-byte characters representing the string in UTF-8.
 * <br/>
 * <pre>
 * Prefix   Length           Characters                                            ...
 * 00000111 0000000000000110 01010011 01110100 01110010 01101001 01101110 01100111
 * STRING   = 6              = "String"
 * </pre>
 * @since 1.0
 */
public class TagString implements ITag<String> {
    private String value;

    public TagString() {
        value = "";
    }

    public TagString( String value ) {
        this.value = value;
    }

    @Override
    public Binary encode() {
        byte[] bytes = value.getBytes();
        short length = ( short ) bytes.length;
        byte[] lenBytes = {
                ( byte ) ( length >>> 8 & 0xff ),
                ( byte ) ( length & 0xff )
        };
        Binary.Builder builder = new Binary.Builder();
        builder.append( lenBytes );
        builder.append( bytes );
        return builder.build();
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue( String value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b111 );
    }

    /**
     * Parses a CTag code as a string.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              string data.
     * @return The parsed {@link TagString} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagString parse( CTagInput input ) throws IOException, EndException {
        int len = TagShort.parse( input ).getValue();
        Binary binary = input.read( len );
        byte[] bytes = binary.getBytes();

        String value = new String( bytes );
        return new TagString( value );
    }

    public String toString() {
        return "STRING \"" + value + "\"";
    }
}
