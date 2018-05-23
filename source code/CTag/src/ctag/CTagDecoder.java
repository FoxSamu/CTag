package ctag;

import ctag.exception.CTagInvalidException;
import ctag.tags.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Decodes a ctag code
 * @since 1.0
 */
public class CTagDecoder {
    private CTagInput input;

    /**
     * Decodes from a {@link CTagInput} stream
     * @param input The {@link CTagInput}
     * @since 1.0
     */
    public CTagDecoder( CTagInput input ) {
        this.input = input;
    }

    /**
     * Decodes from an {@link InputStream}
     * @param stream The {@link InputStream}
     * @since 1.0
     */
    public CTagDecoder( InputStream stream ) {
        input = new CTagInput( stream );
    }

    /**
     * Decodes from a string
     * @param string The string
     * @since 1.0
     */
    public CTagDecoder( String string ) {
        Binary b = new Binary( string );
        input = new CTagInput( new ByteArrayInputStream( b.getBytes() ) );
    }

    /**
     * Decodes from a byte array
     * @param bytes The byte array
     * @since 1.0
     */
    public CTagDecoder( byte[] bytes ) {
        input = new CTagInput( new ByteArrayInputStream( bytes ) );
    }

    /**
     * Decodes from a {@link Binary}
     * @param bytes The {@link Binary}
     * @since 1.0
     */
    public CTagDecoder( Binary bytes ) {
        input = new CTagInput( new ByteArrayInputStream( bytes.getBytes() ) );
    }

    /**
     * Decodes the CTag code
     * @return The {@link ITag} this CTag code stores
     * @exception IOException          If {@link CTagInput}'s underlying input stream
     *                                 throws an {@link IOException}.
     * @exception CTagInvalidException If the CTag code is invalid.
     * @since 1.0
     */
    public ITag decode() throws IOException, CTagInvalidException {
        Binary type = input.read( 1 );
        byte typeByte = type.getByte( 0 );

        if( typeByte == 0 ) {
            throw new CTagInvalidException( "Found TagEnd as main type. TagEnd should not be used." );
        } else if( typeByte == 1 ) {
            return TagByte.parse( input );
        } else if( typeByte == 2 ) {
            return TagShort.parse( input );
        } else if( typeByte == 3 ) {
            return TagInteger.parse( input );
        } else if( typeByte == 4 ) {
            return TagLong.parse( input );
        } else if( typeByte == 5 ) {
            return TagFloat.parse( input );
        } else if( typeByte == 6 ) {
            return TagDouble.parse( input );
        } else if( typeByte == 7 ) {
            return TagString.parse( input );
        } else if( typeByte == 8 ) {
            return TagArray.parse( input );
        } else if( typeByte == 9 ) {
            return TagCompound.parse( input );
        } else if( typeByte == 10 ) {
            return TagNull.parse( input );
        } else if( typeByte == 11 ) {
            return TagBoolean.parse( input );
        } else if( typeByte == 12 ) {
            return TagByteArray.parse( input );
        } else if( typeByte == 13 ) {
            return TagShortArray.parse( input );
        } else if( typeByte == 14 ) {
            return TagIntegerArray.parse( input );
        } else if( typeByte == 15 ) {
            return TagLongArray.parse( input );
        } else if( typeByte == 16 ) {
            return TagFloatArray.parse( input );
        } else if( typeByte == 17 ) {
            return TagDoubleArray.parse( input );
        } else if( typeByte == 18 ) {
            return TagBooleanArray.parse( input );
        } else {
            throw new CTagInvalidException( "Found invalid prefix: '" + type + "'." );
        }
    }
}
