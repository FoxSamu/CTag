package ctag;

import ctag.exception.CTagInvalidException;
import ctag.exception.EndException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Used to read bytes using an input stream
 * @since 1.0
 */
public class CTagInput {
    private InputStream input;

    /**
     * Makes a {@link CTagInput} using an underlying input stream
     * @param stream
     * @since 1.0
     */
    public CTagInput( InputStream stream ) {
        input = stream;
    }

    /**
     * Reads a specific amount of bytes
     * @param bytes The amount of bytes
     * @return A binary containing the read bytes
     * @exception IOException When the underlying input stream throws an
     *                        {@link IOException}
     * @since 1.0
     */
    public Binary read( int bytes ) throws IOException, EndException {
        Binary.Builder builder = new Binary.Builder();
        for( int i = 0; i < bytes; i++ ) {
            int bits = input.read();
            if( bits > -1 ) {
                builder.append( new byte[] {
                        ( byte ) bits
                } );
            } else {
                throw new EndException( "The input stream does not provide any more bytes." );
            }
        }
        return builder.build();
    }

    /**
     * Closes the stream
     * @exception IOException When the underlying input stream throws an
     *                        {@link IOException}
     * @since 1.0
     */
    public void close() throws IOException {
        input.close();
    }
}
