package tree2;


import java.util.ArrayList;
import java.util.List;

public class TreeConverter {

    public static List<List<TreeNode>> convertToColumns(List<TreeNode> roots) {
        List<List<TreeNode>> columns = new ArrayList<>();

        for (TreeNode root : roots) {
            List<TreeNode> column = new ArrayList<>();
            traverseTree(root, column);
            columns.add(column);
        }

        return columns;
    }

    private static void traverseTree(TreeNode node, List<TreeNode> column) {
        if (node != null) {
            column.add(node);
            for (TreeNode child : node.getChildren()) {
                traverseTree(child, column);
            }
        }
    }
}