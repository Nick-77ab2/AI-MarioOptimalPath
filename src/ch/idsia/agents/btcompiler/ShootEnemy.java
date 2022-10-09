package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

public class ShootEnemy implements Task {
    @Override
    public boolean run(BehaviorTreeAgent c) {
        c.shoot();
        //System.out.println("Shooting Enemy");
        return true;
    }
}
