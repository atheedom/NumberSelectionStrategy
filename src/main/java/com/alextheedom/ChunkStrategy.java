package com.alextheedom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atheedom on 24/07/15.
 */
public class ChunkStrategy implements Strategy<Integer> {


    @Override
    public List<Integer> execute(Config config) {

        List<Chunk> sortedChunks = Utils.sortedChunks;

        // TODO: do something to prevent indexoutof bounds exceptions - although it should never happen!!
        // Select the first two chunks
        Chunk chunk1 = sortedChunks.get(0);
        chunk1.setStatus(Chunk.Status.IN_USE);
        Chunk chunk2 = sortedChunks.get(1);
        chunk2.setStatus(Chunk.Status.IN_USE);

        // Generate number from chunk1
        Integer previousNumber = chunk1.getPreviousNumber();
        Integer nextNumberChunk1;
        if (previousNumber == null) {
            nextNumberChunk1 = chunk1.getStartOfChunk();
        } else {
            nextNumberChunk1 = ++previousNumber;
        }
        chunk1.setPreviousNumber(nextNumberChunk1);

        // Persist chunk one
        // chunkRepository.saveAndFlush(chunk1);

        // Generate number from chunk2
        previousNumber = chunk2.getPreviousNumber();
        Integer nextNumberChunk2;
        if (previousNumber == null) {
            nextNumberChunk2 = chunk2.getEndOfChunk();
        } else {
            nextNumberChunk2 = --previousNumber;
        }
        chunk2.setPreviousNumber(nextNumberChunk2);

        // Persist chunk two
        // chunkRepository.saveAndFlush(chunk2);

        if (nextNumberChunk1.equals(chunk1.getEndOfChunk())) {
            chunk1.setStatus(Chunk.Status.DEAD);

            // Persist chunk one
            // chunkRepository.saveAndFlush(chunk1);

            List<Chunk> tempArray = sortedChunks.stream()
                    .filter(chunk -> chunk.getStatus() == Chunk.Status.LIVE)
                    .collect(Collectors.toList());

            if (!tempArray.isEmpty()) {
                chunk1 = tempArray.get(0);
                chunk1.setStatus(Chunk.Status.IN_USE);

                // Persist chunk one
                // chunkRepository.saveAndFlush(chunk1);
            } else {
                return new ArrayList<>();
            }
        }

        if (nextNumberChunk2.equals(chunk2.getStartOfChunk())) {
            chunk2.setStatus(Chunk.Status.DEAD);

            // Persist chunk two
            // chunkRepository.saveAndFlush(chunk2);

            List<Chunk> tempArray = sortedChunks.stream()
                    .filter(chunk -> chunk.getStatus() == Chunk.Status.LIVE)
                    .collect(Collectors.toList());

            if (!tempArray.isEmpty()) {
                chunk2 = tempArray.get(0);
                chunk2.setStatus(Chunk.Status.IN_USE);

                // Persist chunk two
                // chunkRepository.saveAndFlush(chunk2);
            } else {
                return new ArrayList<>();
            }
        }

        List<Integer> numbers = new ArrayList<>();
        numbers.add(nextNumberChunk1);
        numbers.add(nextNumberChunk2);
        return numbers;
    }

}
