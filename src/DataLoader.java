import NN.DataSet;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {
    public static DataSet loadData(String filePath, String[] words) {
        String[] lines = FileReader.readLines(filePath);

        ArrayList<ArrayList<ArrayList<String>>> res = new ArrayList<>();
        int chunk = -1;
        int chunkLine = 0;

        int length = 0;
        int totalChunks = 0;
        for(int l = 0; l < lines.length; l++){
            if(lines[l].startsWith("-")) {
                length++;
            } else{
                totalChunks++;
            }
        }

        double[][] input = new double[length][10 * (words.length + 1)];
        double[][] output = new double[length][totalChunks];

        for(int l = 0; l < lines.length; l++){
            if(lines[l].startsWith("-")) {
                input[chunkLine] = encode(TextProcessor.process(lines[l]), words);
                output[chunkLine] = oneHotNum(chunk, totalChunks);
                chunkLine++;
            }else{
                chunk++;
            }
        }

        return new DataSet(input, output);
    }

    private static double[] oneHotNum(int num, int max){
        double[] res = new double[max];
        for(int i = 0; i < max; i++){
            if(i == num) res[i] = 1.0;
            else res[i] = 0.0;
        }
        return res;
    }

    public static double[] encode(String[] wordArr, String[] words) {
        double[] res = new double[10 * (words.length + 1)];
        for (int w = 0; w < 10; w++) {
            int index = -1;
            if (w < wordArr.length) {
                index = List.of(words).indexOf(wordArr[w]);
                if (index == -1) index = words.length;
            }
            for (int i = 0; i <= words.length; i++) {
                if (i == index) res[i + w * (words.length + 1)] = 1;
                else res[i + w * (words.length + 1)] = 0;
            }
        }
        return res;
    }
}
