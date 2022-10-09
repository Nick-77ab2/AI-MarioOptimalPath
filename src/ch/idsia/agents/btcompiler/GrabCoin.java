package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class GrabCoin implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        c.right();
        if(c.coinAbove){
            c.jump();
        }
        return true;
    }
}
