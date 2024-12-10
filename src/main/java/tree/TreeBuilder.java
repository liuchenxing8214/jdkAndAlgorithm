package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeBuilder {
    public List<TreeNode> buildTree(List<TreeNode> nodes) {
        Map<Long, TreeNode> nodeMap = new HashMap<>();
        List<TreeNode> rootNodes = new ArrayList<>();

        // 将所有节点放入map中
        for (TreeNode node : nodes) {
            nodeMap.put(node.getId(), node);
        }
        // 构建树形结构
        for (TreeNode node : nodes) {
            if (node.getParentId().compareTo(-1L)==0) { // 根节点
                rootNodes.add(node);
            } else {
                TreeNode parent = nodeMap.get(node.getParentId());
                if (parent != null) {
                    parent.getChildren().add(node);
                }
            }
        }
        return rootNodes; // 返回根节点列表
    }
}