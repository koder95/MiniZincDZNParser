package pl.koder95.dznp.lang.grammar;

import pl.koder95.dznp.util.Stacks;

import java.io.IOException;
import java.util.*;

public class Model {

    private final List<AssignItem> assignItemList;

    private Model(List<AssignItem> assignItemList) {
        this.assignItemList = Collections.unmodifiableList(Objects.requireNonNull(assignItemList));
    }

    public List<AssignItem> getAssignItemList() {
        return assignItemList;
    }

    public static Model from(Stack<Object> stack) throws IOException {
        List<AssignItem> assignItemList = new ArrayList<>();
        while (!stack.empty()) {
            String sign = Stacks.getType(stack, String.class);
            if (sign == null || !sign.equals(";")) break;
            addAssignItemFromStack(stack, assignItemList);
        }
        return new Model(assignItemList);
    }

    private static void addAssignItemFromStack(Stack<Object> stack, List<AssignItem> assignItemList) throws IOException {
        AssignItem assignItem = Stacks.getType(stack, AssignItem.class);
        if (assignItem == null) throw new IOException();
        assignItemList.add(assignItem);
    }
}
