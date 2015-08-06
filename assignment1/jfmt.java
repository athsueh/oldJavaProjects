// Alan Hsueh, athsueh@ucsc.edu
// $Id: jfmt.java,v 1.9 2012-01-23 02:51:16-08 - - $
//
// Starter code for the jfmt utility.  This program is similar
// to the example code jcat.java, which iterates over all of its
// input files, except that this program shows how to use
// String.split to extract non-whitespace sequences of characters
// from each line.
//

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import static java.lang.System.*;

class jfmt {
   // Static variables keeping the general status of the program.
   public static final String JAR_NAME = get_jarname ();
   public static final int EXIT_SUCCESS = 0;
   public static final int EXIT_FAILURE = 1;
   public static int exit_status = EXIT_SUCCESS;
   
   public static int LINE_LENGTH = 65;

   // A basename is the final component of a pathname.
   // If a java program is run from a jar, the classpath is the
   // pathname of the jar.
   static String get_jarname () {
      String jarpath = getProperty ("java.class.path");
      int lastslash = jarpath.lastIndexOf ('/');
      if (lastslash < 0) return jarpath;
      return jarpath.substring (lastslash + 1);
   }


   // Formats a single file.
   static void format (Scanner infile) {
      // Read each line from the opened file, one after the other.
      // Stop the loop at end of file.

      for (int linenr = 1; infile.hasNextLine (); ++linenr) {
         String line = infile.nextLine ();
         
         //out.printf ("line %3d: [%s]%n", linenr, line);

         // Create a LinkedList of Strings.
         List<String> words = new LinkedList<String> ();

         // Split the line into words around white space and iterate
         // over the words.
         for (String word: line.split ("\\s+")) {

            // Skip an empty word if such is found.
/*
             if (word.length () == 0) continue;
            out.printf ("...[%s]%n", word);
            */
            // Append the word to the end of the linked list.
            words.add (word);

         }
         /*
         out.printf ("list:");

         // Use iterator syntax to print out all of the words.
         for (String word: words) out.printf (" %s", word);
         out.printf ("%n");
         
         */
         
         printparagraph(words);
         
         out.println();
         //out.printf ("%n");
         words.clear();
      }
   }


   private static void printparagraph(List<String> wds) {
// An int is required to count the line length based on words
// A second int is required so there is no redundant space
int wLength = 0;
int checker = 0;

// Each word's length as well as 
// the spaces must be taken into account
// The line count must be reset every new line

for (String word: wds){
wLength = word.length();
//checker++;
if (wLength + checker + 1 > LINE_LENGTH){
checker = wLength + 1;
out.println();
out.print(word);
}
//If a word is too long it has its own line
else if(wLength + 1 > LINE_LENGTH){
out.print(word);
out.println();
checker = 0;
}else if (checker == 0){
out.print(word);
//out.printf (" %s", word);
checker+= wLength;
//checker++;
}
else {
out.print(" "+word);
checker+= wLength;
checker++;
}
}
}


// Main function scans arguments and opens/closes files.
   public static void main (String[] args) {
      if (args.length == 0) {
         // There are no filenames given on the command line.
         out.printf ("FILE: -%n");
         format (new Scanner (in));
      }else {
         // Iterate over each filename given on the command line.
for (int argix = 0; argix < args.length; ++argix) {
 //Check for line length arg
            String filename = args[argix];
            
            if (filename.charAt(0) == ('-') && filename.length()>1){
LINE_LENGTH = Integer.parseInt(filename.substring(1));
}
            
            if (filename.equals ("-")) {

// Treat a filename of "-" to mean System.in.
               out.printf ("FILE: -%n");
               format (new Scanner (in));
            }else {
               // Open the file and read it, or error out.
               try {
                  Scanner infile = new Scanner (new File (filename));
                  out.printf ("FILE: %s%n", filename);
                  format (infile);
                  infile.close ();
               }catch (IOException error) {
                  exit_status = EXIT_FAILURE;
                  err.printf ("%s: %s%n", JAR_NAME,
                              error.getMessage ());
               }
            }
         
         }
      }
      exit (exit_status);
   }

}
