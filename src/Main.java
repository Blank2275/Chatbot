import NN.*;

public class Main {
    public static void main(String[] args) {
        String s = "What's your name. A dog is cool";
        String[] uniqueWords = "a, about, are, best, call, called, can, cats, color, do, dogs, doing, favorite, give, good, hear, hello, hi, hola, how, i, is, joke, like, m, me, my, name, not, or, prefer, say, should, sup, tell, to, up, what, you, your, yours".split(", ");
        uniqueWords = TextProcessor.process(String.join(" ", uniqueWords));
        uniqueWords = TextProcessor.removeDuplicates(uniqueWords);

        for(String word: uniqueWords){
            System.out.println(word);
        }

        for(String string: TextProcessor.process(s)){

//            System.out.println(string);
        }

        DataSet data = DataLoader.loadData("./src/Data/training.txt", uniqueWords);
//        for(int i = 0; i < data.getInput().length; i++){
//            for(int j = 0; j < data.getInput()[0].length; j++){
//                System.out.print(data.getInput()[i][j] + " ");
//            }
//            System.out.println();
//        }

//        for(int i = 0; i < data.getOutput().length; i++){
//            for(int j = 0; j < data.getOutput()[0].length; j++){
//                System.out.print(data.getOutput()[i][j] + " ");
//            }
//            System.out.println();
//        }

        int inputSize = data.getInput()[0].length;
        int outputSize = data.getOutput()[0].length;

        Layer[] layers = new Layer[6];
        layers[0] = new Layer(null, inputSize);
        layers[1] = new Layer(new ActivationRelu(), 64);
        layers[2] = new Layer(new ActivationRelu(), 64);
        layers[3] = new Layer(new ActivationRelu(), 32);
        layers[4] = new Layer(new ActivationRelu(), 32);
        layers[5] = new Layer(new ActivationSigmoid(), outputSize);
        Network base = new Network(layers);

        Trainer trainer = new Trainer(150, base);
        Network best = trainer.train(30, data);

        Saver saver = new Saver(best);
        saver.save("./best.wb");
    }
}