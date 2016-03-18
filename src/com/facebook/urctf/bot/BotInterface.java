package com.facebook.urctf.bot;

import com.facebook.urctf.protocol.Move;

/**
 * Interface used by Bots to implement game logic.
 */
public interface BotInterface {
    /**
     * <p>Called each turn by the game engine, to get the next moves the bot wants to do.</p>
     * @param numTurn Turn number in the game (starting with zero)
     * @param reinforcementCount Max. number of units that the Bot can drop as reinforcements in this turn.
     * @param world The current state of the world.
     * @return All reinforcement and movement commands the bot is issuing in this turn.
     */
    Move getMoveForTurn(int numTurn, int reinforcementCount, World world);
}
