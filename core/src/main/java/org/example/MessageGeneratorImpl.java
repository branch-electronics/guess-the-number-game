package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MessageGeneratorImpl implements MessageGenerator{

    private static final Logger log= LoggerFactory.getLogger(MessageGeneratorImpl.class);

    private final Game game;

    public MessageGeneratorImpl(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void postConstructMethod(){
        log.info("game = {}",game);
    }

    @Override
    public String getMainMessage() {
        return "Number is between "+game.getSmallest()+" and "+game.getBiggest()+". Can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon())
            return "You have won";
        else if(game.isGameLost())
            return "Sorry, you couldn't make it. The number was "+game.getNumber();
        else if(game.getRemainingGuesses()== game.getGuessCount())
            return "What's your first guess?";
        else if(!game.isValidNumberRange())
            return "Your guess is outside the valid number range";
        else{
            String dir="";
            if(game.getGuess()>game.getNumber())
                dir="Lower!";
            if(game.getGuess()<game.getNumber())
                dir="Higher!";
            return dir+" You have "+game.getRemainingGuesses()+" guesses left";
        }

    }
}
