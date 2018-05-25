<img src="https://i.imgur.com/u5rbx7o.png" width="300">

# CTag
A binary format, based on Minecraft NBT, and my (deprecated and archived) repository: MBDF (https://github.com/RedGalaxySoftware/MBDF). This repository contains java tools that can encode and decode that binary format.

## How to use
Add the jar as dependency to your project.

#### Encoding
At fisrt you have to make an `ITag` instance. The `ITag` is an interface and could not be instantiated. However, the library provides many implementations of it. Don't make your own one, that's currently not supported (it will encode, but decoding causes exceptions). These are the supported tags:
- `TagByte`: A 8-bit signed integer
- `TagShort`: A 16-bit signed integer
- `TagInteger`: A 32-bit signed integer
- `TagLong`: A 64-bit signed integer
- `TagFloat`: A 32-bit floating point (single precision)
- `TagDouble`: A 64-bit floating point (double precision)
- `TagBoolean`: A boolean
- `TagByteArray`: A 8-bit signed integer array
- `TagShortArray`: A 16-bit signed integer array
- `TagIntegerArray`: A 32-bit signed integer array
- `TagLongArray`: A 64-bit signed integer array
- `TagFloatArray`: A 32-bit floating point array (single precision)
- `TagDoubleArray`: A 64-bit floating point array (double precision)
- `TagBooleanArray`: A boolean array
- `TagString`: An UTF-8 encoded string
- `TagArray`: An any-tag array
- `TagCompound`: A string-tag map
- `TagNull`: A null reference

Let's create an array, with a string `HELLO`, a double `2.5` and a boolean `true`:
```java
Array array = new Array();      // Create an array object, this stores the tags of the TagArray.
array.add( "HELLO" );           // Creates a TagString, with "HELLO" as value and adds it to the array.
array.add( 2.5D );              // Creates a TagDouble, with 2.5 as value and adds it to the array.
array.add( true );              // Creates a TagBoolean, with true as value and adds it to the array.

// Create the actual tag
TagArray tagArray = new TagArray( array );
```
Ok, it is really that simple, but it is not encoded now. To encode it, make a `CTagEncoder`, and encode the tag.
```java
CTagEncoder encoder = new CTagEncoder( tagArray );    // The encoder must encode the array tag
Binary encoded = encoder.encode();                    // This does the complete encode process
System.out.println( encoded.toHexString() );          // Print the hexadecimal data
System.out.println( encoded.toString() );             // Print the binary data
System.out.println( encoded.makeString() );           // Print the base64 data
```
The output:
```
08 00 03 07 00 05 48 45 4c 4c 4f 06 40 04 00 00 00 00 00 00 0b 01
00001000 00000000 00000011 00000111 00000000 00000101 01001000 01000101 01001100 01001100 01001111 00000110 01000000 00000100 00000000 00000000 00000000 00000000 00000000 00000000 00001011 00000001
CAADBwAFSEVMTE8GQAQAAAAAAAALAQ
```
The base64 output is the actual output stored in files. It can be compressed and decompressed, but that's currently not included in the library, and not supported by the decoder, meaning that you have to decompress it with another library before you decode it with the decoder.

#### What do I see in the output
The output is confusing and maybe you're not actually able to read it by yourself without knowing the structure. To understand what the output means, I'll explain it to you.
###### The format
The hexadecimal output, `08 00 03 07 00 05 48 45 4c 4c 4f 06 40 04 00 00 00 00 00 00 0b 01`, is just each byte as a hexadecimal number. Compare it to the binary output:
```
08       00       03       07       00       05       48       45       4c       4c       4f       06       40       04       00       00       00       00       00       00       0b       01
00001000 00000000 00000011 00000111 00000000 00000101 01001000 01000101 01001100 01001100 01001111 00000110 01000000 00000100 00000000 00000000 00000000 00000000 00000000 00000000 00001011 00000001
```
The base64 output is just each 6 bits of the complete bit string.

###### The content
A CTag binary always starts with one byte indicating which tag type is stored in the binary. Then the stored value follows it directly:
```
Prefix   Value -->
00001000 00000000 00000011 00000111 00000000 00000101 01001000 01000101 01001100 01001100 01001111 00000110 01000000 00000100 00000000 00000000 00000000 00000000 00000000 00000000 00001011 00000001
```
In this case we have the prefix `00001000` (`08`), which refers to an `ARRAY` tag. So the prefix is followed by an `ARRAY` binary.

Array binaries start with a 16 bits integer (`short`) holding the length, followed by the entries.
```
Prefix  ARRAY      00001000
Length  = 3        00000000 00000011 
Entries            00000111 00000000 00000101 01001000 01000101 01001100 01001100 01001111 00000110 01000000 00000100 00000000 00000000 00000000 00000000 00000000 00000000 00001011 00000001
```
The entries of an `ARRAY` are just basic CTag codes, starting with a type prefix followed by their value. The length of the array tells the decoder how many bytes to read until the array ends.

The first entry is a string (`00000111` - `07`) and starts with a `short` indicating the length. It is followed by that amount bytes holding the characters.
The second entry is a double (`00000110` - `06`) and just is 8 bytes representing the double respectively.
The third entry is a boolean (`00001011` - `0b`) and just is one byte where the last bit represents the value.

```
Prefix           ARRAY      00001000
Length           = 3        00000000 00000011 
Entries              
  Entry 1
    Prefix       STRING     00000111 
    Length       = 5        00000000 00000101
    Char 1       = H        01001000 
    Char 2       = E        01000101 
    Char 3       = L        01001100 
    Char 4       = L        01001100 
    Char 5       = O        01001111 
  Entry 2
    Prefix       DOUBLE     00000110 
    Value        = 2.5      01000000 00000100 00000000 00000000 00000000 00000000 00000000 00000000 
  Entry 3
    Prefix       BOOLEAN    00001011 
    Value        = true     00000001
```
That's it. Maybe it's still hard to understand, but hopefully it will make the basic things clear.

#### Decoding
Okay, back to the code. Our `Binary` instance, `encoded`, contains our CTag code. To decode it, we need a decoder.
```java
CTagDecoder decoder = new CTagDecoder( encoded );              // Decode the binary
```

To make sure the base64 string is also parseable, you can also make the decoder like this:
```java
CTagDecoder decoder = new CTagDecoder( encoded.makeString() ); // Decode the base64 string
```
Now, to make the decoded `ITag`, just call `decoder.decode()`. This, however, comes with some exceptions:
```java
try {
    ITag tag = decoder.decode();
    System.out.println( tag );
} catch( IOException | CTagInvalidException exception ) {
    // The IOException is thrown by the CTagInput instance ( created by the decoder ) if it's underlying InputStream throws it.
    // The CTagInvalidException is thrown when the CTag code is corrupted or invalid.
    exception.printStackTrace();
}
```
This will print the following if no exceptions are thrown.
```
ARRAY [
    0: STRING "HELLO"
    1: DOUBLE 2.5
    2: BOOLEAN true
]
```
This are the values we created, so it works!


Copyright &copy; 2018 RedGalaxy
