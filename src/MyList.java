/**
 *  The {@code MyList} interface is my personal attempt to write my own implementation of {@code List} interface.
 *  The {@code MyList} interface provided with all basic methods(e.g {@code add}, {@code remove}, {@code get}, etc.).
 *  Also, the {@code MyList} interface has {@code sort} methods which needed {@code MyComparator} interface implementations.
 *
 * @param <E> the type of elements in this list.
 * @author Nikita Egorov
 * @see MyArrayList
 * @see MyLinkedList
 */

public interface MyList<E>  {
    /**
     *Returns the number of elements in this list.
     * @return numbers of elements in the list.
     */
    int size();

    /**
     *  Places given an element to the end of this list.
     * @param e element which needed to be place.
     * @return {@code true} if the given element was successfully placed.
     */
    boolean add(E e);

    /**
     * Remove given element from the list. If the element is present.
     * @param o element which needed to be removed.
     * @return {@code true} if the given element was successfully removed.
     */
    boolean remove(E o);

    /**
     * Clear the list from all elements.
     */
    void clear();

    boolean equals(Object o);

    int hashCode();

    /**
     * Returns the element at the specified position in this list.
     * @param index index of a needed element of the list.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range
     *              ({@code index < 0 || index >= size()}).
     */
    E get(int index);

    /**
     * Sort the list by specified order which specified by user.
     * @param c {@code MyComparator} interface which specify order in the list.
     * @see MyComparator
     */
    void sort(MyComparator<? super E> c);

    /**
     * Replaces the element at the specified position in this list into the
     * specified element.
     * @param index index of the element whose value needs to be changed.
     * @param element new value of the element.
     *  @throws IndexOutOfBoundsException if the index is out of range
     * ({@code index < 0 || index >= size()}).
     */
    void set(int index, E element);

    /**
     * Remove given element from the list. If the element is present.
     * @param index index of the element of this list whose needs to be removed.
     * @return value of a removed element of this list.
     */
     E remove(int index);
     boolean isEmpty();

    /**
     * Places given the element to specified place of this list.
     * @param index index of the place where the element needs to be placed.
     * @param element element that to be placed.
     */
     void add(int index, E element);

    /**
     * Return the first element of this list.
     * @return the first element of this list.
     * @throws MyNoSuchElementException if the list is empty.
     */
    default E getHead(){
         if (isEmpty()){
             throw new MyNoSuchElementException("The list is empty");
         }
         return this.get(0);

     }
}
