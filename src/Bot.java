import com.facebook.urctf.bot.BotInterface;
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
        List<Reinforcement> reinforcements = new ArrayList<>();
        List<Action> actions = new ArrayList<>();
        List<Blob> blobs = generateBlobs(world);

        int totalPriority = 0;
        for(Cell cell: world.getMyCells()) totalPriority += cell.armySize;
        for(Blob blob: blobs){
            int priority = blob.getArmySize();
            int blobReinforcement = reinforcementCount * (priority / totalPriority);
            reinforcementCount -= blobReinforcement;
            totalPriority -= priority;
            reinforcements.addAll(blob.reinforcements(world, blobReinforcement));
            actions.addAll(blob.move(world));
        }

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
                blob.cells.add(checking);
                for(Cell cell: world.getAdjCells(checking)){
                    if(blob.cells.contains(cell)) continue;
                    if(cell.getTeam().equals(world.getMyTeam())) {
                        cellSet.remove(cell);
                        toCheck.add(cell);
                    }
                }
            }
            blobs.add(blob);
        }
        return blobs;
    }

    private int getPriority(World world, Cell cell) {
        int sum = 0;
        for(Cell s: world.getAdjCells(cell)) sum += (world.getMyTeam().equals(s.getTeam()) ? 0 : s.armySize + 1);
        return sum;
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
            int sum = 0;
            for(Cell s: cells) sum += s.getArmySize();
            return sum;
        }

        List<Action> move(World world){
            List<Action> actions = new ArrayList<>();
            for(Cell cell: cells){
                int priority = getPriority(world, cell);
                int armyToSpare = cell.armySize - 1;
                int surroundingPriority = priority;
                for(Cell s: world.getAdjCells(cell)) surroundingPriority += getPriority(world, s);

                for(Cell s: world.getAdjCells(cell)){
                    int cellPriority = getPriority(world, s);
                    int moveArmy = armyToSpare * (cellPriority / surroundingPriority);
                    surroundingPriority -= cellPriority;
                    armyToSpare -= moveArmy;
                    actions.add(new Action(cell.x, cell.y, moveArmy, getDirection(cell, s)));
                }
            }
            return actions;
        }

        List<Reinforcement> reinforcements(World world, int reinforcementCount){
            int totalPriority = 0;
            for(Cell s: cells) totalPriority += getPriority(world, s);
            List<Reinforcement> reinforcementList = new ArrayList<>();
            for(Cell s: cells){
                int cellPriority = getPriority(world, s);
                int reinforce = reinforcementCount * (cellPriority / totalPriority);
                totalPriority -= cellPriority;
                reinforcementCount -= reinforce;
                reinforcementList.add(new Reinforcement(s.x, s.y, reinforce));
            }
            return reinforcementList;
        }

        Direction getDirection(Cell src, Cell dest) {
            int x = dest.x - src.x;
            int y = dest.y - src.y;
            if(y == 0) return x == 1 ? Direction.RIGHT : Direction.LEFT;
            return y == 1 ? Direction.UP : Direction.DOWN;
        }
    }

}