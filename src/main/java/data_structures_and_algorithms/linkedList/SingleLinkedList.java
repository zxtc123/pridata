package data_structures_and_algorithms.linkedList;

/**
 * @Author: zhaoxin
 * @Date: 2020/9/10 10:43
 * 单向链表
 */
public class SingleLinkedList {

    private Node head;//表头
    private int size;//节点个数

    private class Node{//链表内的节点
        private Object data;
        private Node next;

        public Node(Object data){
            this.data = data;
        }
    }

    //表头添加元素
    public void addHead(Object value){
        Node newNode = new Node(value);
        if(head != null){//链表不为空
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    //表头删除元素
    public Object deleteHead(){
        if(size == 0)return null;
        Node deleteNode = head;
        head = deleteNode.next;
        size--;
        return deleteNode.data;
    }

    //查找指定节点
    public Node find(Object obj){
        if(head == null)return null;
        int findSize = size;
        Node current = head;
        while (findSize > 0 && obj != current.data){
            current = current.next;
            findSize--;
        }
        if(obj == current.data)
            return current;
        else
            return null;
    }

    //删除指定元素
    public Node delete(Object obj){
        if(head == null) return null;
        //需要找到被删除节点和它之前的节点
        Node pre = head;
        Node current = head;
        while (obj != current.data){
            if(current.next == null){//后面没有元素了
                return null;
            }else{
                pre = current;
                current = current.next;
            }
        }

        if(current == head){
            head = null;
        }else{
            pre.next = current.next;
        }
        size--;
        return current;
    }

    //链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //打印链表中的元素
    public void display(){
        if(head == null){
            System.out.println("[]");
        }else{
            int findSize = size;
            Node current = head;
            while (findSize > 0){
                if(findSize == size){
                    System.out.print("[" + current.data);
                }else if(findSize == 1){
                    System.out.println("," + current.data + "]");
                }else{
                    System.out.print("," + current.data);
                }
                current = current.next;
                findSize--;
            }
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleList = new SingleLinkedList();
        singleList.addHead("A");
        singleList.addHead("B");
        singleList.addHead("C");
        singleList.addHead("D");
        singleList.addHead("E");
        singleList.addHead("F");
        singleList.addHead("G");
        //打印当前链表信息
        singleList.display();
        //删除C
        singleList.delete("C");
        singleList.display();
        //查找B
        System.out.println(singleList.find("B"));
    }










}
