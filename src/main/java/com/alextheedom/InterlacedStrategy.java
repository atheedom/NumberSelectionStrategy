package com.alextheedom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InterlacedStrategy implements Strategy<Integer> {

    @Override
    public List<Integer> execute(Config config) {

        // Should retrieve the blocks from the database
        List<Block> sortedBlocks = Utils.sortedBlocks;

        // Select the first two blocks
        Block block1 = sortedBlocks.get(0);
        block1.setStatus(Block.Status.IN_USE);
        Block block2 = sortedBlocks.get(1);
        block2.setStatus(Block.Status.IN_USE);

        // Generate number from block1
        Integer previousNumber = block1.getPreviousNumber();
        Integer nextNumberBlock1;
        if (previousNumber == null) {
            nextNumberBlock1 = block1.getStartOfBlock();
        } else {
            nextNumberBlock1 = ++previousNumber;
        }
        block1.setPreviousNumber(nextNumberBlock1);

        // Generate number from block2
        previousNumber = block2.getPreviousNumber();
        Integer nextNumberBlock2;
        if (previousNumber == null) {
            nextNumberBlock2 = block2.getEndOfBlock();
        } else {
            nextNumberBlock2 = --previousNumber;
        }
        block2.setPreviousNumber(nextNumberBlock2);

        if (nextNumberBlock1.equals(block1.getEndOfBlock())) {
            block1.setStatus(Block.Status.DEAD);

            List<Block> tempArray = sortedBlocks.stream()
                    .filter(block -> block.getStatus() == Block.Status.LIVE)
                    .collect(Collectors.toList());

            if (!tempArray.isEmpty()) {
                block1 = tempArray.get(0);
                block1.setStatus(Block.Status.IN_USE);
            } else {
                return new ArrayList<>();
            }
        }

        if (nextNumberBlock2.equals(block2.getStartOfBlock())) {
            block2.setStatus(Block.Status.DEAD);

            List<Block> tempArray = sortedBlocks.stream()
                    .filter(block -> block.getStatus() == Block.Status.LIVE)
                    .collect(Collectors.toList());

            if (!tempArray.isEmpty()) {
                block2 = tempArray.get(0);
                block2.setStatus(Block.Status.IN_USE);
            } else {
                return new ArrayList<>();
            }
        }

        // Should persist the blocks to the database

        List<Integer> numbers = new ArrayList<>();
        numbers.add(nextNumberBlock1);
        numbers.add(nextNumberBlock2);
        return numbers;
    }

}
