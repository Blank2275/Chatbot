import NN.*;

import java.awt.font.FontRenderContext;

public class Chatbot {
    public static void main(String[] args){
        String[] words = TextProcessor.loadWords();
//        String[] words = "a, about, are, best, call, called, can, cats, color, do, dogs, doing, favorite, give, good, hear, hello, hi, hola, how, i, is, joke, like, m, me, my, name, not, or, prefer, say, should, sup, tell, to, up, what, you, your, yours".split(", ");
//        words = TextProcessor.process(String.join(" ", words));
//        words = TextProcessor.removeDuplicates(words);
        for(String word: words){
            System.out.println(word);
        }
        Network network = Saver.load("./best.wb");
        String message = "sup";
        System.out.println(runNetwork(network, message, words));
    }

    private static int runNetwork(Network network, String input, String[] words){
        double[] encoded = DataLoader.encode(TextProcessor.process(input), words);
//        for(int i = 0; i < encoded.length; i++){
//            if(encoded[i] == 1) System.out.println(i);
//        }

        double[] output = network.evaluateNetwork(encoded);

        for(double d: output){
            System.out.print(d + " ");
        }
        System.out.println();

        double max = output[0];
        int index = 0;
        for(int i = 0; i < output.length; i++){
            if(output[i] > max){
                max = output[i];
                index = i;
            }
        }

        return index;
    }
}
