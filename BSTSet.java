
package csci235;

import java.util.ArrayList;
import java.util.List;

public class BSTSet<T extends Comparable<T> > implements Set<T>
{
    private Node root;
    private int size;

    public BSTSet() {
        root = null;
        size = 0;
    }

    private class Node {
        private T value;
        private Node left;
        private Node right;

        private Node(T val) {
            value = val;
            left = null;
            right = null;
        }

        private boolean add( T t ) {

            Node curNode = root;
            Node prevNode = null;
            int direction = 0; //0 denotes right, 1 denotes left

            while(curNode != null) {
                prevNode = curNode;
                if(curNode.value.compareTo(t) < 0) {
                    curNode = curNode.right;
                    direction = 0;
                } else if(curNode.value.compareTo(t) > 0) {
                    curNode = curNode.left;
                    direction = 1;
                } else {
                    return false;
                }
            }

            curNode = new Node(t);
            if(direction == 0) {
                prevNode.right = curNode;
            } else {
                prevNode.left = curNode;
            }
            return true;
        }

        private boolean contains( T t ) {

            Node curNode = root;

            while(curNode != null) {
                if(curNode.value.compareTo(t) < 0) {
                    curNode = curNode.right;
                } else if(curNode.value.compareTo(t) > 0) {
                    curNode = curNode.left;
                } else {
                    return true;
                }
            }
            return false;
        }

        private void addtoList( List<T> lst ) {

            if(left != null) {
                left.addtoList(lst);
            }

            lst.add(this.value);

            if(right != null) {
                right.addtoList(lst);
            }
        }

        private void appendtoString( StringBuilder str ) {

            if(left != null) {
                left.appendtoString(str);
            }

            str.append(this.value).append(" ");

            if(right != null) {
                right.appendtoString(str);
            }
        }
    }

    public boolean add( T t ) {
        if(root == null) {
            root = new Node(t);
            size++;
            return true;
        }
        if(root.add(t)) {
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean contains( T t ) {
        if(root == null) {
            return false;
        }
        return root.contains(t);
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();

        if(root != null) {
            root.addtoList(list);
        }
        return list;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{");
        if(root != null) {
            root.appendtoString(str);
        }
        str.append("}");
        return str.toString();
    }

    public int getSize(){
        return size;
    }

    public void clear() {
        root = null;
        size = 0;
    }
}
