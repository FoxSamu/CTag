package ctag;

import ctag.exception.Base64Exception;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a series of bytes holding some binary information.
 * @since 1.0
 */
public class Binary {

    private static final char[] BASE64ARR = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-'
    };
    // Dammet default value, I want -1
    private static final byte[] BASE64REVERSE = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    static {
        for( int i = 0; i < BASE64ARR.length; i++ ) {
            BASE64REVERSE[ BASE64ARR[ i ] ] = ( byte ) i;
        }
    }

    private byte[] bytes;

    /**
     * Makes an empty binary
     * @since 1.0
     */
    public Binary() {
        bytes = new byte[ 0 ];
    }

    /**
     * Makes a binary containing one byte
     * @param b The byte to contain
     * @since 1.0
     */
    public Binary( byte b ) {
        bytes = new byte[] { b };
    }

    /**
     * Makes a binary containing more than one byte
     * @param b The byte array to contain
     * @since 1.0
     */
    public Binary( byte[] b ) {
        bytes = b;
    }

    /**
     * Parses a base-64 string and uses the output as binary.
     * @param b The base-64 string
     * @since 1.0
     */
    public Binary( String b ) {
        if( b.length() < 1 ) {
            bytes = new byte[ 0 ];
            return;
        }
        if( b.length() == 1 ) {
            throw new Base64Exception( "Base64 binary contains only one byte, needs at least 2, or exactly 0." );
        }

        bytes = b.getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream( bytes );
        Builder builder = new Builder();
        while( true ) {
            int byte1 = stream.read();
            if( byte1 == -1 ) {
                break;
            }

            int byte2 = stream.read();
            if( byte2 == -1 ) {
                break;
            } else {
                builder.append( new byte[] {
                        ( byte ) ( ( BASE64REVERSE[ byte1 ] << 2 ) + ( ( BASE64REVERSE[ byte2 ] & 63 ) >>> 4 ) )
                } );
            }

            int byte3 = stream.read();
            if( byte3 == -1 ) {
                break;
            } else {
                builder.append( new byte[] {
                        ( byte ) ( ( BASE64REVERSE[ byte2 ] << 4 ) + ( ( BASE64REVERSE[ byte3 ] & 63 ) >>> 2 ) )
                } );
            }
            int byte4 = stream.read();
            if( byte4 == -1 ) {
                break;
            } else {
                builder.append( new byte[] {
                        ( byte ) ( ( BASE64REVERSE[ byte3 ] << 6 ) + ( BASE64REVERSE[ byte4 ] & 63 ) )
                } );
            }
        }
        bytes = builder.build().bytes;
    }

    /**
     * Makes a binary from a {@link Byte}{@code []} object
     * @param b The {@link Byte}{@code []} containing the bytes
     * @since 1.0
     */
    public Binary( Byte[] b ) {
        byte[] bytes = new byte[ b.length ];
        for( int i = 0; i < b.length; i++ ) {
            bytes[ i ] = b[ i ];
        }
        this.bytes = bytes;
    }

    /**
     * Returns a byte from the binary
     * @param index The byte index
     * @return The byte at index
     * @since 1.0
     */
    public byte getByte( int index ) {
        return bytes[ index ];
    }

    /**
     * Returns the amount of bytes in the binary
     * @return The amount of bytes in the binary
     * @since 1.0
     */
    public int size() {
        return bytes.length;
    }

    private static Byte[] copy( byte[] b ) {
        Byte[] out = new Byte[ b.length ];
        for( int i = 0; i < b.length; i++ ) {
            out[ i ] = b[ i ];
        }
        return out;
    }

    /**
     * A {@link Binary.Builder} can be used to make a binary by appending
     * multiple byte arrays or binaries to it.
     * @since 1.0
     */
    public static class Builder {
        ArrayList<Byte[]> bytes = new ArrayList<>();

        /**
         * @since 1.0
         */
        public void append( Byte[] bytes ) {
            this.bytes.add( bytes );
        }

        /**
         * @since 1.0
         */
        public void prepend( Byte[] bytes ) {
            this.bytes.add( 0, bytes );
        }

        /**
         * @since 1.0
         */
        public void append( byte[] bytes ) {
            this.bytes.add( copy( bytes ) );
        }

        /**
         * @since 1.0
         */
        public void prepend( byte[] bytes ) {
            this.bytes.add( 0, copy( bytes ) );
        }

        /**
         * @since 1.0
         */
        public void append( Binary bytes ) {
            append( bytes.bytes );
        }

        /**
         * @since 1.0
         */
        public void prepend( Binary bytes ) {
            prepend( bytes.bytes );
        }

        /**
         * @since 1.0
         */
        public Binary build() {
            ArrayList<Byte> binary = new ArrayList<>();
            for( Byte[] bytes : bytes ) {
                binary.addAll( Arrays.asList( bytes ) );
            }
            return new Binary( binary.<Byte> toArray( new Byte[ 0 ] ) );
        }
    }

    /**
     * Converts the binary to a binary (01) string
     * @since 1.0
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for( byte b : bytes ) {
            String str = Integer.toString( b & 0xff, 2 );
            builder.append( "00000000".substring( str.length() ) );
            builder.append( str );
            builder.append( " " );
        }
        String str = builder.toString();
        return str.substring( 0, str.length() - 1 );
    }

    /**
     * Converts the binary to an hexadecimal (0123456789abcdef) string
     * @since 1.0
     */
    public String toHexString() {
        StringBuilder builder = new StringBuilder();
        for( byte b : bytes ) {
            String str = Integer.toString( b & 0xff, 16 );
            builder.append( "00".substring( str.length() ) );
            builder.append( str );
            builder.append( " " );
        }
        String str = builder.toString();
        return str.substring( 0, str.length() - 1 );
    }

    /**
     * Returns the byte array of this binary
     * @return The byte array of this binary
     * @since 1.0
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Converts the byte array to a base-64 string, which can be compiled back
     * to a binary by the {@link Binary}{@link Binary(String)} constructor.
     * @return A base-64 string.
     * @since 1.0
     */
    public String makeString() {
        StringBuilder builder = new StringBuilder();
        ByteArrayInputStream stream = new ByteArrayInputStream( bytes );
        while( true ) {
            int byte1 = stream.read();
            if( byte1 == -1 ) {
                break;
            }

            int byte2 = stream.read();
            if( byte2 == -1 ) {
                int index1 = byte1 >>> 2;
                int index2 = ( byte1 & 3 ) << 4;
                builder.append( BASE64ARR[ index1 ] );
                builder.append( BASE64ARR[ index2 ] );
                break;
            }

            int byte3 = stream.read();
            if( byte3 == -1 ) {
                int index1 = byte1 >>> 2;
                int index2 = ( ( byte1 & 3 ) << 4 ) + ( byte2 >>> 4 );
                int index3 = ( byte2 & 15 ) << 2;
                builder.append( BASE64ARR[ index1 ] );
                builder.append( BASE64ARR[ index2 ] );
                builder.append( BASE64ARR[ index3 ] );
                break;
            } else {
                int index1 = byte1 >>> 2;
                int index2 = ( ( byte1 & 3 ) << 4 ) + ( byte2 >>> 4 );
                int index3 = ( ( byte2 & 15 ) << 2 ) + ( byte3 >>> 6 );
                int index4 = byte3 & 63;
                builder.append( BASE64ARR[ index1 ] );
                builder.append( BASE64ARR[ index2 ] );
                builder.append( BASE64ARR[ index3 ] );
                builder.append( BASE64ARR[ index4 ] );
            }
        }
        return builder.toString();
    }
}
