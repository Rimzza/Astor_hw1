/**
 * Implementation of the {@code MyList} interface. {@code MyArrayList} is a dynamic array.
 * Each {@code MyArrayList} instance has a capacity. As elements are added to a MyArrayList, its capacity grows automatically.
 * Computation of the capacity specifies in the {@link MyArrayList#grow()} method.
 * Default capacity equals 10. You can create {@code MyArrayList} instance with your own capacity.
 * @param <E> the type of elements in this list.
 *  @author Nikita Egorov
 */
public class MyArrayList<E> implements MyList<E> {

    /**
     * Default capacity.
     */
    private static final int DEFAULT_CAPACITY = 3;

    /**
     * Array which elements of the MyArrayList are stored
     */
    private Object[] elements;

    /**
     * Numbers of elements in the list.
     */
    private int size;

    /**
     * Numbers of elements that MyArrayList is able to store.
     * Can be specified by {@code MyArrayList(int capacity)}.
     */
    private int capacity = DEFAULT_CAPACITY;

    /**
     * Constructs an empty list with default capacity.
     */
    public MyArrayList() {
        this.elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     * @param capacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *               is negative.
     */
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.elements = new Object[capacity];
            this.capacity = capacity;
        } else if (capacity == 0) {
            this.elements = new Object[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal capacity: " + capacity);
        }
    }

    /**
     * Returns size (numbers of elements in this list).
     * @return return size.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *  Places given an element to the end of this list.
     * @param e element which needed to be place.
     * @return {@code true} if a given element was successfully placed.
     */

    public boolean add(E e) {
        add(e, elements, size);
        return true;
    }

    /**
     * Places given an element to the end of this list.
     * @param e element that to be needed place.
     * @param array array of the elements.
     * @param size numbers of elements in this list
     */

    private void add(E e, Object[] array, int size) {
        if (size == array.length) {
            elements = grow();
        }
        elements[this.size++] = e;

    }

    /**
     * Remove given element from the list. If the element is present.
     * @param o element which needed to be removed.
     * @return {@code true} if a given element was successfully removed.
     */
    @Override
    public boolean remove(E o) {
        for (int i = 0; i < elements.length; i++){
            if (elements[i].equals(o)){
                int numMoved = size - i;
                System.arraycopy(elements, i + 1, elements, i, numMoved);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Clear the list from all elements.
     */
    @Override
    public void clear() {
        if (size == 0)
            return;
        elements = new Object[capacity];
        size = 0;
    }

    /**
     * Increase the capacity of the list and copy all elements from old list to list with increased capacity.
     * Formula for new capacity : {@code (oldCapacity * 3) / 2 + 1}
     * @return increased list with elements from old lists.
     */
    private Object[] grow() {
        Object[] oldArray = elements;
        int oldCapacity = elements.length;
        capacity = (oldCapacity * 3) / 2 + 1;
        elements = new Object[capacity];
        System.arraycopy(oldArray, 0, elements, 0, size);
        return elements;
    }

    /**
     * Returns the element at the specified position in this list.
     * @param index index of a needed element of the list.
     * @return the element at the specified position in this list.
     *  @throws IndexOutOfBoundsException if the index is out of range
     *  ({@code index < 0 || index >= size()}).
     */
    @Override
    public E get(int index) {
        checkIndex(index);

        return (E) elements[index];
    }

    /**
     * Sort the list by specified order which specified by user.
     * @param c {@code MyComparator} interface which specify order in the list.
     * @see MyComparator
     */
    public void sort(MyComparator<? super E> c) {
        if (size <= 1)
            return;
        myQuickSort(c,0,size - 1);
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
        elements[index] = element;
    }

    /**
     * Sort the list by QuickSort algorithm.
     * @param c implementation of {@code MyComparator } interface
     * @param low start of searching. Better use index of the first element of this list.
     * @param high end of searching. Better use index of the last element of this list.
     */
    private void myQuickSort(MyComparator<? super E> c, int low, int high){
        int mid = low + (high - low) / 2;

        int i = low;
        int j = high;

        while (i <= j) {

            while (c.compare((E) elements[i], (E) elements[mid]) < 0) {
                i++;
            }
            while (c.compare((E) elements[j], (E) elements[mid]) > 0) {
                j--;
            }

            if (i <= j) {
                Object temp = elements[i];
                elements[i] = elements[j];
                elements[j] = temp;
                i++;
                j--;
            }
        }

            if (low < j){
                myQuickSort(c,low,j);
            }

            if (high > i) {
                myQuickSort(c,i,high);
            }
    }

    /**
     * Remove given element from the list. If the element is present.
     * @param index index of the element of this list whose needs to be removed.
     * @return value of a removed element of this list.
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size  - index - 1;
        System.arraycopy(elements, index + 1, elements, index, numMoved);
        elements[--size] = null;
        return oldValue;
    }

    /**
     * Places given the element to specified place of this list.
     * @param index index of the place where the element needs to be placed.
     * @param element element that to be placed.
     * @throws IndexOutOfBoundsException if the index is out of range {@code (index < 0 || index >= size())}
     */
    @Override
    public void add(int index, E element) {
        if (checkIndex(index)) {
            if (size + 1 == elements.length) {
                elements = grow();
            }
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;

    }

    /**
     *Checks if the {@code index} is within the bounds of the range from
     * {@code 0} (inclusive) to {@code size} (exclusive).
     * @param index the index.
     * @return {@code true} if it is within the bounds of the range.
     * @throws IndexOutOfBoundsException if
     */
    private boolean checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", " + "Size: " + size);
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element : elements){
            if (element != null)
                stringBuilder.append(element).append("\n");
        }
        return stringBuilder.toString();
    }
}
