package com.alextheedom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atheedom on 02/07/2014.
 */
public class Utils {

    public static List<Chunk> sortedChunks = new ArrayList<>();

    /**
     * Make the chucks for the given raffle.
     */
    public static void makeChunks(Config config) {

        sortedChunks.clear();

        int possibleRange = (config.getLastNumber() - config.getInitialNumber()) + 1;

        double numberOfChunks = possibleRange / config.getBlockSize();

        List<Chunk> chunks = new ArrayList<>();

        int currentChunkStart = config.getInitialNumber();
        int currentChunkEnd;

        for (int i = 1; i <= numberOfChunks; i++) {
            currentChunkEnd = currentChunkStart + config.getBlockSize() - 1;

            // Last chunk end number will always be equal to the endRange number
            if (i == numberOfChunks) {
                currentChunkEnd = config.getLastNumber();
            }

            Chunk chunk = new Chunk(i, currentChunkStart, currentChunkEnd, null, Chunk.Status.LIVE);

            chunks.add(chunk);
            currentChunkStart = (currentChunkEnd + 1);
        }

        // Split chucks and order
        List<Chunk> listOddChunks = chunks
                .stream()
                .filter(chunk -> chunk.getIdx() % 2 == 1)
                .sorted(Chunk::compareTo)
                .collect(Collectors.toList());

        List<Chunk> listEvenChunk = chunks
                .stream()
                .filter(chunk -> chunk.getIdx() % 2 == 0)
                .sorted(Chunk::compareTo)
                .collect(Collectors.toList());

        // Concatenate the two lists.
        sortedChunks.addAll(listOddChunks);
        sortedChunks.addAll(listEvenChunk);

        // Reindex chucks to allow ordering
        int[] idx = {0};
        sortedChunks.stream().forEachOrdered(chunk -> chunk.setIdx(idx[0]++));

        // persist in db
        // Store in class for ease of demonstration

    }

}
