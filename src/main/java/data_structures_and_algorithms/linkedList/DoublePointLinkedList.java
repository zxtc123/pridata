package data_structures_and_algorithms.linkedList;

/**
 * @Auther zhaoxin
 * @Date 2020/9/19 12:08
 * 双端链表
 */
public class DoublePointLinkedList {

    private int size;//节点个数
    private Node head;//头节点
    private Node tail;//尾节点

    private class Node {//链表内的节点
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    //表头添加元素
    public void addHead(Object value) {
        Node newNode = new Node(value);
        if (size == 0) {//没有元素则首尾都添加该元素
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    //表尾添加元素
    public void addTail(Object value) {
        Node newNode = new Node(value);
        if (size == 0) {//没有元素则首尾都添加该元素
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    //表头删除元素
    public boolean deleteHead() {
        if (size == 0) return false;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            Node deleteNode = head;
            head = deleteNode.next;
        }
        size--;
        return true;
    }

    //链表是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //打印链表中的元素
    public void display() {
        if (head == null) {
            System.out.println("[]");
        } else {
            int findSize = size;
            Node current = head;
            while (findSize > 0) {
                if (findSize == size) {
                    System.out.print("[" + current.data);
                } else if (findSize == 1) {
                    System.out.println("," + current.data + "]");
                } else {
                    System.out.print("," + current.data);
                }
                current = current.next;
                findSize--;
            }
        }
    }

    public static void main(String[] args) {
        DoublePointLinkedList dList = new DoublePointLinkedList();
        dList.addHead(1);
        dList.addHead(2);
        dList.addHead(3);
        dList.display();
        dList.addTail(4);
        dList.addTail(5);
        dList.addTail(6);
        dList.display();

        dList.deleteHead();
        dList.display();

    }

}
