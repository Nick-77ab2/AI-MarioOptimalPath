package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class MoveForward implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        c.right();
        //System.out.println("Moving Forwards");
        return true;
    }
}
