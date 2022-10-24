package NN;

public class ActivationSigmoid extends ActivationType {
    public ActivationSigmoid(){
        encodeName = "sigmoid";
    }

    @Override
    public double activate(double in) {
        return 1.0 / (1.0 + Math.exp(-super.activate(in)));
    }
}
