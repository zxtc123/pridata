package leetcode.solution.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * 
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（val = 1），第二个节点值为 2（val = 2），以此类推。
 * 该图在测试用例中使用邻接列表表示。
邻接列表 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * @author Administrator
 *
 */
public class CloneGraph {
	Set<Node>set;
    public Node cloneGraph(Node node) {
    	if(node==null)return null;
    	set = new HashSet<>();
    	Node root = new Node(1);
    	
    	
    	
        return null;
    }
    
    private void DFS(Node node){
    	if(set.contains(node))return;
    	set.add(new Node(node.val));
    	List<Node> neighbors = node.neighbors;
    	for(Node nb : neighbors){
    		if(!set.contains(node)){
    			DFS(nb);
    		}
    	}
    }
    
    private HashMap <Node, Node> visited = new HashMap <> ();
    public Node cloneGraph2(Node node) {
        if (node == null) {
            return node;
        }

        // 如果该节点已经被访问过了，则直接从哈希表中取出对应的克隆节点返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 克隆节点，注意到为了深拷贝我们不会克隆它的邻居的列表
        Node cloneNode = new Node(node.val, new ArrayList<>());
        // 哈希表存储
        visited.put(node, cloneNode);

        // 遍历该节点的邻居并更新克隆节点的邻居列表
        for (Node neighbor: node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }
        return cloneNode;
    }
}
