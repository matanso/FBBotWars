namespace java com.facebook.urctf.protocol
namespace py warhead.protocol

typedef i32 int;

/** A direction **/
enum Direction {
    UP = 1,
    RIGHT = 2,
    DOWN = 3,
    LEFT = 4,
}

/** A team */
enum Team {
    RED = 1,
    BLUE = 2,
}

/** A type of square **/
enum Terrain {
    /** Normal square (white) */
    NORMAL = 1,
    /** Obstacle (blocked) square (grey) */
    OBSTACLE = 2,
    /** Defenser advantage (green) */
    DEF_ADV = 3,
    /** Attacker advantage (red) */
    ATT_ADV = 4,
}

/** A single square on the board */
struct Cell {
    /** The current team that occupies the square (could be <code>null</code> if unoccupied) */
    1: optional Team team;
    /** Number of units the occupying team has in the square */
    2: required int armySize;
    /** Square's X coordinate */
    3: required int x;
    /** Square's Y coordinate */
    4: required int y;
    /** Terrain type in square */
    5: required Terrain terrain;
}

/** A grid of cells */
typedef list<list<Cell>> Board;

/** An instruction to reinforce a square */
struct Reinforcement {
    /** X coordinate of square to reinforce */
    1: required int x;
    /** Y coordinate of square to reinforce */
    2: required int y;
    /** Number of units to drop in square */
    3: required int armySize;
}

/** An instruction to move units from one square to an adjacent one */
struct Action {
    /** X coordinate of square to move from */
    1: required int x;
    /** Y coordinate of square to move from */
    2: required int y;
    /** Number of units to move */
    3: required int armySize;
    /** Direction to move units to */
    4: required Direction direction;
}

/** A bot's reply to a game turn */
struct Move {
    /** List of reinforcement instructions */
    1: required list<Reinforcement> reinforcements;
    /** List of move instructions */
    2: required list<Action> actions;
}

service PlayWarHead {
    void start(1: required int gameId, 2: required Team team),
    Move move(
        1: required int gameId,
        2: required int turnNum,
        3: required int reinforcementCount,
        4: required Board board
    ),
    void result(1: required int gameId, 2: required Board board)
}
