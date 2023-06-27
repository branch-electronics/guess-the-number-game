package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class GameImpl implements Game{

//    constants
    private static final Logger log= LoggerFactory.getLogger(GameImpl.class);

//    fields
    private final NumberGenerator numberGenerator;

    private final int guesscount;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guesscount) {
        this.numberGenerator = numberGenerator;
        this.guesscount = guesscount;
    }

    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange=true;

//    Constructor based dependency injection
//    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }

//    public methods
//    init method
    @PostConstruct
    @Override
    public void reset() {
        smallest=numberGenerator.getMinNumber();
        guess=numberGenerator.getMinNumber();
        remainingGuesses=guesscount;
        biggest=numberGenerator.getMaxNumber();
        number=numberGenerator.next();
    }

    @PreDestroy
    public void preDestroy(){
        log.info("In Game PreDestroy");
    }

//    Setter based dependency injection
//    public void setNumberGenerator(NumberGenerator numberGenerator){
//        this.numberGenerator=numberGenerator;
//    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess=guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    @Override
    public int getGuessCount() {
        return guesscount;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if(guess>number){
                biggest=guess-1;
            }
            if(guess<number){
                smallest=guess+1;
            }
        }
        remainingGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses<=0;
    }

//    private method
    private void checkValidNumberRange(){
        validNumberRange = (guess>=smallest) && (guess<=biggest);
    }
}
