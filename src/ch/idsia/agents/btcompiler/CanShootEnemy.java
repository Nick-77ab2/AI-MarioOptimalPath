package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class CanShootEnemy implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        return c.isEnemyInRange();
    }
}
