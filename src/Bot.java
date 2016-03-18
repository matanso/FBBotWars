import com.facebook.urctf.bot.BotInterface;
import com.facebook.urctf.bot.Vector2i;
import com.facebook.urctf.bot.World;
import com.facebook.urctf.protocol.*;

import java.util.*;
import java.util.function.ToIntBiFunction;

/**
 * Created by matan on 18/03/16.
 */
public class Bot implements BotInterface{
    @Override
    public Move getMoveForTurn(int numTurn, int reinforcementCount, World world) {
        Vector2i size = world.getSize();
        Team myTeam = world.getMyTeam();
        List<Cell> myCells = world.getMyCells();
        Cell first = myCells.get(0);
        List<Cell> possibleCells = world.getAdjCells(first);

        List<Reinforcement> reinforcements = new ArrayList<>();
        reinforcements.add(new Reinforcement(first.x, first.y, reinforcementCount));
        List<Action> actions = new ArrayList<>();
        return new Move(reinforcements, actions);
    }

    private List<Blob> generateBlobs(World world){
        List<Cell> cells = world.getMyCells();
        TreeSet<Cell> cellSet = new TreeSet<>(cells);
        List<Blob> blobs = new ArrayList<>();
        while (cellSet.size() > 0) {
            Cell current = cellSet.pollFirst();
            TreeSet<Cell> toCheck = new TreeSet<>();
            Blob blob = new Blob();
            toCheck.add(current);
            while (toCheck.size() > 0){
                Cell checking = toCheck.pollFirst();
                for(Cell cell: world.getAdjCells(checking)){
                    if(blob.cells.contains(cell)) continue;
                    if(cell.getTeam().equals(world.getMyTeam())) {
                        blob.cells.add(cell);
                        toCheck.add(cell);
                    }
                }
            }
            blobs.add(blob);
        }
        return blobs;
    }

    private int getPriority(World world, Cell cell) {
        return world.getAdjCells(cell).stream().mapToInt(cell1 -> cell1.getTeam().equals(world.getMyTeam()) ? 0 : cell1.armySize).sum();
    }

    private class Blob {
        public Blob(List<Cell> cells) {
            this.cells = cells;
        }

        List<Cell> cells;

        Blob() {
            cells = new ArrayList<>();
        }

        int getArmySize(){
            return cells.stream().mapToInt(cell -> cell.armySize).sum();
        }

        List<Action> move(World world){
        }
    }

}
