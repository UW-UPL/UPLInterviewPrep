import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class MySet {
    public MyNode root;

    public MySet() {
        root = null;
    }

    public void add(int data) {
        if (root == null) {
            root = new MyNode(data);
        } else {
            root.add(data);
        }
    }

    // The following set operations all return new MySet instances

    public MySet union(MySet otherSet) {
        MySet newSet = new MySet();
        Iterator<Integer> myIt = iterator();
        while (myIt.hasNext()) {
            newSet.add(myIt.next());
        }

        Iterator<Integer> otherIt = otherSet.iterator();
        while (otherIt.hasNext()) {
            newSet.add(otherIt.next());
        }

        return newSet;
    }

    public MySet intersection(MySet otherSet) {
        MySet newSet = new MySet();
        Iterator<Integer> myIt = iterator();
        Iterator<Integer> otherIt = otherSet.iterator();

        boolean myEmpty = !myIt.hasNext();
        boolean otherEmpty = !otherIt.hasNext();

        if (myEmpty || otherEmpty) {
            return newSet;
        }

        int myVal = myIt.next();
        int otherVal = otherIt.next();
        while (myIt.hasNext() && otherIt.hasNext()) {
            if (myVal == otherVal) {
                newSet.add(myVal);
                myVal = myIt.next();
                otherVal = otherIt.next();
            } else if (myVal < otherVal) {
                while (myVal < otherVal) {
                    myVal = myIt.next();
                }
            } else {
                while (otherVal < myVal) {
                    otherVal = otherIt.next();
                }
            }
        }

        if (myVal == otherVal) {
            newSet.add(myVal);
        }

        return newSet;
    }

    public MySet difference(MySet otherSet) {
        MySet newSet = new MySet();
        Iterator<Integer> myIt = iterator();
        Iterator<Integer> otherIt = otherSet.iterator();

        boolean myEmpty = !myIt.hasNext();
        boolean otherEmpty = !otherIt.hasNext();

        if (myEmpty) {
            return newSet;
        } else if (otherEmpty) {
            while (myIt.hasNext()) {
                newSet.add(myIt.next());
            }
            return newSet;
        }

        Integer myVal = myIt.next();
        Integer otherVal = otherIt.next();
        while (myIt.hasNext() && otherIt.hasNext()) {
            if (myVal.equals(otherVal)) {
                myVal = myIt.next();
                otherVal = otherIt.next();
            } else if (myVal < otherVal) {
                while (myVal < otherVal) {
                    newSet.add(myVal);
                    myVal = myIt.next();
                }
            } else {
                while (otherVal < myVal) {
                    otherVal = otherIt.next();
                }
            }
        }

        if (!myVal.equals(otherVal)) {
            newSet.add(myVal);
        }

        while (myIt.hasNext()) {
            newSet.add(myIt.next());
        }

        return newSet;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        Iterator<Integer> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().intValue());
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public Iterator<Integer> iterator() {
        return new MyIterator(root);
    }

    // private classes

    private class MyNode {
        public int data;
        public MyNode left;
        public MyNode right;

        public MyNode(int myData) {
            data = myData;
            left = null;
            right = null;
        }

        public void add(int newData) {
            if (newData < data) {
                if (left == null) {
                    left = new MyNode(newData);
                } else {
                    left.add(newData);
                }
            } else if (newData > data) {
                if (right == null) {
                    right = new MyNode(newData);
                } else {
                    right.add(newData);
                }
            }
        }
    }

    // define an in-order iterator over the set
    private class MyIterator implements Iterator<Integer> {
        private List<MyNode> stack;
        private MyNode current;

        private MyIterator(MyNode root) {
            stack = new ArrayList<>();
            if (root != null) {
                stack.add(root);
                while (root.left != null) {
                    root = root.left;
                    stack.add(0, root);
                }
            }
            current = null;
        }

        public boolean hasNext() {
            return stack.size() > 0 || current != null;
        }

        public Integer next() {
            while (stack.size() > 0 || current != null) {
                if (current != null) {
                    stack.add(0, current);
                    current = current.left;
                } else {
                    current = stack.get(0);
                    stack.remove(0);
                    MyNode retVal = current;
                    current = current.right;
                    return retVal.data;
                }
            }
            throw new NoSuchElementException();
        }
    }

    public static void main(String[] args) {
        MySet a = new MySet();
        a.add(1);
        a.add(2);
        a.add(3);
        System.out.println("Set A: " + a.toString());

        MySet b = new MySet();
        b.add(2);
        b.add(3);
        System.out.println("Set B: " + b.toString());

        System.out.println("Union: " + a.union(b).toString());

        System.out.println("Intersection: " + a.intersection(b).toString());

        System.out.println("Difference (A - B): " + a.difference(b).toString());
        System.out.println("Difference (B - A): " + b.difference(a).toString());
    }
}
