package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;

public class CanGrabCoin implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        return c.CanGrabCoin();
    }
}
