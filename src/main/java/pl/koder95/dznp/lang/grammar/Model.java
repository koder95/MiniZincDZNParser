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
        // Creating list of AssignItem
        List<AssignItem> assignItemList = new ArrayList<>();
        String sign = Stacks.getType(stack, String.class); // - get top element if it is String
        // Checking that sign is ";" sign. If it is not, throws exception;
        if (sign != null && !sign.equals(";")) throw new IOException();
        while (!stack.empty()) {
            addAssignItemFromStack(stack, assignItemList);
            if (!stack.empty()) {
                sign = Stacks.getType(stack, String.class);
                if (sign == null || !sign.equals(";")) break;
            }
        }
        return new Model(assignItemList);
    }

    private static void addAssignItemFromStack(Stack<Object> stack, List<AssignItem> assignItemList) throws IOException {
        AssignItem assignItem = Stacks.getType(stack, AssignItem.class);
        if (assignItem == null)
            assignItem = AssignItem.from(stack);
        assignItemList.add(assignItem);
    }
}
