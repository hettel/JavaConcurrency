package slides.o7_forkJoin.case_study;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import slides.o7_forkJoin.case_study.SudokuBoard.FieldPosition;
import slides.o7_forkJoin.case_study.SudokuBoard.FieldValue;

public class Sequentiel_SudokuSolver
{

  public static void main(String[] args) throws Exception
  {
    File file = new File("board01.txt");
    SudokuBoard board = SudokuBoard.load(file);
    board.print();

    System.out.println("Solve game: ");

    long start = System.currentTimeMillis();
    List<SudokuBoard> boards = solve(board);
    System.out.println("Duration " + (System.currentTimeMillis() - start) + " [ms]");
    System.out.println("Found " + boards.size());
    boards.get(0).print();

    System.out.println("done sequential");
  }

  public static List<SudokuBoard> solve(SudokuBoard board)
  {
    if (board.isComplete())
    {
      return List.of(board);
    }

    FieldPosition nextFreeField = board.getFreePosition();
    Set<FieldValue> candidates = board.getValueCandidates(nextFreeField.row, nextFreeField.col);

    if (candidates.isEmpty())
    {
      return Collections.emptyList();
    }

    List<SudokuBoard> resultList = new ArrayList<>();
    for (FieldValue field : candidates)
    {
      SudokuBoard newBoard = SudokuBoard.copy(board);
      newBoard.setValue(nextFreeField.row, nextFreeField.col, field);
      newBoard.pack();
      resultList.addAll(solve(newBoard));
    }

    return resultList;
  }
}
