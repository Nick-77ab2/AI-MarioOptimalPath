package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

import java.util.ArrayList;

//Create the Sequence
public class Sequence implements Composite {
    private ArrayList<Task> children;

    public Sequence() {
        children = new ArrayList<Task>();
    }

    @Override
    public boolean run(BehaviorTreeAgent x) {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).run(x) != true) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Task> getChildren() {
        return children;
    }

    public void setChildren(Task t) {
        children.add(t);
    }
}
