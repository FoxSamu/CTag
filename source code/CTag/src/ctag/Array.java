package ctag;

import ctag.exception.WrongTagException;
import ctag.tags.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents an any-type array. This is used by {@link TagArray} for storing
 * the values.
 * @since 1.0
 */
public class Array implements Iterable<ITag> {
    private ArrayList<ITag> values = new ArrayList<>();

    /**
     * Makes an array with pre-added tags
     * @param values Varargs containing the tags to add
     * @since 1.0
     */
    public Array( ITag... values ) {
        this.values.addAll( Arrays.asList( values ) );
    }

    /**
     * Makes an array with pre-added tags
     * @param values {@link Collection} containing the tags to add
     * @since 1.0
     */
    public Array( Collection<? extends ITag> values ) {
        this.values.addAll( values );
    }

    /**
     * Makes an array with pre-added integer tags
     * @param values Varargs containing the integer values of the tags
     * @since 1.0
     */
    public Array( int... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added short tags
     * @param values Varargs containing the short values of the tags
     * @since 1.0
     */
    public Array( short... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added long tags
     * @param values Varargs containing the long values of the tags
     * @since 1.0
     */
    public Array( long... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added byte tags
     * @param values Varargs containing the byte values of the tags
     * @since 1.0
     */
    public Array( byte... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added double tags
     * @param values Varargs containing the double values of the tags
     * @since 1.0
     */
    public Array( double... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added float tags
     * @param values Varargs containing the float values of the tags
     * @since 1.0
     */
    public Array( float... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added boolean tags
     * @param values Varargs containing the boolean values of the tags
     * @since 1.0
     */
    public Array( boolean... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added string tags
     * @param values Varargs containing the string values of the tags
     * @since 1.0
     */
    public Array( String... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added array tags
     * @param values Varargs containing the array values of the tags
     * @since 1.0
     */
    public Array( Array... values ) {
        addAll( values );
    }

    /**
     * Makes an array with pre-added compound tags
     * @param values Varargs containing the compound values of the tags
     * @since 1.0
     */
    public Array( Compound... values ) {
        addAll( values );
    }

    /**
     * Makes an empty array
     * @since 1.0
     */
    public Array() {
    }

    private void checkAddIndex( int index ) {
        if( index < 0 || index > size() ) {
            throw new IndexOutOfBoundsException(
                    "Insert index out of bounds. Must be between 0 and " +
                            size() + " (inclusive), found: " + index + "."
            );
        }
    }

    private void checkIndex( int index ) {
        if( index < 0 || index > size() - 1 ) {
            throw new IndexOutOfBoundsException(
                    "Index out of bounds. Must be between 0 and " +
                            ( size() - 1 ) + " (inclusive), found: " + index + "."
            );
        }
    }

    /**
     * Adds a tag to the array
     * @param value The tag
     * @since 1.0
     */
    public Array add( ITag value ) {
        values.add( value );
        return this;
    }

    /**
     * Adds a long tag to the array
     * @param value The value for the long tag
     * @since 1.0
     */
    public Array add( long value ) {
        values.add( new TagLong( value ) );
        return this;
    }

    /**
     * Adds an integer tag to the array
     * @param value The value for the integer tag
     * @since 1.0
     */
    public Array add( int value ) {
        values.add( new TagInteger( value ) );
        return this;
    }

    /**
     * Adds a short tag to the array
     * @param value The value for the short tag
     * @since 1.0
     */
    public Array add( short value ) {
        values.add( new TagShort( value ) );
        return this;
    }

    /**
     * Adds a byte tag to the array
     * @param value The value for the byte tag
     * @since 1.0
     */
    public Array add( byte value ) {
        values.add( new TagByte( value ) );
        return this;
    }

    /**
     * Adds a double tag to the array
     * @param value The value for the double tag
     * @since 1.0
     */
    public Array add( double value ) {
        values.add( new TagDouble( value ) );
        return this;
    }

    /**
     * Adds a float tag to the array
     * @param value The value for the float tag
     * @since 1.0
     */
    public Array add( float value ) {
        values.add( new TagFloat( value ) );
        return this;
    }

    /**
     * Adds a boolean tag to the array
     * @param value The value for the boolean tag
     * @since 1.0
     */
    public Array add( boolean value ) {
        values.add( new TagBoolean( value ) );
        return this;
    }

    /**
     * Adds a string tag to the array
     * @param value The value for the string tag
     * @since 1.0
     */
    public Array add( String value ) {
        values.add( new TagString( value ) );
        return this;
    }

    /**
     * Adds an array tag to the array
     * @param value The value for the array tag
     * @since 1.0
     */
    public Array add( Array value ) {
        values.add( new TagArray( value ) );
        return this;
    }

    /**
     * Adds a compound tag to the array
     * @param value The value for the compound tag
     * @since 1.0
     */
    public Array add( Compound value ) {
        values.add( new TagCompound( value ) );
        return this;
    }

    /**
     * Adds a byte array tag to the array
     * @param value The value for the byte array tag
     * @since 1.1
     */
    public Array add( byte... value ) {
        values.add( new TagByteArray( value ) );
        return this;
    }

    /**
     * Adds a short array tag to the array
     * @param value The value for the short array tag
     * @since 1.1
     */
    public Array add( short... value ) {
        values.add( new TagShortArray( value ) );
        return this;
    }

    /**
     * Adds an integer array tag to the array
     * @param value The value for the integer array tag
     * @since 1.1
     */
    public Array add( int... value ) {
        values.add( new TagIntegerArray( value ) );
        return this;
    }

    /**
     * Adds a long array tag to the array
     * @param value The value for the long array tag
     * @since 1.1
     */
    public Array add( long... value ) {
        values.add( new TagLongArray( value ) );
        return this;
    }

    /**
     * Adds a float array tag to the array
     * @param value The value for the float array tag
     * @since 1.1
     */
    public Array add( float... value ) {
        values.add( new TagFloatArray( value ) );
        return this;
    }

    /**
     * Adds a double array tag to the array
     * @param value The value for the double array tag
     * @since 1.1
     */
    public Array add( double... value ) {
        values.add( new TagDoubleArray( value ) );
        return this;
    }

    /**
     * Adds a boolean array tag to the array
     * @param value The value for the boolean array tag
     * @since 1.1
     */
    public Array add( boolean... value ) {
        values.add( new TagBooleanArray( value ) );
        return this;
    }

    /**
     * Adds a string array tag to the array
     * @param value The value for the string array tag
     * @since 1.1
     */
    public Array add( String... value ) {
        values.add( new TagStringArray( value ) );
        return this;
    }

    /**
     * Set a value of the array to a specific tag
     * @param index The index of the value
     * @param value The new tag for the value
     * @since 1.0
     */
    public Array set( int index, ITag value ) {
        checkIndex( index );
        values.set( index, value );
        return this;
    }

    /**
     * Sets a value of the array to a long tag
     * @param index The index of the value
     * @param value The long value for the new tag
     * @since 1.0
     */
    public Array set( int index, long value ) {
        checkIndex( index );
        values.set( index, new TagLong( value ) );
        return this;
    }

    /**
     * Sets a value of the array to an integer tag
     * @param index The index of the value
     * @param value The integer value for the new tag
     * @since 1.0
     */
    public Array set( int index, int value ) {
        checkIndex( index );
        values.set( index, new TagInteger( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a short tag
     * @param index The index of the value
     * @param value The short value for the new tag
     * @since 1.0
     */
    public Array set( int index, short value ) {
        checkIndex( index );
        values.set( index, new TagShort( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a byte tag
     * @param index The index of the value
     * @param value The byte value for the new tag
     * @since 1.0
     */
    public Array set( int index, byte value ) {
        checkIndex( index );
        values.set( index, new TagByte( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a double tag
     * @param index The index of the value
     * @param value The double value for the new tag
     * @since 1.0
     */
    public Array set( int index, double value ) {
        checkIndex( index );
        values.set( index, new TagDouble( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a float tag
     * @param index The index of the value
     * @param value The float value for the new tag
     * @since 1.0
     */
    public Array set( int index, float value ) {
        checkIndex( index );
        values.set( index, new TagFloat( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a boolean tag
     * @param index The index of the value
     * @param value The boolean value for the new tag
     * @since 1.0
     */
    public Array set( int index, boolean value ) {
        checkIndex( index );
        values.set( index, new TagBoolean( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a string tag
     * @param index The index of the value
     * @param value The string value for the new tag
     * @since 1.0
     */
    public Array set( int index, String value ) {
        checkIndex( index );
        values.set( index, new TagString( value ) );
        return this;
    }

    /**
     * Sets a value of the array to an array tag
     * @param index The index of the value
     * @param value The array value for the new tag
     * @since 1.0
     */
    public Array set( int index, Array value ) {
        checkIndex( index );
        values.set( index, new TagArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a compound tag
     * @param index The index of the value
     * @param value The compound value for the new tag
     * @since 1.0
     */
    public Array set( int index, Compound value ) {
        checkIndex( index );
        values.set( index, new TagCompound( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a byte array tag
     * @param index The index of the value
     * @param value The byte values for the new tag
     * @since 1.1
     */
    public Array set( int index, byte... value ) {
        checkIndex( index );
        values.set( index, new TagByteArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a short array tag
     * @param index The index of the value
     * @param value The short values for the new tag
     * @since 1.1
     */
    public Array set( int index, short... value ) {
        checkIndex( index );
        values.set( index, new TagShortArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a integer array tag
     * @param index The index of the value
     * @param value The integer values for the new tag
     * @since 1.1
     */
    public Array set( int index, int... value ) {
        checkIndex( index );
        values.set( index, new TagIntegerArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a long array tag
     * @param index The index of the value
     * @param value The long values for the new tag
     * @since 1.1
     */
    public Array set( int index, long... value ) {
        checkIndex( index );
        values.set( index, new TagLongArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a float array tag
     * @param index The index of the value
     * @param value The float values for the new tag
     * @since 1.1
     */
    public Array set( int index, float... value ) {
        checkIndex( index );
        values.set( index, new TagFloatArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a double array tag
     * @param index The index of the value
     * @param value The double values for the new tag
     * @since 1.1
     */
    public Array set( int index, double... value ) {
        checkIndex( index );
        values.set( index, new TagDoubleArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a boolean array tag
     * @param index The index of the value
     * @param value The boolean values for the new tag
     * @since 1.1
     */
    public Array set( int index, boolean... value ) {
        checkIndex( index );
        values.set( index, new TagBooleanArray( value ) );
        return this;
    }

    /**
     * Sets a value of the array to a a string array tag
     * @param index The index of the value
     * @param value The string values for the new tag
     * @since 1.1
     */
    public Array set( int index, String... value ) {
        checkIndex( index );
        values.set( index, new TagStringArray( value ) );
        return this;
    }

    /**
     * Inserts a specific tag into the array
     * @param index The index of the insertion place
     * @param value The tag to insert
     * @since 1.0
     */
    public Array insert( int index, ITag value ) {
        checkAddIndex( index );
        values.add( index, value );
        return this;
    }

    /**
     * Inserts a long tag into the array
     * @param index The index of the insertion place
     * @param value The long value for the new tag
     * @since 1.0
     */
    public Array insert( int index, long value ) {
        checkAddIndex( index );
        values.add( index, new TagLong( value ) );
        return this;
    }

    /**
     * Inserts an integer tag into the array
     * @param index The index of the insertion place
     * @param value The integer value for the new tag
     * @since 1.0
     */
    public Array insert( int index, int value ) {
        checkAddIndex( index );
        values.add( index, new TagInteger( value ) );
        return this;
    }

    /**
     * Inserts a short tag into the array
     * @param index The index of the insertion place
     * @param value The short value for the new tag
     * @since 1.0
     */
    public Array insert( int index, short value ) {
        checkAddIndex( index );
        values.add( index, new TagShort( value ) );
        return this;
    }

    /**
     * Inserts a byte tag into the array
     * @param index The index of the insertion place
     * @param value The byte value for the new tag
     * @since 1.0
     */
    public Array insert( int index, byte value ) {
        checkAddIndex( index );
        values.add( index, new TagByte( value ) );
        return this;
    }

    /**
     * Inserts a double tag into the array
     * @param index The index of the insertion place
     * @param value The double value for the new tag
     * @since 1.0
     */
    public Array insert( int index, double value ) {
        checkAddIndex( index );
        values.add( index, new TagDouble( value ) );
        return this;
    }

    /**
     * Inserts a float tag into the array
     * @param index The index of the insertion place
     * @param value The float value for the new tag
     * @since 1.0
     */
    public Array insert( int index, float value ) {
        checkAddIndex( index );
        values.add( index, new TagFloat( value ) );
        return this;
    }

    /**
     * Inserts a boolean tag into the array
     * @param index The index of the insertion place
     * @param value The boolean value for the new tag
     * @since 1.0
     */
    public Array insert( int index, boolean value ) {
        checkAddIndex( index );
        values.add( index, new TagBoolean( value ) );
        return this;
    }

    /**
     * Inserts a string tag into the array
     * @param index The index of the insertion place
     * @param value The string value for the new tag
     * @since 1.0
     */
    public Array insert( int index, String value ) {
        checkAddIndex( index );
        values.add( index, new TagString( value ) );
        return this;
    }

    /**
     * Inserts an array tag into the array
     * @param index The index of the insertion place
     * @param value The array value for the new tag
     * @since 1.0
     */
    public Array insert( int index, Array value ) {
        checkAddIndex( index );
        values.add( index, new TagArray( value ) );
        return this;
    }

    /**
     * Inserts a compound tag into the array
     * @param index The index of the insertion place
     * @param value The compound value for the new tag
     * @since 1.0
     */
    public Array insert( int index, Compound value ) {
        checkAddIndex( index );
        values.add( index, new TagCompound( value ) );
        return this;
    }

    /**
     * Inserts a byte array tag into the array
     * @param index The index of the insertion place
     * @param value The byte values for the new tag
     * @since 1.1
     */
    public Array insert( int index, byte... value ) {
        checkAddIndex( index );
        values.add( index, new TagByteArray( value ) );
        return this;
    }

    /**
     * Inserts a short array tag into the array
     * @param index The index of the insertion place
     * @param value The short values for the new tag
     * @since 1.1
     */
    public Array insert( int index, short... value ) {
        checkAddIndex( index );
        values.add( index, new TagShortArray( value ) );
        return this;
    }

    /**
     * Inserts a integer array tag into the array
     * @param index The index of the insertion place
     * @param value The integer values for the new tag
     * @since 1.1
     */
    public Array insert( int index, int... value ) {
        checkAddIndex( index );
        values.add( index, new TagIntegerArray( value ) );
        return this;
    }

    /**
     * Inserts a long array tag into the array
     * @param index The index of the insertion place
     * @param value The long values for the new tag
     * @since 1.1
     */
    public Array insert( int index, long... value ) {
        checkAddIndex( index );
        values.add( index, new TagLongArray( value ) );
        return this;
    }

    /**
     * Inserts a float array tag into the array
     * @param index The index of the insertion place
     * @param value The float values for the new tag
     * @since 1.1
     */
    public Array insert( int index, float... value ) {
        checkAddIndex( index );
        values.add( index, new TagFloatArray( value ) );
        return this;
    }

    /**
     * Inserts a double array tag into the array
     * @param index The index of the insertion place
     * @param value The double values for the new tag
     * @since 1.1
     */
    public Array insert( int index, double... value ) {
        checkAddIndex( index );
        values.add( index, new TagDoubleArray( value ) );
        return this;
    }

    /**
     * Inserts a boolean array tag into the array
     * @param index The index of the insertion place
     * @param value The boolean values for the new tag
     * @since 1.1
     */
    public Array insert( int index, boolean... value ) {
        checkAddIndex( index );
        values.add( index, new TagBooleanArray( value ) );
        return this;
    }

    /**
     * Inserts a String array tag into the array
     * @param index The index of the insertion place
     * @param value The String values for the new tag
     * @since 1.1
     */
    public Array insert( int index, String... value ) {
        checkAddIndex( index );
        values.add( index, new TagStringArray( value ) );
        return this;
    }

    /**
     * Adds a list of specific tags to the array
     * @param value Varargs containing the tags to add
     * @since 1.0
     */
    public Array addAll( ITag... value ) {
        return addAll( Arrays.asList( value ) );
    }

    /**
     * Adds a list of specific tags to the array
     * @param value {@link Collection} containing the tags to add
     * @since 1.0
     */
    public Array addAll( Collection<? extends ITag> value ) {
        values.addAll( value );
        return this;
    }

    /**
     * Adds a list of integer tags to the array
     * @param value Varargs containing the integer values for the tags
     * @since 1.0
     */
    public Array addAll( int... value ) {
        for( int val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of long tags to the array
     * @param value Varargs containing the long values for the tags
     * @since 1.0
     */
    public Array addAll( long... value ) {
        for( long val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of short tags to the array
     * @param value Varargs containing the short values for the tags
     * @since 1.0
     */
    public Array addAll( short... value ) {
        for( short val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of byte tags to the array
     * @param value Varargs containing the byte values for the tags
     * @since 1.0
     */
    public Array addAll( byte... value ) {
        for( byte val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of double tags to the array
     * @param value Varargs containing the double values for the tags
     * @since 1.0
     */
    public Array addAll( double... value ) {
        for( double val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of float tags to the array
     * @param value Varargs containing the float values for the tags
     * @since 1.0
     */
    public Array addAll( float... value ) {
        for( float val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of boolean tags to the array
     * @param value Varargs containing the boolean values for the tags
     * @since 1.0
     */
    public Array addAll( boolean... value ) {
        for( boolean val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of string tags to the array
     * @param value Varargs containing the string values for the tags
     * @since 1.0
     */
    public Array addAll( String... value ) {
        for( String val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of array tags to the array
     * @param value Varargs containing the array values for the tags
     * @since 1.0
     */
    public Array addAll( Array... value ) {
        for( Array val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of compound tags to the array
     * @param value Varargs containing the compound values for the tags
     * @since 1.0
     */
    public Array addAll( Compound... value ) {
        for( Compound val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of byte array tags to the array
     * @param value Varargs containing the byte array values for the tags
     * @since 1.1
     */
    public Array addAll( byte[]... value ) {
        for( byte[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of short array tags to the array
     * @param value Varargs containing the short array values for the tags
     * @since 1.1
     */
    public Array addAll( short[]... value ) {
        for( short[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of integer array tags to the array
     * @param value Varargs containing the integer array values for the tags
     * @since 1.1
     */
    public Array addAll( int[]... value ) {
        for( int[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of long array tags to the array
     * @param value Varargs containing the long array values for the tags
     * @since 1.1
     */
    public Array addAll( long[]... value ) {
        for( long[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of float array tags to the array
     * @param value Varargs containing the float array values for the tags
     * @since 1.1
     */
    public Array addAll( float[]... value ) {
        for( float[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of double array tags to the array
     * @param value Varargs containing the double array values for the tags
     * @since 1.1
     */
    public Array addAll( double[]... value ) {
        for( double[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of boolean array tags to the array
     * @param value Varargs containing the boolean array values for the tags
     * @since 1.1
     */
    public Array addAll( boolean[]... value ) {
        for( boolean[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Adds a list of string array tags to the array
     * @param value Varargs containing the string array values for the tags
     * @since 1.1
     */
    public Array addAll( String[]... value ) {
        for( String[] val : value ) {
            add( val );
        }
        return this;
    }

    /**
     * Inserts a list of specific tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the tags
     * @since 1.0
     */
    public Array insertAll( int index, ITag... value ) {
        checkAddIndex( index );
        return insertAll( index, Arrays.asList( value ) );
    }

    /**
     * Inserts a list of specific tags into the array
     * @param index The index of the insertion place
     * @param value {@link Collection} containing the tags
     * @since 1.0
     */
    public Array insertAll( int index, Collection<? extends ITag> value ) {
        checkAddIndex( index );
        values.addAll( index, value );
        return this;
    }

    /**
     * Inserts a list of integer tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the integer values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, int... value ) {
        checkAddIndex( index );
        for( int val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of long tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the long values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, long... value ) {
        checkAddIndex( index );
        for( long val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of short tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the short values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, short... value ) {
        checkAddIndex( index );
        for( short val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of byte tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the byte values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, byte... value ) {
        checkAddIndex( index );
        for( byte val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of double tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the double values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, double... value ) {
        checkAddIndex( index );
        for( double val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of float tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the float values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, float... value ) {
        checkAddIndex( index );
        for( float val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of boolean tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the boolean values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, boolean... value ) {
        checkAddIndex( index );
        for( boolean val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of string tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the string values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, String... value ) {
        checkAddIndex( index );
        for( String val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the array values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, Array... value ) {
        checkAddIndex( index );
        for( Array val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of compound tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the compound values for the tags
     * @since 1.0
     */
    public Array insertAll( int index, Compound... value ) {
        checkAddIndex( index );
        for( Compound val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of byte array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the byte array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, byte[]... value ) {
        checkAddIndex( index );
        for( byte[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of short array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the short array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, short[]... value ) {
        checkAddIndex( index );
        for( short[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of integer array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the integer array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, int[]... value ) {
        checkAddIndex( index );
        for( int[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of long array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the long array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, long[]... value ) {
        checkAddIndex( index );
        for( long[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of float array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the float array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, float[]... value ) {
        checkAddIndex( index );
        for( float[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of double array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the double array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, double[]... value ) {
        checkAddIndex( index );
        for( double[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of boolean array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the boolean array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, boolean[]... value ) {
        checkAddIndex( index );
        for( boolean[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Inserts a list of string array tags into the array
     * @param index The index of the insertion place
     * @param value Varargs containing the string array values for the tags
     * @since 1.1
     */
    public Array insertAll( int index, String[]... value ) {
        checkAddIndex( index );
        for( String[] val : value ) {
            insert( index, val );
            index++;
        }
        return this;
    }

    /**
     * Returns a tag of the array
     * @param index The index of the tag
     * @return The tag of the array at index
     * @since 1.0
     */
    public ITag get( int index ) {
        checkIndex( index );
        return values.get( index );
    }

    /**
     * Returns an integer tag of the array
     * @param index The index of the tag
     * @return The integer tag of the array at index
     * @exception WrongTagException If the tag at the index is not an integer tag
     * @since 1.0
     */
    public TagInteger getTagInteger( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagInteger ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagInteger, but did not found that." );
        }
    }

    /**
     * Returns a long tag of the array
     * @param index The index of the tag
     * @return The long tag of the array at index
     * @exception WrongTagException If the tag at the index is not a long tag
     * @since 1.0
     */
    public TagLong getTagLong( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagLong ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagLong, but did not found that." );
        }
    }

    /**
     * Returns a short tag of the array
     * @param index The index of the tag
     * @return The short tag of the array at index
     * @exception WrongTagException If the tag at the index is not a short tag
     * @since 1.0
     */
    public TagShort getTagShort( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagShort ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagShort, but did not found that." );
        }
    }

    /**
     * Returns a byte tag of the array
     * @param index The index of the tag
     * @return The byte tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte tag
     * @since 1.0
     */
    public TagByte getTagByte( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagByte ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagByte, but did not found that." );
        }
    }

    /**
     * Returns a double tag of the array
     * @param index The index of the tag
     * @return The double tag of the array at index
     * @exception WrongTagException If the tag at the index is not a double tag
     * @since 1.0
     */
    public TagDouble getTagDouble( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagDouble ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagDouble, but did not found that." );
        }
    }

    /**
     * Returns a float tag of the array
     * @param index The index of the tag
     * @return The float tag of the array at index
     * @exception WrongTagException If the tag at the index is not a float tag
     * @since 1.0
     */
    public TagFloat getTagFloat( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagFloat ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagFloat, but did not found that." );
        }
    }

    /**
     * Returns a boolean tag of the array
     * @param index The index of the tag
     * @return The boolean tag of the array at index
     * @exception WrongTagException If the tag at the index is not a boolean tag
     * @since 1.0
     */
    public TagBoolean getTagBoolean( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagBoolean ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagBoolean, but did not found that." );
        }
    }

    /**
     * Returns a null tag of the array
     * @param index The index of the tag
     * @return The null tag of the array at index
     * @exception WrongTagException If the tag at the index is not a null tag
     * @since 1.0
     */
    public TagNull getTagNull( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagNull ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagNull, but did not found that." );
        }
    }

    /**
     * Returns a string tag of the array
     * @param index The index of the tag
     * @return The string tag of the array at index
     * @exception WrongTagException If the tag at the index is not a string tag
     * @since 1.0
     */
    public TagString getTagString( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagString ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagString, but did not found that." );
        }
    }

    /**
     * Returns an array tag of the array
     * @param index The index of the tag
     * @return The array tag of the array at index
     * @exception WrongTagException If the tag at the index is not an array tag
     * @since 1.0
     */
    public TagArray getTagArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagArray, but did not found that." );
        }
    }

    /**
     * Returns a compound tag of the array
     * @param index The index of the tag
     * @return The compound tag of the array at index
     * @exception WrongTagException If the tag at the index is not a compound tag
     * @since 1.0
     */
    public TagCompound getTagCompound( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagCompound ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagCompound, but did not found that." );
        }
    }

    /**
     * Returns a byte array tag of the array
     * @param index The index of the tag
     * @return The byte array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagByteArray getTagByteArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagByteArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagByteArray, but did not found that." );
        }
    }

    /**
     * Returns a short array tag of the array
     * @param index The index of the tag
     * @return The short array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagShortArray getTagShortArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagShortArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagShortArray, but did not found that." );
        }
    }

    /**
     * Returns an integer array tag of the array
     * @param index The index of the tag
     * @return The integer array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagIntegerArray getTagIntegerArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagIntegerArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagIntegerArray, but did not found that." );
        }
    }

    /**
     * Returns a long array tag of the array
     * @param index The index of the tag
     * @return The long array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagLongArray getTagLongArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagLongArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagLongArray, but did not found that." );
        }
    }

    /**
     * Returns a float array tag of the array
     * @param index The index of the tag
     * @return The float array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagFloatArray getTagFloatArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagFloatArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagFloatArray, but did not found that." );
        }
    }

    /**
     * Returns a double array tag of the array
     * @param index The index of the tag
     * @return The double array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagDoubleArray getTagDoubleArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagDoubleArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagDoubleArray, but did not found that." );
        }
    }

    /**
     * Returns a boolean array tag of the array
     * @param index The index of the tag
     * @return The double array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagBooleanArray getTagBooleanArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagBooleanArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagBooleanArray, but did not found that." );
        }
    }

    /**
     * Returns a string array tag of the array
     * @param index The index of the tag
     * @return The string array tag of the array at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public TagStringArray getTagStringArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( TagStringArray ) values.get( index );
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for TagStringArray, but did not found that." );
        }
    }

    /**
     * Returns the integer value of a tag in the array
     * @param index The index of the tag
     * @return The integer value of the tag at index
     * @exception WrongTagException If the tag at the index is not an integer tag
     * @since 1.0
     */
    public int getInteger( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagInteger ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Integer, but did not found tag for that." );
        }
    }

    /**
     * Returns the long value of a tag in the array
     * @param index The index of the tag
     * @return The long value of the tag at index
     * @exception WrongTagException If the tag at the index is not a long tag
     * @since 1.0
     */
    public long getLong( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagLong ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Long, but did not found tag for that." );
        }
    }

    /**
     * Returns the short value of a tag in the array
     * @param index The index of the tag
     * @return The short value of the tag at index
     * @exception WrongTagException If the tag at the index is not a short tag
     * @since 1.0
     */
    public short getShort( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagShort ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Short, but did not found tag for that." );
        }
    }

    /**
     * Returns the byte value of a tag in the array
     * @param index The index of the tag
     * @return The byte value of the tag at index
     * @exception WrongTagException If the tag at the index is not a byte tag
     * @since 1.0
     */
    public byte getByte( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagByte ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Byte, but did not found tag for that." );
        }
    }

    /**
     * Returns the double value of a tag in the array
     * @param index The index of the tag
     * @return The double value of the tag at index
     * @exception WrongTagException If the tag at the index is not a double tag
     * @since 1.0
     */
    public double getDouble( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagDouble ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Double, but did not found tag for that." );
        }
    }

    /**
     * Returns the float value of a tag in the array
     * @param index The index of the tag
     * @return The float value of the tag at index
     * @exception WrongTagException If the tag at the index is not a float tag
     * @since 1.0
     */
    public float getFloat( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagFloat ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Float, but did not found tag for that." );
        }
    }

    /**
     * Returns the boolean value of a tag in the array
     * @param index The index of the tag
     * @return The boolean value of the tag at index
     * @exception WrongTagException If the tag at the index is not a boolean tag
     * @since 1.0
     */
    public boolean getBoolean( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagBoolean ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Boolean, but did not found tag for that." );
        }
    }

    /**
     * Returns the string value of a tag in the array
     * @param index The index of the tag
     * @return The string value of the tag at index
     * @exception WrongTagException If the tag at the index is not a string tag
     * @since 1.0
     */
    public String getString( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagString ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for String, but did not found tag for that." );
        }
    }

    /**
     * Returns the array value of a tag in the array
     * @param index The index of the tag
     * @return The array value of the tag at index
     * @exception WrongTagException If the tag at the index is not an array tag
     * @since 1.0
     */
    public Array getArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Array, but did not found tag for that." );
        }
    }

    /**
     * Returns the compound value of a tag in the array
     * @param index The index of the tag
     * @return The compound value of the tag at index
     * @exception WrongTagException If the tag at the index is not a compound tag
     * @since 1.0
     */
    public Compound getCompound( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagCompound ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for Compound, but did not found tag for that." );
        }
    }

    /**
     * Returns the byte array value of a tag in the array
     * @param index The index of the tag
     * @return The byte array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a byte array tag
     * @since 1.1
     */
    public byte[] getByteArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagByteArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for ByteArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the short array value of a tag in the array
     * @param index The index of the tag
     * @return The short array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a short array tag
     * @since 1.1
     */
    public short[] getShortArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagShortArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for ShortArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the integer array value of a tag in the array
     * @param index The index of the tag
     * @return The integer array value of the tag at index
     * @exception WrongTagException If the tag at the index is not an integer array tag
     * @since 1.1
     */
    public int[] getIntegerArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagIntegerArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for IntegerArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the long array value of a tag in the array
     * @param index The index of the tag
     * @return The long array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a long array tag
     * @since 1.1
     */
    public long[] getLongArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagLongArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for LongArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the float array value of a tag in the array
     * @param index The index of the tag
     * @return The float array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a float array tag
     * @since 1.1
     */
    public float[] getFloatArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagFloatArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for FloatArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the double array value of a tag in the array
     * @param index The index of the tag
     * @return The double array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a double array tag
     * @since 1.1
     */
    public double[] getDoubleArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagDoubleArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for DoubleArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the boolean array value of a tag in the array
     * @param index The index of the tag
     * @return The boolean array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a boolean array tag
     * @since 1.1
     */
    public boolean[] getBooleanArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagBooleanArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for BooleanArray, but did not found tag for that." );
        }
    }

    /**
     * Returns the string array value of a tag in the array
     * @param index The index of the tag
     * @return The string array value of the tag at index
     * @exception WrongTagException If the tag at the index is not a string array tag
     * @since 1.1
     */
    public String[] getStringArray( int index ) throws WrongTagException {
        checkIndex( index );
        try {
            return ( ( TagStringArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            throw new WrongTagException( "Asked for StringArray, but did not found tag for that." );
        }
    }

    /**
     * Returns an integer tag of the array
     * @param index The index of the tag
     * @param def A default value if no integer found
     * @return The integer tag of the array at index
     * @since 1.1
     */
    public TagInteger optTagInteger( int index, TagInteger def ) {
        checkIndex( index );
        try {
            return ( TagInteger ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a long tag of the array
     * @param index The index of the tag
     * @param def A default value if no long found
     * @return The long tag of the array at index
     * @since 1.1
     */
    public TagLong optTagLong( int index, TagLong def ) {
        checkIndex( index );
        try {
            return ( TagLong ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a short tag of the array
     * @param index The index of the tag
     * @param def A default value if no short found
     * @return The short tag of the array at index
     * @since 1.1
     */
    public TagShort optTagShort( int index, TagShort def ) {
        checkIndex( index );
        try {
            return ( TagShort ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a byte tag of the array
     * @param index The index of the tag
     * @param def A default value if no byte found
     * @return The byte tag of the array at index
     * @since 1.1
     */
    public TagByte optTagByte( int index, TagByte def ) {
        checkIndex( index );
        try {
            return ( TagByte ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a double tag of the array
     * @param index The index of the tag
     * @param def A default value if no double found
     * @return The double tag of the array at index
     * @since 1.1
     */
    public TagDouble optTagDouble( int index, TagDouble def ) {
        checkIndex( index );
        try {
            return ( TagDouble ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a float tag of the array
     * @param index The index of the tag
     * @param def A default value if no float found
     * @return The float tag of the array at index
     * @since 1.1
     */
    public TagFloat optTagFloat( int index, TagFloat def ) {
        checkIndex( index );
        try {
            return ( TagFloat ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a boolean tag of the array
     * @param index The index of the tag
     * @param def A default value if no boolean found
     * @return The boolean tag of the array at index
     * @since 1.1
     */
    public TagBoolean optTagBoolean( int index, TagBoolean def ) {
        checkIndex( index );
        try {
            return ( TagBoolean ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a null tag of the array
     * @param index The index of the tag
     * @param def A default value if no null found
     * @return The null tag of the array at index
     * @since 1.1
     */
    public TagNull optTagNull( int index, TagNull def ) {
        checkIndex( index );
        try {
            return ( TagNull ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a string tag of the array
     * @param index The index of the tag
     * @param def A default value if no string found
     * @return The string tag of the array at index
     * @since 1.1
     */
    public TagString optTagString( int index, TagString def ) {
        checkIndex( index );
        try {
            return ( TagString ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns an array tag of the array
     * @param index The index of the tag
     * @param def A default value if no array found
     * @return The array tag of the array at index
     * @since 1.1
     */
    public TagArray optTagArray( int index, TagArray def ) {
        checkIndex( index );
        try {
            return ( TagArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a compound tag of the array
     * @param index The index of the tag
     * @param def A default value if no compound found
     * @return The compound tag of the array at index
     * @since 1.1
     */
    public TagCompound optTagCompound( int index, TagCompound def ) {
        checkIndex( index );
        try {
            return ( TagCompound ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a byte array tag of the array
     * @param index The index of the tag
     * @param def A default value if no byte array found
     * @return The byte array tag of the array at index
     * @since 1.1
     */
    public TagByteArray optTagByteArray( int index, TagByteArray def ) {
        checkIndex( index );
        try {
            return ( TagByteArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a short array tag of the array
     * @param index The index of the tag
     * @param def A default value if no short array found
     * @return The short array tag of the array at index
     * @since 1.1
     */
    public TagShortArray optTagShortArray( int index, TagShortArray def ) {
        checkIndex( index );
        try {
            return ( TagShortArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns an integer array tag of the array
     * @param index The index of the tag
     * @param def A default value if no integer array found
     * @return The integer array tag of the array at index
     * @since 1.1
     */
    public TagIntegerArray optTagIntegerArray( int index, TagIntegerArray def ) {
        checkIndex( index );
        try {
            return ( TagIntegerArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a long array tag of the array
     * @param index The index of the tag
     * @param def A default value if no long array found
     * @return The long array tag of the array at index
     * @since 1.1
     */
    public TagLongArray optTagLongArray( int index, TagLongArray def ) {
        checkIndex( index );
        try {
            return ( TagLongArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a float array tag of the array
     * @param index The index of the tag
     * @param def A default value if no float array found
     * @return The float array tag of the array at index
     * @since 1.1
     */
    public TagFloatArray optTagFloatArray( int index, TagFloatArray def ) {
        checkIndex( index );
        try {
            return ( TagFloatArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a double array tag of the array
     * @param index The index of the tag
     * @param def A default value if no double array found
     * @return The double array tag of the array at index
     * @since 1.1
     */
    public TagDoubleArray optTagDoubleArray( int index, TagDoubleArray def ) {
        checkIndex( index );
        try {
            return ( TagDoubleArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a boolean array tag of the array
     * @param index The index of the tag
     * @param def A default value if no boolean array found
     * @return The boolean array tag of the array at index
     * @since 1.1
     */
    public TagBooleanArray optTagBooleanArray( int index, TagBooleanArray def ) {
        checkIndex( index );
        try {
            return ( TagBooleanArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns a string array tag of the array
     * @param index The index of the tag
     * @param def A default value if no string array found
     * @return The string array tag of the array at index
     * @since 1.1
     */
    public TagStringArray optTagStringArray( int index, TagStringArray def ) {
        checkIndex( index );
        try {
            return ( TagStringArray ) values.get( index );
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the integer value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no integer found
     * @return The integer value of the tag at index
     * @since 1.1
     */
    public int optInteger( int index, int def ) {
        checkIndex( index );
        try {
            return ( ( TagInteger ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the long value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no long found
     * @return The long value of the tag at index
     * @since 1.1
     */
    public long optLong( int index, long def ) {
        checkIndex( index );
        try {
            return ( ( TagLong ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the short value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no short found
     * @return The short value of the tag at index
     * @since 1.1
     */
    public short optShort( int index, short def ) {
        checkIndex( index );
        try {
            return ( ( TagShort ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the byte value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no byte found
     * @return The byte value of the tag at index
     * @since 1.1
     */
    public byte optByte( int index, byte def ) {
        checkIndex( index );
        try {
            return ( ( TagByte ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the double value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no double found
     * @return The double value of the tag at index
     * @since 1.1
     */
    public double optDouble( int index, double def ) {
        checkIndex( index );
        try {
            return ( ( TagDouble ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the float value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no float found
     * @return The float value of the tag at index
     * @since 1.1
     */
    public float optFloat( int index, float def ) {
        checkIndex( index );
        try {
            return ( ( TagFloat ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the boolean value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no boolean found
     * @return The boolean value of the tag at index
     * @since 1.1
     */
    public boolean optBoolean( int index, boolean def ) {
        checkIndex( index );
        try {
            return ( ( TagBoolean ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the string value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no string found
     * @return The string value of the tag at index
     * @since 1.1
     */
    public String optString( int index, String def ) {
        checkIndex( index );
        try {
            return ( ( TagString ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no array found
     * @return The array value of the tag at index
     * @since 1.1
     */
    public Array optArray( int index, Array def ) {
        checkIndex( index );
        try {
            return ( ( TagArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the compound value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no compound found
     * @return The compound value of the tag at index
     * @since 1.1
     */
    public Compound optCompound( int index, Compound def ) {
        checkIndex( index );
        try {
            return ( ( TagCompound ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the byte array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no byte array found
     * @return The byte array value of the tag at index
     * @since 1.1
     */
    public byte[] optByteArray( int index, byte[] def ) {
        checkIndex( index );
        try {
            return ( ( TagByteArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the short array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no short array found
     * @return The short array value of the tag at index
     * @since 1.1
     */
    public short[] optShortArray( int index, short[] def ) {
        checkIndex( index );
        try {
            return ( ( TagShortArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the integer array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no integer array found
     * @return The integer array value of the tag at index
     * @since 1.1
     */
    public int[] optIntegerArray( int index, int[] def ) {
        checkIndex( index );
        try {
            return ( ( TagIntegerArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the long array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no long array found
     * @return The long array value of the tag at index
     * @since 1.1
     */
    public long[] optLongArray( int index, long[] def ) {
        checkIndex( index );
        try {
            return ( ( TagLongArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the double array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no double array found
     * @return The double array value of the tag at index
     * @since 1.1
     */
    public double[] optDoubleArray( int index, double[] def ) {
        checkIndex( index );
        try {
            return ( ( TagDoubleArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the float array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no float array found
     * @return The float array value of the tag at index
     * @since 1.1
     */
    public float[] optFloatArray( int index, float[] def ) {
        checkIndex( index );
        try {
            return ( ( TagFloatArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the boolean array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no boolean array found
     * @return The boolean array value of the tag at index
     * @since 1.1
     */
    public boolean[] optBooleanArray( int index, boolean[] def ) {
        checkIndex( index );
        try {
            return ( ( TagBooleanArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Returns the string array value of a tag in the array
     * @param index The index of the tag
     * @param def A default value if no string array found
     * @return The string array value of the tag at index
     * @since 1.1
     */
    public String[] optStringArray( int index, String[] def ) {
        checkIndex( index );
        try {
            return ( ( TagStringArray ) values.get( index ) ).getValue();
        } catch( ClassCastException exception ) {
            return def;
        }
    }

    /**
     * Removes a tag from the array
     * @param index The index of the tag
     * @since 1.0
     */
    public Array remove( int index ) {
        checkIndex( index );
        values.remove( index );
        return this;
    }

    /**
     * Removes a tag from the array
     * @param value The tag
     * @return True if the value was contained, and is removed successfully
     * @since 1.0
     */
    public boolean remove( ITag value ) {
        return values.remove( value );
    }

    /**
     * Checks if a tag is contained by the array
     * @param value The tag
     * @return True if the value is contained by the array
     * @since 1.0
     */
    public boolean contains( ITag value ) {
        return values.contains( value );
    }

    /**
     * Removes all values from the array
     * @since 1.0
     */
    public void clear() {
        values.clear();
    }

    /**
     * Checks if the array is empty
     * @return True if empty
     * @since 1.0
     */
    public boolean isEmpty() {
        return values.isEmpty();
    }

    /**
     * Returns the amount of tags in the array
     * @return The size of the array
     * @since 1.0
     */
    public short size() {
        return ( short ) values.size();
    }

    /**
     * Returns the contained tags as a native array ({@link ITag}{@code []}).
     * @since 1.0
     */
    public ITag[] getTags() {
        return values.toArray( new ITag[ size() ] );
    }

    /**
     * @since 1.0
     */
    @Override
    public Iterator<ITag> iterator() {
        return values.iterator();
    }

}
