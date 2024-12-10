package tree2;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {

    public static TreeNode buildTree(List<TreeNode> nodes) {
        Map<Long, TreeNode> nodeMap = new HashMap<>();
        TreeNode root = new TreeNode();

        // 将所有节点放入Map中
        for (TreeNode node : nodes) {
            nodeMap.put(node.getId(), node);
        }

        // 构建树结构
        for (TreeNode node : nodes) {
            if (node.getParentId().compareTo(-1L)==0) {
                root = node;
            } else {
                TreeNode parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.addChild(node);
                }
            }
        }

        return root;
    }
}
