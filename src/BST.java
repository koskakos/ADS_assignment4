import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<Map.Entry<K, V>> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private int size = 1;
        private Node left, right;
        private Node parent;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(K key, V val) {
        Node head = root;

        if (root == null) {
            root = new Node(key, val);
            return;
        }

        while (true) {
            head.size++;
            int comp = key.compareTo(head.key);
            if(comp == 0) {
                head.val = val;
                return;
            }
            if (comp > 0) {
                if (head.right == null) {
                    head.right = new Node(key, val);
                    head.right.parent = head;
                    return;
                }
                head = head.right;
            } else if (comp < 0) {
                if (head.left == null) {
                    head.left = new Node(key, val);
                    head.left.parent = head;
                    return;
                }
                head = head.left;
            }
        }
    }

    public V get(K key) {
        if (root == null) return null;
        Node head = root;
        while (head != null) {
            if (head.key == key) return head.val;

            int comp = key.compareTo(head.key);
            if (comp > 0) {
                head = head.right;
            } else if (comp < 0) {
                head = head.left;
            }
        }
        return null;
    }

    private Node extractMin(Node node) {
        if (node == null) throw new NullPointerException();
        while (node.left.left != null) {
            node = node.left;
        }
        Node temp = node.left;
        node.left = null;
        return temp;
    }

    private Node extractMax(Node node) {
        if (node == null) throw new NullPointerException();
        while (node.right.right != null) {
            node = node.right;
        }
        Node temp = node.right;
        node.right = null;
        return temp;
    }

    public void delete(K key) {
        Node head = root;

        if (head.left == null && head.right == null && head.key.compareTo(key) == 0) {
            root = null;
            return;
        }

        while (head != null) {
            int comp = key.compareTo(head.key);
            if (comp == 0) {
                if (head.left == null && head.right == null) {
                    head = head.parent;
                    if (key.compareTo(head.left.key) == 0) head.left = null;
                    else head.right = null;
                    return;
                }
                break;
            }
            if (comp > 0) {
                head = head.right;
            } else if (comp < 0) {
                head = head.left;
            }
        }
        if (head == null) return;

        Node temp;
        if (head.left != null && head.right != null) {
            if (head.left.size <= head.right.size) {
                temp = extractMax(head);
            } else {
                temp = extractMin(head);
            }
        } else if (head.left == null) {
            temp = extractMax(head);
        } else {
            temp = extractMin(head);
        }
        head.key = temp.key;
        head.val = temp.val;

    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Map.Entry<K, V>> {
        private Queue<Map.Entry<K, V>> q = new LinkedList<>();

        public MyIterator() {
            inorderTraversal(root, q);
        }

        public void inorderTraversal(Node head, Queue<Map.Entry<K, V>> q) {
            if (head == null) return;
            inorderTraversal(head.left, q);
            q.add(new AbstractMap.SimpleEntry<>(head.key, head.val));
            inorderTraversal(head.right, q);
        }

        @Override
        public boolean hasNext() {
            return !q.isEmpty();
        }

        @Override
        public Map.Entry<K, V> next() {
            return q.poll();
        }
    }

}
