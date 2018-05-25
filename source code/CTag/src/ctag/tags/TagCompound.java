package ctag.tags;

import ctag.Binary;
import ctag.CTagInput;
import ctag.Compound;
import ctag.KeyValuePair;
import ctag.exception.CTagInvalidException;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;

import java.io.IOException;
import java.util.Map;

/**
 * The tag that represents a string-value map, order not preserved.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001001 - 09</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>1 byte</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>Infinite</td>
 * </tr>
 * </table>
 * The compound binary starts directly with elements, existing of a prefix, the
 * name (2 bytes = length, 1-byte characters...), and it's value. To mark the end, the
 * compound ends with {@code 00000000} (end tag).
 * <br/>
 * <pre>
 * Prefix   1: Type  Name length      Name val Value    2: End
 * 00001001 00000001 0000000000000001 01100001 00001011 00000000
 * COMPOUND BYTE     = 1              = "a"    = 11     END
 * </pre>
 * The {@code TagEnd} is used to mark the end of the compound and is not added
 * to the parsed value.
 * @since 1.0
 */
public class TagCompound implements ITag<Compound> {
    private Compound compound;

    public TagCompound() {
        compound = new Compound();
    }

    public TagCompound( Compound comp ) {
        compound = comp;
    }

    public TagCompound( Map<String, ? extends ITag> arr ) {
        compound = new Compound( arr );
    }

    @Override
    public Binary encode() {
        Binary.Builder builder = new Binary.Builder();
        KeyValuePair[] pairs = compound.getPairs();
        for( KeyValuePair pair : pairs ) {
            builder.append( pair.encode() );
        }
        builder.append( new byte[] { 0 } );
        return builder.build();
    }

    @Override
    public Compound getValue() {
        return compound;
    }

    @Override
    public void setValue( Compound value ) {
        compound = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1001 );
    }

    /**
     * Parses a CTag code as a compound.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              compound data.
     * @return The parsed {@link TagCompound} if parsed with success.
     * @exception IOException          If the {@link CTagInput}'s underlying
     *                                 stream throws an IOException.
     * @exception CTagInvalidException If an invalid prefix is found.
     * @since 1.0
     */
    public static TagCompound parse( CTagInput input ) throws IOException, CTagInvalidException, EndException, NegativeLengthException {
        Compound value = new Compound();
        boolean foundEnd = false;
        while( !foundEnd ) {
            Binary prefixBin = input.read( 1 );
            byte prefix = prefixBin.getByte( 0 );
            if( prefix == 0 ) {
                foundEnd = true;
            } else {
                String name = TagString.parse( input ).getValue();
                if( prefix == 1 ) {
                    value.put( name, TagByte.parse( input ) );
                } else if( prefix == 2 ) {
                    value.put( name, TagShort.parse( input ) );
                } else if( prefix == 3 ) {
                    value.put( name, TagInteger.parse( input ) );
                } else if( prefix == 4 ) {
                    value.put( name, TagLong.parse( input ) );
                } else if( prefix == 5 ) {
                    value.put( name, TagFloat.parse( input ) );
                } else if( prefix == 6 ) {
                    value.put( name, TagDouble.parse( input ) );
                } else if( prefix == 7 ) {
                    value.put( name, TagString.parse( input ) );
                } else if( prefix == 8 ) {
                    value.put( name, TagArray.parse( input ) );
                } else if( prefix == 9 ) {
                    value.put( name, TagCompound.parse( input ) );
                } else if( prefix == 10 ) {
                    value.put( name, TagNull.parse( input ) );
                } else if( prefix == 11 ) {
                    value.put( name, TagBoolean.parse( input ) );
                } else if( prefix == 12 ) {
                    value.put( name, TagByteArray.parse( input ) );
                } else if( prefix == 13 ) {
                    value.put( name, TagShortArray.parse( input ) );
                } else if( prefix == 14 ) {
                    value.put( name, TagIntegerArray.parse( input ) );
                } else if( prefix == 15 ) {
                    value.put( name, TagLongArray.parse( input ) );
                } else if( prefix == 16 ) {
                    value.put( name, TagFloatArray.parse( input ) );
                } else if( prefix == 17 ) {
                    value.put( name, TagDoubleArray.parse( input ) );
                } else if( prefix == 18 ) {
                    value.put( name, TagBooleanArray.parse( input ) );
                } else if( prefix == 19 ) {
                    value.put( name, TagStringArray.parse( input ) );
                } else {
                    throw new CTagInvalidException( "Found invalid prefix: '" + prefixBin + "'." );
                }
            }
        }
        return new TagCompound( value );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "COMPOUND {\n" );
        KeyValuePair[] pairs = compound.getPairs();
        for( KeyValuePair pair : pairs ) {
            ITag tag = pair.value;
            builder.append( "    " );
            builder.append( pair.key );
            builder.append( ": " );
            String[] lines = tag.toString().split( "[\n\r]" );
            int j = 0;
            for( String line : lines ) {
                if( j == 0 ) {
                    builder.append( line );
                    if( j != lines.length - 1 ) builder.append( "\n" );
                } else if( j == lines.length - 1 ) {
                    builder.append( "    " );
                    builder.append( line );
                } else {
                    builder.append( "    " );
                    builder.append( line );
                    builder.append( "\n" );
                }
                j++;
            }
            builder.append( "\n" );
        }
        return builder.append( "}" ).toString();
    }
}
