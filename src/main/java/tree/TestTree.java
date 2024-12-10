package tree;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class TestTree {
    public static void main(String[] args) throws JsonProcessingException {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(new TreeNode(1L, -1L, "根节点A"));
        nodes.add(new TreeNode(2L, -1L, "根节点B"));
        nodes.add(new TreeNode(3L, 1L, "子节点A1"));
        nodes.add(new TreeNode(4L, 1L, "子节点A2"));
        nodes.add(new TreeNode(5L, 3L, "子节点A1.1"));
        nodes.add(new TreeNode(6L, 2L, "子节点B1"));
        nodes.add(new TreeNode(7L, 6L, "子节点B1.1"));

        TreeBuilder treeBuilder = new TreeBuilder();
        List<TreeNode> tree = treeBuilder.buildTree(nodes);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(tree);
        System.out.println(jsonString);
    }


}