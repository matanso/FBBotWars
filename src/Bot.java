import com.facebook.urctf.bot.BotInterface;
import com.facebook.urctf.bot.Vector2i;
import com.facebook.urctf.bot.World;
import com.facebook.urctf.protocol.Action;
import com.facebook.urctf.protocol.Cell;
import com.facebook.urctf.protocol.Move;
import com.facebook.urctf.protocol.Reinforcement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matan on 18/03/16.
 */
public class Bot implements BotInterface{
    @Override
    public Move getMoveForTurn(int numTurn, int reinforcementCount, World world) {
        Vector2i size = world.getSize();
        List<Cell> myCells = world.getMyCells();
        Cell first = myCells.get(0);
        List<Reinforcement> reinforcements = new ArrayList<>();
        reinforcements.add(new Reinforcement(first.x, first.y, reinforcementCount));
        List<Action> actions = new ArrayList<>();
        return new Move(reinforcements, actions);
    }
}
