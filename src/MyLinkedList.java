/**
 * Doubly linked list implementation of the {@code MyList} interface.
 * {@code MyLinkedList} implementation has head (first element) and last element.
 * It is necessary for fast place elements to list (except for placing the item in the specified location).
 * Each element of the list has links to the previous element(except first elements)
 * and to the next element(except last elements).
 * @author Nikita Egorov
 * @param <E> the type of elements held in this collection
 */
public class MyLinkedList<E> implements MyList<E> {

    /**
     * First element of this list.
     */
    private static Node head;

    /**
     * Last element of this list.
     */
    private static Node last;

    /**
     * Numbers of elements in the list.
     */
    private static int size;

    /**
     * Return Numbers of elements in the list.
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *  Places given an element to the end of this list.
     * @param e element which needed to be place.
     * @return {@code true} if the given element was successfully placed.
     */
    @Override
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    /**
     * Remove given element from the list. If the element is present.
     * @param o element which needed to be removed.
     * @return {@code true} if the given element was successfully removed.
     * @throws MyNoSuchElementException if specified element is not present
     */
    @Override
    public boolean remove(E o) {
        if (o != null) {
            for (Node<E> i = head; i != null; i = i.nextNode) {
                if (o.equals(i.value)) {
                    destroy(i);
                    return true;
                }
            }
            throw new MyNoSuchElementException();
        }
        return false;
    }

    /**
     * Remove specified elements from the list and change links.
     * @param e element that to be removed.
     * @return element that was removed.
     */
    private E destroy(Node<E> e) {
        Node<E> prev = e.prevNode;
        Node<E> next = e.nextNode;
        E value = e.value;

        if (prev == null) {
            head = next;
        } else {
            prev.nextNode = next;
            e.prevNode = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prevNode = prev;
            e.nextNode = null;
        }

        e.value = null;
        size--;
        return value;

    }

    /**
     * Clear the list from all elements.
     */
    @Override
    public void clear() {
        Node<E> node = head;

        for (int i = 0; i < size; i++) {
            Node<E> next = node.nextNode;
            node.value = null;
            node.prevNode = null;
            node.nextNode = null;
            node = next;
        }
        size = 0;
        head = null;
        last = null;


    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of a needed element of the list.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range
     *              ({@code index < 0 || index >= size()}).
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        return node(index).value;
    }

    /**
     * Sort the list by specified order which specified by user.
     * @param c {@code MyComparator} interface which specify order in the list.
     * @see MyComparator
     * @throws MyNoSuchElementException if the list is empty.
     */
    @Override
    public void sort(MyComparator<? super E> c) {
        if (head == null || last == null) {
            throw new MyNoSuchElementException("LinkedList is empty");
        }

        for (Node<E> i = head; i.nextNode != null; i = i.nextNode) {
            Node<E> minNode = i;
            E min = (E) i.value;
            for (Node<E> j = i.nextNode; j != null; j = j.nextNode) {
                if (c.compare(min, j.value) > 0) {
                    min = j.value;
                    minNode = j;
                }
            }
            E temp = i.value;
            i.value = min;
            minNode.value = temp;
        }
    }

    /**
     * Replaces the element at the specified position in this list into the
     * specified element.
     * @param index index of the element whose value needs to be changed.
     * @param element new value of the element.
     *  @throws IndexOutOfBoundsException if the index is out of range
     * ({@code index < 0 || index >= size()}).
     */
    @Override
    public void set(int index, E element) {
        checkIndex(index);
        Node<E> node = node(index);
        node.value = element;
    }

    /**
     * Remove given element from the list. If the element is present.
     * @param index index of the element of this list whose needs to be removed.
     * @return value of a removed element of this list.
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();

        } else if (index == size) {
            return removeLast();
        } else {
            return destroy(node(index));
        }
    }

    /**
     * Remove the last element of this list.
     * @return Old value of the old last element.
     * @throws MyNoSuchElementException if there is no last element in this list.
     */
    private E removeLast() {
        Node<E> node = last;
        if (node == null) {
            throw new MyNoSuchElementException("There is no last element.");
        }
        E value = node.value;
        Node<E> prev = node.prevNode;


        node.value = null;
        node.prevNode = null;
        last = prev;

        if (last == null) {
            head = null;
        } else {
            prev.nextNode = null;
        }
        return value;
    }

    /**
     * Remove the first element of this list.
     * @return Old value of the old first element.
     * @throws MyNoSuchElementException if there is no first element in this list.
     */
    private E removeFirst() {
        Node<E> node = head;
        E value = node.value;
        //Node<E> prev = node.prevNode;
        Node<E> next = node.nextNode;

        if (node == null) {
            throw new MyNoSuchElementException();
        }
        node.value = null;
        node.nextNode = null;
        head = next;

        if (next == null) {
            last = null;
        } else {
            next.prevNode = null;
        }
        return value;
    }

    /**
     * Places given the element to specified place of this list.
     * @param index index of the place where the element needs to be placed.
     * @param element element that to be placed.
     */
    @Override
    public void add(int index, E element) {
        checkIndex(index);
        if (index == size) {
            linkLast(element);
        } else if (index == 0) {
            linkFirst(element);
        } else {
            Node<E> n = node(index);
            Node<E> prev = n.prevNode;
            Node<E> newNode = new Node<>(element, prev, n);
            prev.nextNode = newNode;
            n.prevNode = newNode;
            size++;
        }
    }

    /**
     * link the specified element as the head of this list.
     * @param e element that will be head of this list.
     */
    private void linkFirst(E e) {
        Node<E> n = head;
        Node<E> node = new Node<>(e, null, n);
        head = node;
        if (n == null)
            last = node;
        else
            n.prevNode = node;
    }
    /**
     * link the specified element as the last of this list.
     * @param element element that will be last of this list.
     */
    private void linkLast(E element) {
        Node<E> oldLast = last;
        Node<E> newLast = new Node<>(element, oldLast, null);
        last = newLast;
        if (oldLast == null) {
            head = newLast;
        } else {
            oldLast.nextNode = newLast;
        }
        size++;


    }


    private Node<E> node(int index) {
        if (index > (size / 2)) {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prevNode;
            }
            return node;
        } else {
            Node<E> node = head;
            for (int i = 0; i < index; i++) {
                node = node.nextNode;
            }
            return node;
        }
    }

    /**
     *Checks if the {@code index} is within the bounds of the range from
     * {@code 0} (inclusive) to {@code size} (exclusive).
     * @param index the index.
     * @return {@code true} if it is within the bounds of the range.
     * @throws IndexOutOfBoundsException if index out of range({@code index >= size}, {@code index < 0}).
     */
    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", " + "Size: " + size);
        }
        return true;
    }

    /**
     * Return value of head.
     * @return value if head.
     */

    public E getHead(){
       return (E) head.value;
    }

    /**
     * Return value of last element.
     * @return value of last element.
     */
    public E getLast(){
        return (E) last.value;
    }

    private static class Node<E> {
        private E value;
        private Node<E> prevNode;
        private Node<E> nextNode;

        Node(E value, Node<E> prevNode, Node<E> nextNode) {
            this.value = value;
            this.prevNode = prevNode;
            this.nextNode = nextNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node<E> i = head; i != null; i = i.nextNode) {
            stringBuilder.append(i.value).append("\n");
        }
        return stringBuilder.toString();
    }
}
