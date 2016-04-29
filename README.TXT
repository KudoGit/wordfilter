A word filter
By James Kuch

Coded on BlueJ (Java IDE for Raspberry Pi)

Files:

curses.txt:
-List of curse words to be filtered out. Each word must be separated by a newline.
 Currently contains known and suggested filter words, edit this freely if you want to
 filter something else.

exceptions.txt
-The opposite of the above, list of words for the filter to ignore. Safe words that
 contain curse words should be listed here.

README.txt
-You're reading it!

Filter.class
-The main package, currently only has one function, String convert(String);
Takes a message and filters out curse words while leaving exception words alone.
Only returns up to the first 80 characters of the given message.
If a curse word is found and only some of its letters are found in exception words,
the remaining letters of the curse word are filtered (prevents some filter evasion).

Usage:
1.) Put Filter.class with the rest of your source files
2.) Declare a new WFilter object (i.e. Filter f = new Filter();)
3.) Call function with f.convert("Message to filter here");

FilterTest.java
-A small program to test the above.

To-Compile: javac FilterTest.java
Usage: java FilterTest
-Enter a message to be filtered.
