# NumberSelectionStrategy
Generates numbers base on a number selection strategy.


# Problem

Select one of two number selection strategies and select two numbers using that strategy.

Strategy one: Ordered
Numbers are selected sequentially.

Strategy two: Interlaced
Numbers are selected from interlaced groups of numbers so that no two numbers are sequentail and sufficently apart to appear randomly selected. No number can be selected more than once.

# Solution

The strategy is selected using an enum implementation of the strategy pattern. 

Strategy one: Ordered
The last number selected is persisted in the database and the next two numbers are selected.

Strategy two: Interlaced
Blocks of numbers are generated based on a block size and a minimum and maximum number. Numbers are selected from the begining of one block and the end of the other in ascending and descending order respectively. 

Example:

Example: total numbers 10000, starting at 1000 with a block size of 1000.

  block 1: 1000 - 1999
  block 2: 2000 - 2999
  block 3: 3000 - 3999
  block 4: 4000 - 4999
  ... 

Note: the last block should end 10,000 not 9999.

Two numbers are chosen. One number is chosen from the beginning of block 1 and another from the end of block 3, this should continue until both blocks have been exhausted of numbers. Selection should start again from the beginning of block 2 and the end of block 4 until all numbers where exhausted. This should continue untill all blocks have been used up.

Important points to take account of:
* There must be an even number of blocks
* the block size must be such that the number of blocks is even such that ((endRange - startRange) + 1 / blockSize ) % 2 == 0;
