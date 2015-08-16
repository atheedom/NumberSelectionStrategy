package com.alextheedom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by atheedom on 24/07/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TicketNumberGeneration {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGenerateTwoNumbersWithSequentialNumbersFromTicketStart() {

        // arrange
        Config config = new Config();
        config.setInitialNumber(1000);
        config.setLastNumber(10000);
        config.setNumberPerSelection(2);
        config.setNextNumber(1000);

        // act
        List<Integer> generatedTicketNumbers = NumberStrategy.SEQUENTIAL.getStrategy().execute(config);

        // assert
        assertThat(generatedTicketNumbers.size()).isEqualTo(2);
        assertThat(generatedTicketNumbers.get(0)).isEqualTo(1000);
        assertThat(generatedTicketNumbers.get(1)).isEqualTo(1001);
    }

    @Test
    public void shouldGenerateTwoNumbersWithSequentialNumbersFromNextNumber() {

        // arrange
        Config config = new Config();
        config.setInitialNumber(1000);
        config.setLastNumber(10000);
        config.setNumberPerSelection(2);
        config.setNextNumber(1248);

        // act
        List<Integer> generatedTicketNumbers = NumberStrategy.SEQUENTIAL.getStrategy().execute(config);

        // assert
        assertThat(generatedTicketNumbers.size()).isEqualTo(2);
        assertThat(generatedTicketNumbers.get(0)).isEqualTo(1248);
        assertThat(generatedTicketNumbers.get(1)).isEqualTo(1249);
    }

    @Test
    public void shouldGenerateTwoNumbersWithChunkStrategy() {

        // arrange
        Config config = new Config();
        config.setInitialNumber(1_000);
        config.setLastNumber(11_000);
        config.setNumberPerSelection(2);
        config.setBlockSize(1_000);

        Utils.makeChunks(config);

        // act
        List<Integer> generatedTicketNumbers = NumberStrategy.CHUNK.getStrategy().execute(config);

        // assert
        assertThat(generatedTicketNumbers.size()).isEqualTo(2);
    }

    @Test
    public void shouldGenerateTwoNumbersAtLeastChunkSizeApartWithChunkStrategy() {

        // arrange
        Config config = new Config();
        config.setInitialNumber(1_000);
        config.setLastNumber(11_000);
        config.setNumberPerSelection(2);
        config.setBlockSize(1_000);

        Utils.makeChunks(config);

        // act
        List<Integer> generatedTicketNumbers = NumberStrategy.CHUNK.getStrategy().execute(config);

        // assert
        Integer number1 = generatedTicketNumbers.get(0);
        Integer number2 = generatedTicketNumbers.get(1);

        assertThat(number2-number1).isGreaterThan(config.getBlockSize());
    }

    @Test
    public void shouldGenerateTheTwoNumbersGivenWithChunkStrategy() {

        // arrange
        Config config = new Config();
        config.setInitialNumber(1_000);
        config.setLastNumber(11_000);
        config.setNumberPerSelection(2);
        config.setBlockSize(1_000);

        Utils.makeChunks(config);

        // act
        List<Integer> generatedTicketNumbers = NumberStrategy.CHUNK.getStrategy().execute(config);

        // assert
        assertThat(generatedTicketNumbers.get(0)).isEqualTo(1000);
        assertThat(generatedTicketNumbers.get(1)).isEqualTo(3999);
    }

}
