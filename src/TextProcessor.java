import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextProcessor {
    public static String[] process(String input){
        input = clean(input);
        String[] inputArr = input.split(" ");
        inputArr = stem(inputArr);
        inputArr = removeStops(inputArr);
        return inputArr;
    }

    public static String[] removeStops(String[] input){
        ArrayList<String> res = new ArrayList<String>();
        String[] stops = FileReader.readLines("./src/Data/stopwords.txt");

        for(String word: input){
            if(!Arrays.asList(stops).contains(word)) res.add(word);
        }

        return res.toArray(new String[0]);
    }

    public static String clean(String s){
        s = s.toLowerCase();
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        String res = "";
        for(char c: s.toCharArray()){
            if(Character.isLetter(c) || c == ' ' || c == '*') res += c;
        }

        return res;
    }

    public static String[] stem(String[] input){
        String[] res = new String[input.length];
        for(int w = 0; w < input.length; w++){
            res[w] = stemWord(input[w]);
        }

        return res;
    }

    public static String[] removeDuplicates(String[] words){
        ArrayList<String> res = new ArrayList<String>(List.of(words));
        for(int i = 0; i < res.size() - 1; i++){
            for(int j = i + 1; j < res.size(); j++) {
                if(res.get(i).equals(res.get(j))){
                    res.remove(j);
                    j--;
                }
            }
        }
        return res.toArray(new String[0]);
    }

    private static String stemWord(String word){
        String[] endings = new String[]{"tion", "ity", "er", "ness", "ism", "ment", "ant", "ship", "age", "erying", "es", "s"};
        for(String ending: endings){
            if(word.equals("*")) {
                word = "*filler";
                return word;
            } else if(word.endsWith(ending) && !word.endsWith("is")){
                word = word.substring(0, word.length() - ending.length());
                return word;
            }
        }
        return word;
    }

    public static String[] loadWords(){
        String[] uniqueWords = FileReader.readLines("./src/Data/words.txt");
        uniqueWords = process(String.join(" ", uniqueWords));
        uniqueWords = removeDuplicates(uniqueWords);

        return uniqueWords;
    }
}
