package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NumberGeneratorImpl implements NumberGenerator{

    private final Random random = new Random();

    private int maxNumber;

    private int minNumber;

    public NumberGeneratorImpl(@MaxNumber int maxNumber,@MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    @Override
    public int next(){
        return random.nextInt(maxNumber-minNumber)+minNumber;
    }

    @Override
    public int getMinNumber() {
        return minNumber;
    }

    @ Override
    public int getMaxNumber(){
        return maxNumber;
    }
}
