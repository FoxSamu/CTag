package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.EndException;

import java.io.IOException;

/**
 * The tag that represents a boolean.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001011 - 0B</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>1 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>1 bytes</td>
 * </tr>
 * </table>
 * The boolean has one byte where the right-most bit holds the boolean value
 * (1 = true, 0 = false).
 * <br/>
 * <pre>
 * Prefix   Value
 * 00001011 00000000
 * BOOLEAN  = false
 * </pre>
 * @since 1.0
 */
public class TagBoolean implements ITag<Boolean> {
    private boolean value;

    public TagBoolean( boolean value ) {
        this.value = value;
    }

    public TagBoolean() {
        value = false;
    }

    @Override
    public Binary encode() {
        return new Binary( ( byte ) ( value ? 1 : 0 ) );
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue( Boolean value ) {
        this.value = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1011 );
    }

    /**
     * Parses a CTag code as a boolean.
     * @param input The {@link CTagInput} stream that possibly begins with
     *              boolean data.
     * @return The parsed {@link TagBoolean} if parsed with success.
     * @exception IOException If the {@link CTagInput}'s underlying stream
     *                        throws an IOException.
     * @since 1.0
     */
    public static TagBoolean parse( CTagInput input ) throws IOException, EndException {
        byte b = input.read( 1 ).getByte( 0 );
        return new TagBoolean( ( b & 1 ) != 0 );
    }

    public String toString() {
        return "BOOLEAN " + value;
    }
}
