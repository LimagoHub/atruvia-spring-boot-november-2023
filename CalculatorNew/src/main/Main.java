package main;

import main.client.CalcClient;
import main.common.LoggerProxy;
import main.math.Calculator;
import main.math.CalculatorImpl;
import main.math.CalculatorLogger;
import main.math.CalculatorSecure;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new CalculatorImpl();
        //calculator = new CalculatorLogger(calculator);
        calculator = (Calculator) LoggerProxy.newInstance(calculator);
        calculator = new CalculatorSecure(calculator);
        CalcClient client = new CalcClient(calculator);
        client.go();
    }
}