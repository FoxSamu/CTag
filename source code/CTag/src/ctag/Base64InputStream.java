package ctag;

import ctag.exception.Base64Exception;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * InputStream that reads base-64 encoded bytes. The ouptut gives decoded bytes.
 * The decoder uses this to read a file, without decoding the file completely
 * before decoding. It rather decodes each 3 bytes (4 B64 bytes).
 */
public class Base64InputStream extends InputStream {
    private ArrayList<Byte> bytes = new ArrayList<>();
    private InputStream base64;

    public Base64InputStream( InputStream stream ) throws IOException {
        base64 = stream;
        loadBytes();
    }


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
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };

    static {
        for( int i = 0; i < BASE64ARR.length; i++ ) {
            BASE64REVERSE[ BASE64ARR[ i ] ] = ( byte ) i;
        }
    }

    private void loadBytes() throws IOException {
        while( true ) {
            int byte1 = base64.read();
            if( byte1 == -1 ) {
                break;
            }

            int byte2 = base64.read();
            if( byte2 == -1 ) {
                break;
            } else {
                bytes.add( ( byte ) ( ( BASE64REVERSE[ byte1 ] << 2 ) + ( ( BASE64REVERSE[ byte2 ] & 63 ) >>> 4 ) ) );
            }

            int byte3 = base64.read();
            if( byte3 == -1 ) {
                break;
            } else {
                bytes.add( ( byte ) ( ( BASE64REVERSE[ byte2 ] << 4 ) + ( ( BASE64REVERSE[ byte3 ] & 63 ) >>> 2 ) ) );
            }
            int byte4 = base64.read();
            if( byte4 == -1) {
                break;
            } else {
                bytes.add( ( byte ) ( ( BASE64REVERSE[ byte3 ] << 6 ) + ( BASE64REVERSE[ byte4 ] & 63 ) ) );
            }
        }
    }

    @Override
    public int read() throws IOException {
        if( !bytes.isEmpty() ) {
            Byte r = bytes.remove( 0 );
            if( r == null ) {
                throw new IOException( "Parsed byte is null? Not possible..." );
            }
            if( bytes.isEmpty() ) {
                loadBytes();
            }
            return r & 0xFF;
        }
        return -1;
    }
}
