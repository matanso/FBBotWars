package com.facebook.urctf.bot;

import com.facebook.urctf.protocol.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.NotPredicate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Represents Game State; Also implements all support functions available to Java bots.</p>
 */
public class World {
    private final List<List<Cell>> mMap;
    private final Team mTeamId;
    private final Vector2i mSize;

    /**
     * Constructor for World (not needed in Bot code; it already has <code>world</code> argument)
     * @param map The board, as a two dimensional array of cells
     * @param myTeamId The Bot's team property
     */
    public World(List<List<Cell>> map, Team myTeamId) {
        mMap = map;
        mTeamId = myTeamId;
        mSize = new Vector2i(map.size(), map.get(0).size());
    }

    /**
     * Obtain the Bot's team property.
     * Useful for interpreting ownership of cells, as outlined in the following code snippet.
     * <pre><code>
     *     Cell cell = ...;
     *     if (cell.team == world.getMyTeam()) { ... } // cell belongs to me
     *     else if (cell.team == null) { ... } // cell is unoccupied
     *     else { ... } // cell belongs to enemy
     * </code></pre>
     * @return The Bot's team property.
     */
    public Team getMyTeam() {
        return mTeamId;
    }

    /**
     * <p>Obtain the dimensions of the game board.</p>
     * <p>In this contest, this value is assured to be constant, i.e. same as:</p>
     * <pre><code>
     *     return new Vector2i(6, 6)
     * </code></pre>
     * @return Dimensions of the game board as a vector.
     */
    public Vector2i getSize() {
        return mSize;
    }

    /**
     * Obtain a list of all cells (tiles) on the board.
     * Note that this includes cells with OBSTACLE terrain.
     * @return List of all cells on the board.
     */
    public List<Cell> getAllCells() {
        ArrayList<Cell> result = new ArrayList<>();
        for (List<Cell> cellList : mMap) {
            result.addAll(cellList);
        }

        return result;
    }

    /**
     * <p>Obtain a list of cells occupied by the Bot.
     * The Bot may assume that the returned list is non-empty.
     * @return List of cells occupied by the Bot.
     */
    public List<Cell> getMyCells() {
        List<Cell> tmp = getAllCells();
        CollectionUtils.filter(tmp, new Predicate() {
            @Override
            public boolean evaluate(Object cell) {
                return ((Cell)cell).getTeam() == mTeamId;
            }
        });
        return tmp;
    }

    /**
     * <p>Obtain a reference to a Cell based on its location on the board.</p>
     * <p>The cell is "valid" if (x, y) points to a location inside the board, and the cell
     * at that location does not have OBSTACLE terrain.</p>
     * @param x x-coordinate of specified location
     * @param y y-coordinate of specified location
     * @return The cell at the specified location, or null if such Cell is not valid
     */
    public Cell getCellIfValid(int x, int y) {
        if (x < 0 || x >= mSize.x || y < 0 || y >= mSize.y) {
            return null;
        }
        Cell cell = mMap.get(x).get(y);
        if (cell.terrain == Terrain.OBSTACLE) {
            return null;
        }

        return cell;
    }

    /**
     * <p>Obtain a list of cells that are adjacent to the specified cell.</p>
     * <p>This function can be effectively used to traverse the board as a bidirectional graph.</p>
     * <p>The function is also useful in combination with <code>addAction</code>:</p>
     * <pre><code>
     *     Cell cell = ...;
     *     for (Cell adj : world.getAdjCells(cell)) {
     *         // suppose we decided to move units from cell to adj
     *         int unitsToMove = ...;
     *         world.addAction(world, cell, adj, unitsToMive);
     *     }
     * </code></pre>
     * @param cell the specified cell
     * @return A list of cells that are adjacent to argument cell and are valid.
     */
    public List<Cell> getAdjCells(Cell cell) {
        List<Cell> result = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            Vector2i delta = directionToVector(dir);
            int x = cell.x + delta.x;
            int y = cell.y + delta.y;
            Cell adj = getCellIfValid(x, y);
            if (adj != null) {
                result.add(adj);
            }
        }
        return result;
    }

    /**
     * <p>Transform a direction to its corresponding vector on the plane.</p>
     * <p>When units move in direction <code>dir</code> from cell <code>src</code>,
     * the units will arrive at the following location <code>dst</code>:</p>
     * <pre><code>
     *     Cell src = ...;
     *     Vector2i v = world.directionToVector(dir)
     *     Cell dst = world.getCellIfValid(src.x + v.x, src.y + v.y)
     * </code></pre>
     * @param dir The input direction
     * @return The equivalent of <code>dir</code> as a vector.
     */
    public static Vector2i directionToVector(Direction dir) {
        if (dir == Direction.UP) {
            return Vector2i.UP;
        } else if (dir == Direction.DOWN) {
            return Vector2i.DOWN;
        } else if (dir == Direction.LEFT) {
            return Vector2i.LEFT;
        } else if (dir == Direction.RIGHT) {
            return Vector2i.RIGHT;
        } else {
            throw new IllegalArgumentException("Unmapped direction enum value");
        }
    }

    /**
     * Transform a vector to an equivalent direction, if possible.
     * @param delta The vector to transform.
     * @return an equivalent direction.
     * If no such direction exists, the function throws IllegalArgumentException.
     */
    public static Direction directionFromVector(Vector2i delta) {
        if (delta.equals(Vector2i.UP)) {
            return Direction.UP;
        }
        if (delta.equals(Vector2i.DOWN)) {
            return Direction.DOWN;
        }
        if (delta.equals(Vector2i.LEFT)) {
            return Direction.LEFT;
        }
        if (delta.equals(Vector2i.RIGHT)) {
            return Direction.RIGHT;
        }
        throw new IllegalArgumentException(delta.toString() + " is not equivalent to a direction");
    }

    /**
     * <p>Adds a reinforcement command to the list of commands issued this turn, given a cell and the amount of units requested.</p>
     * <p>Accounts for this many units being added to the cell, so its armySize will reflect
     the updated amount that may still move away from it in this turn, assuming the command is legal.</p>
     * @param move The bot's move for this turn.
     * @param cell Cell to reinforce
     * @param armySize Amount of units requested
     */
    public void addReinforcement(Move move, Cell cell, int armySize) {
        Reinforcement r = new Reinforcement(cell.x, cell.y, armySize);
        move.addToReinforcements(r);
        cell.armySize += armySize;
    }

    /**
     * <p>Adds an action (movement) command to the list of commands issued this turn, given a source cell, destination cell and an amount of units to move.</p>
     * <p>Accounts for this many units moving away from the source cell, so its armySize will reflect
     * the amount that may still move out of it in this turn, assuming the command is legal.</p>
     * @param move The bot's move for this turn.
     * @param src source cell.
     * @param dst destination cell.
     * @param armySize amount of units to move.
     */
    public void addAction(Move move, Cell src, Cell dst, int armySize) {
        Vector2i delta = new Vector2i(dst.x - src.x, dst.y - src.y);
        Action a = new Action(src.x, src.y, armySize, directionFromVector(delta));
        move.addToActions(a);
        src.armySize -= armySize;
    }

    /**
     * <p>Simulates combat in a tile given its current ownership.</p>
     * <p>The outcome of the simulation is an integer, with the following semantics:</p>
     * <p>   <code>x</code> (given some <code>x &gt; 0</code>) if Bot would win with <code>x</code> units remaining</p>
     * <p>   <code>0</code> if both sides would lose all units</p>
     * <p>   <code>-x</code> (given some <code>x &gt; 0</code>) if enemy would win with <code>x</code> units remaining</p>
     * @param cell The cell to consider combat in.
     * @param myArmySize Bot's unit count in the cell (prior to combat)
     * @param enemyArmySize Enemy's unit count in the cell (prior to combat)
     * @return outcome, as specified in description.
     */
    public int simulateCombatAtCell(Cell cell, int myArmySize, int enemyArmySize) {
        boolean myDef = false;
        boolean enemyDef = false;
        Team occupier = cell.team;
        if (occupier == mTeamId) {
            myDef = true;
        } else if (occupier != null) {
            enemyDef = true;
        }
        return simulateCombatOnTerrain(cell.terrain, myDef, enemyDef, myArmySize, enemyArmySize);
    }

    /**
     * <p>Simulates combat on a given terrain type. Caller must specify which side (if any) is defending.</p>
     * <p>Outcome is returned with the same semantics as <code>simulateCombatAtCell()</code></p>
     * @param terrain Terrain to simulate combat on
     * @param myDef True if Bot is defending
     * @param enemyDef True if enemy is defending
     * @param myArmySize Bot's unit count participating in combat
     * @param enemyArmySize enemy unit count participating in combat
     * @return outcome, as specified in description.
     */
    public static int simulateCombatOnTerrain(Terrain terrain, boolean myDef, boolean enemyDef, int myArmySize, int enemyArmySize) {
        int myEff = getCombatEffectiveness(myDef, terrain);
        int enemyEff = getCombatEffectiveness(enemyDef, terrain);

        return getCombatOutcome(myArmySize, enemyArmySize, myEff, enemyEff);
    }

    /**
     * <p>Gets the combat effectiveness rating per unit (as described by game rules)
     * given the terrain type and whether or not they are defending.</p>
     * @param isDefender Whether units are defending.
     * @param terrain Terrain the combat takes place on.
     * @return Combat effectiveness rating.
     */
    public static int getCombatEffectiveness(boolean isDefender, Terrain terrain) {
        // A simple lookup table
        if (terrain.equals(Terrain.NORMAL)) {
            return (isDefender) ? 5 : 4;
        } else if (terrain.equals(Terrain.ATT_ADV)) {
            return (isDefender) ? 4 : 5;
        } else if (terrain.equals(Terrain.DEF_ADV)) {
            return (isDefender) ? 6 : 3;
        } else if (terrain.equals(Terrain.OBSTACLE)) {
            // Makes no sense, but let's not throw
            return 1;
        }
        throw new RuntimeException("Unrecognized terrain type");
    }

    /**
     * <p>Core algorithm used to determine combat outcome given the amount of units and the effectiveness per unit of each side</p>
     * <p>Outcome has the same semantics as explained in <code>simulateCombatAtCell</code></p>
     * @param n0 Number of units on Bot's side.
     * @param n1 Number of units on enemy's side.
     * @param e0 Combat effectiveness on Bot's side.
     * @param e1 Combat effectiveness on enemy's side.
     * @return Outcome, as specified in description.
     */
    public static int getCombatOutcome(int n0, int n1, int e0, int e1) {
        int r0, r1;
        if (n0 > 0 && n1 > 0) {
            // calculate combat strength
            double s0 = n0; s0 *= e0;
            double s1 = n1; s1 *= e1;

            // solve for amount of casualties sufficient to make at least one side lose all units
            double t0 = n0 / s1;
            double t1 = n1 / s0;
            double t = Math.min(t0, t1);

            // amount of remaining units on each side
            r0 = (int) Math.floor(n0 - s1 * t);
            r1 = (int) Math.floor(n1 - s0 * t);
        } else {
            r0 = n0;
            r1 = n1;
        }
        if (r0 <= 0 && r1 <= 0) {
            // all units expire
            return 0;
        }
        if (r1 <= 0) {
            // side 0 remains with r0 units
            return r0;
        }
        // side 1 remains with r1 units
        return -r1;
    }
}
