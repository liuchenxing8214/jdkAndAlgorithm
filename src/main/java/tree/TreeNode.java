package tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TreeNode {
    private Long id; // 节点ID
    private Long parentId; // 父节点ID
    private String label; // 节点名称
    private List<TreeNode> children; // 子节点列表

    public TreeNode(Long id, Long parentId, String label) {
        this.id = id;
        this.parentId = parentId;
        this.label = label;
        this.children = new ArrayList<>();
    }

}