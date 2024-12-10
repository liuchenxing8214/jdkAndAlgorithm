package tree2;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TreeNode {
    private Long id;
    private String name;
    private Long parentId;
    private String value1;
    private String value2;
    private String value3;
    private List<TreeNode> children = new ArrayList<>();

    // Constructors, getters and setters
    public TreeNode() {}

    public TreeNode(Long id, String name, Long parentId, String value1, String value2, String value3) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }
}
