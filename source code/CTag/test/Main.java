import ctag.Binary;
import ctag.CTagDecoder;
import ctag.CTagEncoder;
import ctag.exception.CTagInvalidException;
import ctag.exception.EndException;
import ctag.exception.NegativeLengthException;
import ctag.tags.ITag;
import ctag.tags.TagStringArray;

import java.io.IOException;

public class Main {
    public static void main( String[] args ) throws IOException, CTagInvalidException, EndException, NegativeLengthException {
        ITag tag = new TagStringArray( "Hüj", "Tëst", "String", "Array", "Works" );
        CTagEncoder encoder = new CTagEncoder( tag );
        Binary b = encoder.encode();
        String encoded = b.makeString();
        System.out.println( encoded );
        CTagDecoder decoder = new CTagDecoder( encoded );
        System.out.println( decoder.decode() );
    }
}
