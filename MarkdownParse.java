// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        int count =0;
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int links = 0;
        for(int i = 0; i < markdown.length(); i++){
            count++;
            if(markdown.charAt(i) == ')'){
                links++;
            }
        }
        int currentIndex = 0;
        while(currentIndex < markdown.length() && links>0) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextCloseBracket == openParen-1 && nextOpenBracket != nextCloseBracket-1){
                toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
            links--;
            /*System.out.println("Current " + currentIndex);
            System.out.println("nextOpen " + nextOpenBracket);
            System.out.println("nextClose " + nextCloseBracket);
            System.out.println("openParen " + openParen);
            System.out.println("closeParen " + closeParen);*/
        }
        System.out.print(count);
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
        
    }
}