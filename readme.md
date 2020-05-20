# Sudoku Solver

## What is Sudoku?

According to Wikipedia:

>Sudoku is a logic-based, combinatorial number-placement puzzle. The objective is to fill a 9×9 grid with digits so that each column, each row, and each of the nine 3×3 subgrids that compose the grid (also called "boxes", "blocks", or "regions") contain all of the digits from 1 to 9.

## Rules of the game:
1. In each of the row can have numbers from 1 to 9 only once.
2. Each of the column can have numbers from 1 to 9 only once.
3. In each of the smaller squares can have numbers from 1 to 9 only once.

## About the solution
The current solution I have is based on possible values. The program tries to find out the value in a cell by the process of elimination. It removes all the values that are not possible in a particular value and recursively tries to identify the solution.

>Note: this is **NOT** a full proof solution. A full-proof solution should have backtracking, which this particular solution doesn't use at the moment.*