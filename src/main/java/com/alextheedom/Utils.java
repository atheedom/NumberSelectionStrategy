package com.alextheedom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by atheedom on 02/07/2014.
 */
public class Utils {

    public static List<Block> sortedBlocks = new ArrayList<>();

    /**
     * Make the blocks for the given raffle.
     */
    public static void generateBlocks(Config config) {

        sortedBlocks.clear();

        int possibleRange = (config.getLastNumber() - config.getInitialNumber()) + 1;

        double numberOfBlocks = possibleRange / config.getBlockSize();

        List<Block> blocks = new ArrayList<>();

        int currentBlockStart = config.getInitialNumber();
        int currentBlockEnd;

        for (int i = 1; i <= numberOfBlocks; i++) {
            currentBlockEnd = currentBlockStart + config.getBlockSize() - 1;

            // Last block end number will always be equal to the endRange number
            if (i == numberOfBlocks) {
                currentBlockEnd = config.getLastNumber();
            }

            Block block = new Block(i, currentBlockStart, currentBlockEnd, null, Block.Status.LIVE);

            blocks.add(block);
            currentBlockStart = (currentBlockEnd + 1);
        }

        // Split chucks and order
        List<Block> listOddBlocks = blocks
                .stream()
                .filter(block -> block.getIdx() % 2 == 1)
                .sorted(Block::compareTo)
                .collect(Collectors.toList());

        List<Block> listEvenBlock = blocks
                .stream()
                .filter(block -> block.getIdx() % 2 == 0)
                .sorted(Block::compareTo)
                .collect(Collectors.toList());

        // Concatenate the two lists.
        sortedBlocks.addAll(listOddBlocks);
        sortedBlocks.addAll(listEvenBlock);

        // Reindex chucks to allow ordering
        int[] idx = {0};
        sortedBlocks.stream().forEachOrdered(block -> block.setIdx(idx[0]++));

        // persist in db
        // Store in class for ease of demonstration

    }

}
