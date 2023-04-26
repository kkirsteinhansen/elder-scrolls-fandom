import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ptb2conll {

    /** 
     * Convert Penn Treebank sentence trees to .conll for POS tagging.
     * Tested on the WSJ training set from the Ontonotes 4.0 release.
     */

    private static List<String> ptb = new ArrayList<>();
    private static List<String> conll = new ArrayList<>();

    public static void convert(String path) throws FileNotFoundException {

        // Find innermost parentheses
        Pattern p = Pattern.compile("(\\([^\\(]* [^\\)\\(]*\\))");

        // Input reader
        File fin = new File(path);
        Scanner sc = new Scanner(fin);
    
        while (sc.hasNextLine()) {
            String ln = sc.nextLine();
            Matcher m = p.matcher(ln);
            while (m.find()) {
                String group = m.group(1) // Capture token and POS tag
                                .replaceFirst("^\\(", "")   // Remove left parenthesis
                                .replaceFirst("\\)$", "");  // Remove right parenthesis
                ptb.add(group);
            }
        }
        
        sc.close();
    
        for (String group : ptb) {
            String[] arr = group.split(" ");   // Split into token and tag
            if (arr.length != 2) {
                throw new IllegalArgumentException(
                    "More than two elements in \"" + group + "\""
                );
            }
            String row = arr[1] + "\t" + arr[0];   // Swap token and tag and separate with tab
            conll.add(row);
        }
    
        try {
            File fout = new File(path + ".conll");
            fout.createNewFile();
            FileWriter writer = new FileWriter(fout);
            int index = 0;
            int last = conll.size()-1;
            for (String row : conll) {
                writer.write(row + "\n");
                if (row.matches(".*\t\\.") && index != last)
                    writer.write("\n");   // Add newline after end-of-sentence markers except last line
                    index++;    
            } writer.close();     
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        try {
            ptb2conll.convert(args[0]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(
                "\nERROR: Missing arguments\n"
                + "Please supply input file path.\n"
                + "\n\tjava ptb2conll.java [PATH]\n"
            );
        } 
        System.out.println("Output file: " + args[0] + ".conll");
    }
}