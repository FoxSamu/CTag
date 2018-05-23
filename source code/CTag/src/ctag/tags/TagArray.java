package ctag.tags;

import ctag.Array;
import ctag.Binary;
import ctag.CTagInput;
import ctag.exception.CTagInvalidException;

import java.io.IOException;
import java.util.Collection;

/**
 * The tag that represents an any-type array, and order is preserved.
 * <br/><br/>
 * <table>
 * <tr>
 * <td><b>Binary prefix: </b></td>
 * <td><code>00001000 - 08</code></td>
 * </tr>
 * <tr>
 * <td><b>Minimal payload: </b></td>
 * <td>2 bytes</td>
 * </tr>
 * <tr>
 * <td><b>Maximal payload: </b></td>
 * <td>Infinite</td>
 * </tr>
 * </table>
 * The array binary starts with two bytes holding the length, followed by
 * prefixed, nameless tags:
 * <br/>
 * <pre>
 * Prefix   Length           1th element       2nd element               ...
 * 00001000 0000000000000010 00000001 00000000 00000010 0000000000001011
 * ARRAY    length = 2       BYTE     = 0      SHORT    = 11
 * </pre>
 * The {@link TagEnd} is not allowed in an array
 * @since 1.0
 */
public class TagArray implements ITag<Array> {
    private Array array;

    public TagArray() {
        array = new Array();
    }

    public TagArray( Array arr ) {
        array = arr;
    }

    public TagArray( Collection<? extends ITag> arr ) {
        array = new Array( arr );
    }

    public TagArray( ITag... arr ) {
        array = new Array( arr );
    }

    @Override
    public Binary encode() {
        ITag[] tags = array.getTags();
        Binary binary = new TagShort( array.size() ).encode();
        Binary.Builder builder = new Binary.Builder();
        for( ITag tag : tags ) {
            builder.append( tag.getPrefixByte() );
            builder.append( tag.encode() );
        }
        builder.prepend( binary );
        return builder.build();
    }

    @Override
    public Array getValue() {
        return array;
    }

    @Override
    public void setValue( Array value ) {
        array = value;
    }

    @Override
    public Binary getPrefixByte() {
        return new Binary( ( byte ) 0b1000 );
    }

    /**
     * Parses a CTag code as an array.
     * @param input The {@link CTagInput} stream that possibly begins with this
     *              array data.
     * @return The parsed {@link TagArray} if parsed with success.
     * @exception IOException          If the {@link CTagInput}'s underlying
     *                                 stream throws an IOException.
     * @exception CTagInvalidException If a {@link TagEnd} or an invalid prefix
     *                                 is found.
     * @since 1.0
     */
    public static TagArray parse( CTagInput input ) throws IOException, CTagInvalidException {
        Array value = new Array();
        short len = TagShort.parse( input ).getValue();
        for( int i = 0; i < len; i++ ) {
            Binary prefixBin = input.read( 1 );
            byte prefix = prefixBin.getByte( 0 );
            if( prefix == 0 ) {
                throw new CTagInvalidException( "Found a TagEnd in TagArray." );
            } else if( prefix == 1 ) {
                value.add( TagByte.parse( input ) );
            } else if( prefix == 2 ) {
                value.add( TagShort.parse( input ) );
            } else if( prefix == 3 ) {
                value.add( TagInteger.parse( input ) );
            } else if( prefix == 4 ) {
                value.add( TagLong.parse( input ) );
            } else if( prefix == 5 ) {
                value.add( TagFloat.parse( input ) );
            } else if( prefix == 6 ) {
                value.add( TagDouble.parse( input ) );
            } else if( prefix == 7 ) {
                value.add( TagString.parse( input ) );
            } else if( prefix == 8 ) {
                value.add( TagArray.parse( input ) );
            } else if( prefix == 9 ) {
                value.add( TagCompound.parse( input ) );
            } else if( prefix == 10 ) {
                value.add( TagNull.parse( input ) );
            } else if( prefix == 11 ) {
                value.add( TagBoolean.parse( input ) );
            } else if( prefix == 12 ) {
                value.add( TagByteArray.parse( input ) );
            } else if( prefix == 13 ) {
                value.add( TagShortArray.parse( input ) );
            } else if( prefix == 14 ) {
                value.add( TagIntegerArray.parse( input ) );
            } else if( prefix == 15 ) {
                value.add( TagLongArray.parse( input ) );
            } else if( prefix == 16 ) {
                value.add( TagFloatArray.parse( input ) );
            } else if( prefix == 17 ) {
                value.add( TagDoubleArray.parse( input ) );
            } else if( prefix == 18 ) {
                value.add( TagBooleanArray.parse( input ) );
            } else {
                throw new CTagInvalidException( "Found invalid tag prefix: '" + prefixBin + "'." );
            }
        }
        return new TagArray( value );
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "ARRAY [\n" );
        int i = 0;
        for( ITag tag : array ) {
            String s = i + "";
            builder.append( "    " );
            builder.append( i );
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
            i++;
        }
        return builder.append( "]" ).toString();
    }
}
