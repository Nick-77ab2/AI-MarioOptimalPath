package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class MoveForwardJump implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        c.right();
        c.jump();
        //System.out.println("Jumping Forwards");
        return true;
    }
}
