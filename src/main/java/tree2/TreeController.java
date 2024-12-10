package tree2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;


public class TreeController {

    public static void main(String[] args) throws JsonProcessingException {
        List<TreeNode> nodes1 = new ArrayList<>();
        nodes1.add(new TreeNode(1L, "Root1", -1L, "Value1-1", "Value2-1", "Value3-1"));
        nodes1.add(new TreeNode(2L, "Child1-1", 1L, "Value1-2", "Value2-2", "Value3-2"));
        nodes1.add(new TreeNode(3L, "Child1-2", 1L, "Value1-3", "Value2-3", "Value3-3"));
        nodes1.add(new TreeNode(4L, "Child1-1-1", 2L, "Value1-4", "Value2-4", "Value3-4"));
        nodes1.add(new TreeNode(5L, "Child1-1-1-1", 4L, "Value1-5", "Value2-5", "Value3-5"));

        List<TreeNode> nodes2 = new ArrayList<>();
        nodes2.add(new TreeNode(6L, "Root2", -1L, "Value1-6", "Value2-6", "Value3-6"));
        nodes2.add(new TreeNode(7L, "Child2-1", 6L, "Value1-7", "Value2-7", "Value3-7"));
        nodes2.add(new TreeNode(8L, "Child2-2", 6L, "Value1-8", "Value2-8", "Value3-8"));
        nodes2.add(new TreeNode(9L, "Child2-1-1", 7L, "Value1-9", "Value2-9", "Value3-9"));
        nodes2.add(new TreeNode(10L, "Child2-1-1-1", 9L, "Value1-10", "Value2-10", "Value3-10"));

        List<List<TreeNode>> dataSource = new ArrayList<>();
        dataSource.add(nodes1);
        dataSource.add(nodes2);

        List<TreeNode> roots = new ArrayList<>();
        for (List<TreeNode> nodes : dataSource) {
            TreeNode root = TreeBuilder.buildTree(nodes);
            roots.add(root);
        }
      /*  List<List<TreeNode>> treeNodeList = TreeConverter.convertToColumns(roots);*/

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(roots);
        System.out.println(jsonString);

    }


}