package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;

/**
 * The tag that represents {@code null}.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001010 - 0A</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>0 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>0 bytes</td>
 * </tr>
 * </table>
 * The null value has no payload.
 * <br/>
 * <pre>
 * Prefix
 * 00001010
 * NULL
 * </pre>
 * @since 1.0
 */
public class TagNull implements ITag<Object> {

    @Override
    public Binary encode() {
        return new Binary();
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public void setValue( Object value ) {

    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1010 );
    }

    /**
     * Parses a CTag code as {@coe null}.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              null data.
     * @return The parsed {@link TagNull} if parsed with success.
     * @since 1.0
     */
    public static TagNull parse( CTagInput input ) {
        return new TagNull();
    }

    public String toString() {
        return "NULL";
    }
}
