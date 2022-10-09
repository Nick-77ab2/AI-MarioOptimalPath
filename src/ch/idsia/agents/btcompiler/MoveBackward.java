package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class MoveBackward implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        c.left();
        //System.out.println("Moving Backwards");
        return true;
    }
}
