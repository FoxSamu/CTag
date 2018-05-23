package ctag;

import ctag.exception.NoSuchKeyException;
import ctag.exception.TagEndException;
import ctag.exception.WrongTagException;
import ctag.tags.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a {@link String}-{@link ITag} map. Used by the {@link TagCompound}
 * as value.
 * @since 1.0
 */
public class Compound {
    private HashMap<String, ITag> values;

    /**
     * Makes an empty compound
     * @since 1.0
     */
    public Compound() {
        values = new HashMap<>();
    }

    /**
     * Makes a compound with values from map
     * @param values The map containing the values
     * @since 1.0
     */
    public Compound( Map<String, ? extends ITag> values ) {
        this.values = new HashMap<>( values );
    }

    private void checkKey( String key ) throws NoSuchKeyException {
        if( key == null ) {
            throw new NullPointerException( "Key is null. Not allowed." );
        }
        if( !values.containsKey( key ) ) {
            throw new NoSuchKeyException( "Key '" + key + "' does not exist in this compound." );
        }
    }

    private void checkTag( ITag tag ) {
        if( tag instanceof TagEnd ) {
            throw new TagEndException( "TagEnd not allowed." );
        }
    }

    /**
     * Puts a specific tag into the compound
     * @param key   The key for this tag
     * @param value The specific tag
     * @since 1.0
     */
    public Compound put( String key, ITag value ) {
        checkTag( value );
        values.put( key, value );
        return this;
    }

    /**
     * Puts a long tag into the compound
     * @param key   The key for this tag
     * @param value The long value for the tag
     * @since 1.0
     */
    public Compound put( String key, long value ) {
        return put( key, new TagLong( value ) );
    }

    /**
     * Puts an integer tag into the compound
     * @param key   The key for this tag
     * @param value The integer value for the tag
     * @since 1.0
     */
    public Compound put( String key, int value ) {
        return put( key, new TagInteger( value ) );
    }

    /**
     * Puts a short tag into the compound
     * @param key   The key for this tag
     * @param value The short value for the tag
     * @since 1.0
     */
    public Compound put( String key, short value ) {
        return put( key, new TagShort( value ) );
    }

    /**
     * Puts a byte tag into the compound
     * @param key   The key for this tag
     * @param value The byte value for the tag
     * @since 1.0
     */
    public Compound put( String key, byte value ) {
        return put( key, new TagByte( value ) );
    }

    /**
     * Puts a double tag into the compound
     * @param key   The key for this tag
     * @param value The double value for the tag
     * @since 1.0
     */
    public Compound put( String key, double value ) {
        return put( key, new TagDouble( value ) );
    }

    /**
     * Puts a float tag into the compound
     * @param key   The key for this tag
     * @param value The float value for the tag
     * @since 1.0
     */
    public Compound put( String key, float value ) {
        return put( key, new TagFloat( value ) );
    }

    /**
     * Puts a boolean tag into the compound
     * @param key   The key for this tag
     * @param value The boolean value for the tag
     * @since 1.0
     */
    public Compound put( String key, boolean value ) {
        return put( key, new TagBoolean( value ) );
    }

    /**
     * Puts a string tag into the compound
     * @param key   The key for this tag
     * @param value The string value for the tag
     * @since 1.0
     */
    public Compound put( String key, String value ) {
        return put( key, new TagString( value ) );
    }

    /**
     * Puts an array tag into the compound
     * @param key   The key for this tag
     * @param value The array value for the tag
     * @since 1.0
     */
    public Compound put( String key, Array value ) {
        return put( key, new TagArray( value ) );
    }

    /**
     * Puts a compound tag into the compound
     * @param key   The key for this tag
     * @param value The compound value for the tag
     * @since 1.0
     */
    public Compound put( String key, Compound value ) {
        return put( key, new TagCompound( value ) );
    }

    /**
     * Returns a tag from the compound
     * @param key The key of the tag
     * @return The tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @since 1.0
     */
    public ITag get( String key ) throws NoSuchKeyException {
        checkKey( key );
        return values.get( key );
    }

    /**
     * Returns an integer tag from the compound
     * @param key The key of the tag
     * @return The integer tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not an integer tag
     * @since 1.0
     */
    public TagInteger getTagInteger( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagInteger ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagInteger, but did not found that." );
        }
    }

    /**
     * Returns a long tag from the compound
     * @param key The key of the tag
     * @return The long tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a long tag
     * @since 1.0
     */
    public TagLong getTagLong( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagLong ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagLong, but did not found that." );
        }
    }

    /**
     * Returns a short tag from the compound
     * @param key The key of the tag
     * @return The short tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a short tag
     * @since 1.0
     */
    public TagShort getTagShort( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagShort ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagShort, but did not found that." );
        }
    }

    /**
     * Returns a byte tag from the compound
     * @param key The key of the tag
     * @return The byte tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a byte tag
     * @since 1.0
     */
    public TagByte getTagByte( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagByte ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagByte, but did not found that." );
        }
    }

    /**
     * Returns a double tag from the compound
     * @param key The key of the tag
     * @return The double tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a double tag
     * @since 1.0
     */
    public TagDouble getTagDouble( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagDouble ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagDouble, but did not found that." );
        }
    }

    /**
     * Returns a float tag from the compound
     * @param key The key of the tag
     * @return The float tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a float tag
     * @since 1.0
     */
    public TagFloat getTagFloat( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagFloat ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagFloat, but did not found that." );
        }
    }

    /**
     * Returns a boolean tag from the compound
     * @param key The key of the tag
     * @return The boolean tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a boolean tag
     * @since 1.0
     */
    public TagBoolean getTagBoolean( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagBoolean ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagBoolean, but did not found that." );
        }
    }

    /**
     * Returns a null tag from the compound
     * @param key The key of the tag
     * @return The null tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a null tag
     * @since 1.0
     */
    public TagNull getTagNull( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagNull ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagNull, but did not found that." );
        }
    }

    /**
     * Returns a string tag from the compound
     * @param key The key of the tag
     * @return The string tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a string tag
     * @since 1.0
     */
    public TagString getTagString( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagString ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagString, but did not found that." );
        }
    }

    /**
     * Returns an array tag from the compound
     * @param key The key of the tag
     * @return The array tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not an array tag
     * @since 1.0
     */
    public TagArray getTagArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagArray, but did not found that." );
        }
    }

    /**
     * Returns a compound tag from the compound
     * @param key The key of the tag
     * @return The compound tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a compound tag
     * @since 1.0
     */
    public TagCompound getTagCompound( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagCompound ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagCompound, but did not found that." );
        }
    }

    /**
     * Returns the value of an integer tag from the compound
     * @param key The key of the tag
     * @return The integer value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not an integer tag
     * @since 1.0
     */
    public int getInteger( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagInteger ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Integer, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a long tag from the compound
     * @param key The key of the tag
     * @return The long value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a long tag
     * @since 1.0
     */
    public long getLong( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagLong ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Long, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a short tag from the compound
     * @param key The key of the tag
     * @return The short value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a short tag
     * @since 1.0
     */
    public short getShort( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagShort ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Short, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a byte tag from the compound
     * @param key The key of the tag
     * @return The byte value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a byte tag
     * @since 1.0
     */
    public byte getByte( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagByte ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Byte, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a double tag from the compound
     * @param key The key of the tag
     * @return The double value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a double tag
     * @since 1.0
     */
    public double getDouble( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagDouble ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Double, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a float tag from the compound
     * @param key The key of the tag
     * @return The float value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a float tag
     * @since 1.0
     */
    public float getFloat( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagFloat ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Float, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a boolean tag from the compound
     * @param key The key of the tag
     * @return The boolean value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a boolean tag
     * @since 1.0
     */
    public boolean getBoolean( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagBoolean ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Boolean, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a string tag from the compound
     * @param key The key of the tag
     * @return The string value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a string tag
     * @since 1.0
     */
    public String getString( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagString ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for String, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of an array tag from the compound
     * @param key The key of the tag
     * @return The array value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not an array tag
     * @since 1.0
     */
    public Array getArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Array, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a compound tag from the compound
     * @param key The key of the tag
     * @return The compound value
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not a compound tag
     * @since 1.0
     */
    public Compound getCompound( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagCompound ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Compound, but did not found tag for that." );
        }
    }

    /**
     * Removes a tag from the compound
     * @param key The key of the tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     */
    public Compound remove( String key ) throws NoSuchKeyException {
        checkKey( key );
        values.remove( key );
        return this;
    }

    /**
     * Checks if the compound contains a key
     * @param key The key
     * @return True if contained
     */
    public boolean hasKey( String key ) {
        return values.containsKey( key );
    }

    /**
     * Checks if the compound contains a tag
     * @param value The tag
     * @return True if contained
     */
    public boolean hasValue( ITag value ) {
        return values.containsValue( value );
    }


    /**
     * Returns the {@link KeyValuePair}{@code []} for this compound
     */
    public KeyValuePair[] getPairs() {
        Set<String> keys = values.keySet();
        ArrayList<KeyValuePair> pairs = new ArrayList<>();
        for( String key : keys ) {
            pairs.add( new KeyValuePair( key, values.get( key ) ) );
        }
        return pairs.toArray( new KeyValuePair[ 0 ] );
    }
}
