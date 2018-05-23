package ctag.tags;

import ctag.Binary;

/**
 * The tag that represents the end of a compound. Do not use it!! It is marked
 * for removal in version 1.1.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00000000 - 00</code></td>
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
 * The end tag has no payload, only a prefix.
 * <br/>
 * <pre>
 * Prefix
 * 00000000
 * END
 * </pre>
 * @since 1.0
 * @deprecated Do not use this. Marked for removal in version 1.1.
 */
@Deprecated
public class TagEnd implements ITag<Object> {

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
        return new Binary( ( byte ) 0 );
    }

}
