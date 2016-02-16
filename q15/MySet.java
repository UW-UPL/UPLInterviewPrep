import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class MySet<T> {
    public MyNode<T> root;
    public Comparator<T> cmp;
    public MySet(Comparator<T> myCmp) {
        root = null;
        cmp = myCmp;
    }

    public void add(T data) {
        if (root == null) {
            root = new MyNode<>(data);
        } else {
            root.add(data, cmp);
        }
    }

    // The following set operations all return new MySet instances

    public MySet union(MySet<T> otherSet) {
        MySet<T> newSet = new MySet<>(cmp);
        Iterator<T> myIt = iterator();
        while (myIt.hasNext()) {
            newSet.add(myIt.next());
        }

        Iterator<T> otherIt = otherSet.iterator();
        while (otherIt.hasNext()) {
            newSet.add(otherIt.next());
        }

        return newSet;
    }

    public MySet intersection(MySet<T> otherSet) {
        MySet<T> newSet = new MySet<>(cmp);
        Iterator<T> myIt = iterator();
        Iterator<T> otherIt = otherSet.iterator();

        boolean myEmpty = !myIt.hasNext();
        boolean otherEmpty = !otherIt.hasNext();

        if (myEmpty || otherEmpty) {
            return newSet;
        }

        T myVal = myIt.next();
        T otherVal = otherIt.next();
        while (myIt.hasNext() && otherIt.hasNext()) {
            if (myVal == otherVal) {
                newSet.add(myVal);
                myVal = myIt.next();
                otherVal = otherIt.next();
            } else if (cmp.compare(myVal, otherVal) < 0) {
                while (cmp.compare(myVal, otherVal) < 0) {
                    myVal = myIt.next();
                }
            } else {
                while (cmp.compare(myVal, otherVal) > 0) {
                    otherVal = otherIt.next();
                }
            }
        }

        if (myVal == otherVal) {
            newSet.add(myVal);
        }

        return newSet;
    }

    public MySet difference(MySet<T> otherSet) {
        MySet<T> newSet = new MySet<>(cmp);
        Iterator<T> myIt = iterator();
        Iterator<T> otherIt = otherSet.iterator();

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

        T myVal = myIt.next();
        T otherVal = otherIt.next();
        while (myIt.hasNext() && otherIt.hasNext()) {
            if (myVal.equals(otherVal)) {
                myVal = myIt.next();
                otherVal = otherIt.next();
            } else if (cmp.compare(myVal, otherVal) < 0) {
                while (cmp.compare(myVal, otherVal) < 0) {
                    newSet.add(myVal);
                    myVal = myIt.next();
                }
            } else {
                while (cmp.compare(myVal, otherVal) > 0) {
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
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append(" ");
        }
        sb.append("}");
        return sb.toString();
    }

    public Iterator<T> iterator() {
        return new MyIterator<>(root);
    }

    // private classes

    private class MyNode<T1> {
        public T1 data;
        public MyNode<T1> left;
        public MyNode<T1> right;

        public MyNode(T1 myData) {
            data = myData;
            left = null;
            right = null;
        }

        public void add(T1 newData, Comparator<T1> cmp) {
            int c = cmp.compare(data, newData);
            if (c > 0) {
                if (left == null) {
                    left = new MyNode<>(newData);
                } else {
                    left.add(newData, cmp);
                }
            } else if (c < 0) {
                if (right == null) {
                    right = new MyNode<>(newData);
                } else {
                    right.add(newData, cmp);
                }
            }
        }
    }

    // define an in-order iterator over the set
    private class MyIterator<T2> implements Iterator<T2> {
        private List<MyNode<T2>> stack;
        private MyNode<T2> current;

        private MyIterator(MyNode<T2> root) {
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

        public T2 next() {
            while (stack.size() > 0 || current != null) {
                if (current != null) {
                    stack.add(0, current);
                    current = current.left;
                } else {
                    current = stack.get(0);
                    stack.remove(0);
                    MyNode<T2> retVal = current;
                    current = current.right;
                    return retVal.data;
                }
            }
            throw new NoSuchElementException();
        }
    }

    private static class IntComparator implements Comparator<Integer> {
        public int compare(Integer i1, Integer i2) {
            return i1.compareTo(i2);
        }
    }

    private static class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static void testIntSet() {
        IntComparator cmp = new IntComparator();
        MySet<Integer> a = new MySet<>(cmp);
        a.add(1);
        a.add(2);
        a.add(3);
        System.out.println("Set A: " + a.toString());

        MySet<Integer> b = new MySet<>(cmp);
        b.add(2);
        b.add(3);
        System.out.println("Set B: " + b.toString());

        System.out.println("Union: " + a.union(b).toString());

        System.out.println("Intersection: " + a.intersection(b).toString());

        System.out.println("Difference (A - B): " + a.difference(b).toString());
        System.out.println("Difference (B - A): " + b.difference(a).toString());
    }

    public static void testStringSet() {
        StringComparator cmp = new StringComparator();
        MySet<String> a = new MySet<>(cmp);
        a.add("One");
        a.add("Two");
        a.add("Three");
        System.out.println("Set A: " + a.toString());

        MySet<String> b = new MySet<>(cmp);
        b.add("Two");
        b.add("Three");
        System.out.println("Set B: " + b.toString());

        System.out.println("Union: " + a.union(b).toString());

        System.out.println("Intersection: " + a.intersection(b).toString());

        System.out.println("Difference (A - B): " + a.difference(b).toString());
        System.out.println("Difference (B - A): " + b.difference(a).toString());
    }

    public static void main(String[] args) {
        System.out.println("Int sets:\n");
        testIntSet();
        System.out.println("\n\nString sets:\n");
        testStringSet();

    }
}
