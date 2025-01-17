// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
    //    int currentIndex = 0;
    //    while(currentIndex < markdown.length()) {
    //        int nextOpenBracket = markdown.indexOf("[", currentIndex);
    //        int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
    //        int openParen = markdown.indexOf("(", nextCloseBracket);
    //        int closeParen = markdown.indexOf(")", openParen);
            //if(nextCloseBracket == openParen-1 && nextOpenBracket != nextCloseBracket-1){
    //        toReturn.add(markdown.substring(openParen + 1, closeParen));
    //        currentIndex = closeParen + 1;
    //    }
        
        String[] contentsArray = markdown.split("\n");
        for(String s: contentsArray){
            String[] lineArray = s.split("");
            int firstIndex = 0;
            if(lineArray[0].equals("[") && lineArray[lineArray.length-1].equals(")")){
                for(int i = lineArray.length -1; i >0; i--) {
                    if(lineArray[i].equals("(") && lineArray[i-1].equals("]")){
                        firstIndex = i +1;
                    }
                }
                toReturn.add(s.substring(firstIndex,s.length()-1));
            }
        }
        return toReturn;
    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
        
    }
}