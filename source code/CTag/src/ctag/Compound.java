package ctag;

import ctag.exception.NoSuchKeyException;
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

    /**
     * Puts a specific tag into the compound
     * @param key   The key for this tag
     * @param value The specific tag
     * @since 1.0
     */
    public Compound put( String key, ITag value ) {
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
     * Puts a byte array tag into the compound
     * @param key   The key for this tag
     * @param value The byte array value for the tag
     * @since 1.1
     */
    public Compound put( String key, byte... value ) {
        return put( key, new TagByteArray( value ) );
    }

    /**
     * Puts a short array tag into the compound
     * @param key   The key for this tag
     * @param value The short array value for the tag
     * @since 1.1
     */
    public Compound put( String key, short... value ) {
        return put( key, new TagShortArray( value ) );
    }

    /**
     * Puts an integer array tag into the compound
     * @param key   The key for this tag
     * @param value The integer array value for the tag
     * @since 1.1
     */
    public Compound put( String key, int... value ) {
        return put( key, new TagIntegerArray( value ) );
    }

    /**
     * Puts a long array tag into the compound
     * @param key   The key for this tag
     * @param value The long array value for the tag
     * @since 1.1
     */
    public Compound put( String key, long... value ) {
        return put( key, new TagLongArray( value ) );
    }

    /**
     * Puts a float array tag into the compound
     * @param key   The key for this tag
     * @param value The float array value for the tag
     * @since 1.1
     */
    public Compound put( String key, float... value ) {
        return put( key, new TagFloatArray( value ) );
    }

    /**
     * Puts a double array tag into the compound
     * @param key   The key for this tag
     * @param value The double array value for the tag
     * @since 1.1
     */
    public Compound put( String key, double... value ) {
        return put( key, new TagDoubleArray( value ) );
    }

    /**
     * Puts a boolean array tag into the compound
     * @param key   The key for this tag
     * @param value The boolean array value for the tag
     * @since 1.1
     */
    public Compound put( String key, boolean... value ) {
        return put( key, new TagBooleanArray( value ) );
    }

    /**
     * Puts a string array tag into the compound
     * @param key   The key for this tag
     * @param value The string array value for the tag
     * @since 1.1
     */
    public Compound put( String key, String... value ) {
        return put( key, new TagStringArray( value ) );
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
     * Returns a byte array tag from the compound
     * @param key The key of the tag
     * @return The byte array tag
     * @exception NoSuchKeyException When the key is not contained by the byte array
     * @exception WrongTagException  When the tag is not a byte array tag
     * @since 1.1
     */
    public TagByteArray getTagByteArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagByteArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagByteArray, but did not found that." );
        }
    }

    /**
     * Returns a short array tag from the compound
     * @param key The key of the tag
     * @return The short array tag
     * @exception NoSuchKeyException When the key is not contained by the short array
     * @exception WrongTagException  When the tag is not a short array tag
     * @since 1.1
     */
    public TagShortArray getTagShortArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagShortArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagShortArray, but did not found that." );
        }
    }

    /**
     * Returns an integer array tag from the compound
     * @param key The key of the tag
     * @return The integer array tag
     * @exception NoSuchKeyException When the key is not contained by the integer array
     * @exception WrongTagException  When the tag is not an integer array tag
     * @since 1.1
     */
    public TagIntegerArray getTagIntegerArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagIntegerArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagIntegerArray, but did not found that." );
        }
    }

    /**
     * Returns a long array tag from the compound
     * @param key The key of the tag
     * @return The long array tag
     * @exception NoSuchKeyException When the key is not contained by the long array
     * @exception WrongTagException  When the tag is not a long array tag
     * @since 1.1
     */
    public TagLongArray getTagLongArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagLongArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagLongArray, but did not found that." );
        }
    }

    /**
     * Returns a float array tag from the compound
     * @param key The key of the tag
     * @return The float array tag
     * @exception NoSuchKeyException When the key is not contained by the float array
     * @exception WrongTagException  When the tag is not a float array tag
     * @since 1.1
     */
    public TagFloatArray getTagFloatArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagFloatArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagFloatArray, but did not found that." );
        }
    }

    /**
     * Returns a double array tag from the compound
     * @param key The key of the tag
     * @return The double array tag
     * @exception NoSuchKeyException When the key is not contained by the double array
     * @exception WrongTagException  When the tag is not a double array tag
     * @since 1.1
     */
    public TagDoubleArray getTagDoubleArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagDoubleArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagDoubleArray, but did not found that." );
        }
    }

    /**
     * Returns a boolean array tag from the compound
     * @param key The key of the tag
     * @return The boolean array tag
     * @exception NoSuchKeyException When the key is not contained by the boolean array
     * @exception WrongTagException  When the tag is not a boolean array tag
     * @since 1.1
     */
    public TagBooleanArray getTagBooleanArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagBooleanArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagBooleanArray, but did not found that." );
        }
    }

    /**
     * Returns a string array tag from the compound
     * @param key The key of the tag
     * @return The string array tag
     * @exception NoSuchKeyException When the key is not contained by thestringe array
     * @exception WrongTagException  When the tag is not a string array tag
     * @since 1.1
     */
    public TagStringArray getTagStringArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( TagStringArray ) values.get( key );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagStringArray, but did not found that." );
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
     * Returns the value of a byte array tag from the compound
     * @param key The key of the tag
     * @return The byte array value
     * @exception NoSuchKeyException When the key is not contained by the byte array
     * @exception WrongTagException  When the tag is not a byte array tag
     * @since 1.1
     */
    public byte[] getByteArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagByteArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for ByteArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a short array tag from the compound
     * @param key The key of the tag
     * @return The short array value
     * @exception NoSuchKeyException When the key is not contained by the short array
     * @exception WrongTagException  When the tag is not a short array tag
     * @since 1.1
     */
    public short[] getShortArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagShortArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for ShortArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of an integer array tag from the compound
     * @param key The key of the tag
     * @return The integer array value
     * @exception NoSuchKeyException When the key is not contained by the integer array
     * @exception WrongTagException  When the tag is not an integer array tag
     * @since 1.1
     */
    public int[] getIntegerArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagIntegerArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for IntegerArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a long array tag from the compound
     * @param key The key of the tag
     * @return The long array value
     * @exception NoSuchKeyException When the key is not contained by the long array
     * @exception WrongTagException  When the tag is not a long array tag
     * @since 1.1
     */
    public long[] getLongArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagLongArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for LongArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a float array tag from the compound
     * @param key The key of the tag
     * @return The float array value
     * @exception NoSuchKeyException When the key is not contained by the float array
     * @exception WrongTagException  When the tag is not a float array tag
     * @since 1.1
     */
    public float[] getFloatArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagFloatArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for FloatArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a double array tag from the compound
     * @param key The key of the tag
     * @return The double array value
     * @exception NoSuchKeyException When the key is not contained by the double array
     * @exception WrongTagException  When the tag is not a double array tag
     * @since 1.1
     */
    public double[] getDoubleArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagDoubleArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for DoubleArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a boolean array tag from the compound
     * @param key The key of the tag
     * @return The boolean array value
     * @exception NoSuchKeyException When the key is not contained by the boolean array
     * @exception WrongTagException  When the tag is not a boolean array tag
     * @since 1.1
     */
    public boolean[] getBooleanArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagBooleanArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for BooleanArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the value of a string array tag from the compound
     * @param key The key of the tag
     * @return The string array value
     * @exception NoSuchKeyException When the key is not contained by the string array
     * @exception WrongTagException  When the tag is not a string array tag
     * @since 1.1
     */
    public String[] getStringArray( String key ) throws WrongTagException, NoSuchKeyException {
        checkKey( key );
        try {
            return ( ( TagStringArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for StringArray, but did not found tag for that." );
        }
    }

    private boolean checkKeyDef( String key ) {
        if( key == null ) {
            return false;
        }
        return values.containsKey( key );
    }

    /**
     * Returns a tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key is not found
     * @return The tag
     * @since 1.1
     */
    public ITag opt( String key, ITag def ) {
        if( ! checkKeyDef( key ) ) return def;
        return values.get( key );
    }

    /**
     * Returns an integer tag from the compound
     * @param key The key of the tag
     * @return The integer tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @exception WrongTagException  When the tag is not an integer tag
     * @since 1.1
     */
    public TagInteger optTagInteger( String key, TagInteger def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagInteger ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a long tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a long tag is not found
     * @return The long tag
     * @since 1.1
     */
    public TagLong optTagLong( String key, TagLong def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagLong ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a short tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a short tag is not found
     * @return The short tag
     * @since 1.1
     */
    public TagShort optTagShort( String key, TagShort def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagShort ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a byte tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a byte tag is not found
     * @return The byte tag
     * @since 1.1
     */
    public TagByte optTagByte( String key, TagByte def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagByte ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a double tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a double tag is not found
     * @return The double tag
     * @since 1.1
     */
    public TagDouble optTagDouble( String key, TagDouble def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagDouble ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a float tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a float tag is not found
     * @return The float tag
     * @since 1.1
     */
    public TagFloat optTagFloat( String key, TagFloat def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagFloat ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a boolean tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a boolean tag is not found
     * @return The boolean tag
     * @since 1.1
     */
    public TagBoolean optTagBoolean( String key, TagBoolean def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagBoolean ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a null tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a null tag is not found
     * @return The null tag
     * @since 1.1
     */
    public TagNull optTagNull( String key, TagNull def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagNull ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a string tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a string tag is not found
     * @return The string tag
     * @since 1.1
     */
    public TagString optTagString( String key, TagString def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagString ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns an array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or an array tag is not found
     * @return The array tag
     * @since 1.1
     */
    public TagArray optTagArray( String key, TagArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a compound tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a compound tag is not found
     * @return The compound tag
     * @since 1.1
     */
    public TagCompound optTagCompound( String key, TagCompound def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagCompound ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a byte array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a byte array tag is not found
     * @return The byte array tag
     * @since 1.1
     */
    public TagByteArray optTagByteArray( String key, TagByteArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagByteArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a short array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a short array tag is not found
     * @return The short array tag
     * @since 1.1
     */
    public TagShortArray optTagShortArray( String key, TagShortArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagShortArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns an integer array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a integer array tag is not found
     * @return The integer array tag
     * @since 1.1
     */
    public TagIntegerArray optTagIntegerArray( String key, TagIntegerArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagIntegerArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a long array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a long array tag is not found
     * @return The long array tag
     * @since 1.1
     */
    public TagLongArray optTagLongArray( String key, TagLongArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagLongArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a float array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a float array tag is not found
     * @return The float array tag
     * @since 1.1
     */
    public TagFloatArray optTagFloatArray( String key, TagFloatArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagFloatArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a double array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a double array tag is not found
     * @return The double array tag
     * @since 1.1
     */
    public TagDoubleArray optTagDoubleArray( String key, TagDoubleArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagDoubleArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a boolean array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a boolean array tag is not found
     * @return The boolean array tag
     * @since 1.1
     */
    public TagBooleanArray optTagBooleanArray( String key, TagBooleanArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagBooleanArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a string array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a string array tag is not found
     * @return The string array tag
     * @since 1.1
     */
    public TagStringArray optTagStringArray( String key, TagStringArray def ) {
        if( ! checkKeyDef( key ) ) return def;
        try {
            return ( TagStringArray ) values.get( key );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of an integer tag from the compound
     * @param key The key of the tag
     * ** @param def The default value if the key or a integer tag is not found
     * @return The integer value
     * @since 1.1
     */
    public int optInteger( String key, int def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagInteger ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a long tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a long tag is not found
     * @return The long value
     * @since 1.1
     */
    public long optLong( String key, long def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagLong ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a short tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a short tag is not found
     * @return The short value
     * @since 1.1
     */
    public short optShort( String key, short def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagShort ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a byte tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a byte tag is not found
     * @return The byte value
     * @since 1.1
     */
    public byte optByte( String key, byte def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagByte ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a double tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a double tag is not found
     * @return The double value
     * @since 1.1
     */
    public double optDouble( String key, double def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagDouble ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a float tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a float tag is not found
     * @return The float value
     * @since 1.1
     */
    public float optFloat( String key, float def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagFloat ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a boolean tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a boolean tag is not found
     * @return The boolean value
     * @since 1.1
     */
    public boolean optBoolean( String key, boolean def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagBoolean ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a string tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a string tag is not found
     * @return The string value
     * @since 1.1
     */
    public String optString( String key, String def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagString ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of an array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a array tag is not found
     * @return The array value
     * @since 1.1
     */
    public Array optArray( String key, Array def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a compound tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a compound tag is not found
     * @return The compound value
     * @since 1.1
     */
    public Compound optCompound( String key, Compound def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagCompound ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a byte array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a byte array tag is not found
     * @return The byte array value
     * @since 1.1
     */
    public byte[] optByteArray( String key, byte[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagByteArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a short array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a short array tag is not found
     * @return The short array value
     * @since 1.1
     */
    public short[] optShortArray( String key, short[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagShortArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of an integer array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or an integer array tag is not found
     * @return The integer array value
     * @since 1.1
     */
    public int[] optIntegerArray( String key, int[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagIntegerArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a long array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a long array tag is not found
     * @return The long array value
     * @since 1.1
     */
    public long[] optLongArray( String key, long[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagLongArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a float array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a float array tag is not found
     * @return The float array value
     * @since 1.1
     */
    public float[] optFloatArray( String key, float[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagFloatArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a double array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a double array tag is not found
     * @return The double array value
     * @since 1.1
     */
    public double[] optDoubleArray( String key, double[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagDoubleArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a boolean array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a boolean array tag is not found
     * @return The boolean array value
     * @since 1.1
     */
    public boolean[] optBooleanArray( String key, boolean[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagBooleanArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the value of a string array tag from the compound
     * @param key The key of the tag
     * @param def The default value if the key or a string array tag is not found
     * @return The string array value
     * @since 1.1
     */
    public String[] optStringArray( String key, String[] def ) {
        if ( ! checkKeyDef( key ) ) return def;
        try {
            return ( ( TagStringArray ) values.get( key ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Removes a tag from the compound
     * @param key The key of the tag
     * @exception NoSuchKeyException When the key is not contained by the compound
     * @since 1.0
     */
    public Compound remove( String key ) throws NoSuchKeyException {
        checkKey( key );
        values.remove( key );
        return this;
    }

    /**
     * Removes a tag from the compound if the key exists
     * @param key The key of the tag
     * @return True if the key was found and the item is removed
     * @since 1.1
     */
    public boolean removeOpt( String key ) {
        if( ! checkKeyDef( key ) ) return false;
        values.remove( key );
        return true;
    }

    /**
     * Checks if the compound contains a key
     * @param key The key
     * @return True if contained
     * @since 1.0
     */
    public boolean hasKey( String key ) {
        return values.containsKey( key );
    }

    /**
     * Checks if the compound contains a tag
     * @param value The tag
     * @return True if contained
     * @since 1.0
     */
    public boolean hasValue( ITag value ) {
        return values.containsValue( value );
    }


    /**
     * Returns the {@link KeyValuePair}{@code []} for this compound
     * @since 1.0
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
