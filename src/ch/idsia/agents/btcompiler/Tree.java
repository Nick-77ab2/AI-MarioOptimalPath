package ch.idsia.agents.btcompiler;

import ch.idsia.agents.controllers.BehaviorTreeAgent;

import java.util.ArrayList;

//Create the Tree and its default functions.
public class Tree {
    private ArrayList<Task> children;
    BehaviorTreeAgent x;

    public Tree(BehaviorTreeAgent _x) {
        children = new ArrayList<Task>();
        x = _x;
    }

    public void runLeTree() {
        for (int i = 0; i < children.size(); i++) {
            if (children.get(i).run(x) == true) {
                System.out.println("Exited After success");
                break;
            }
        }
    }

    public void addTask(Task tee) {
        children.add(tee);
    }
}
