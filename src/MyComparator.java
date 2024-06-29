/**
 * {@code MyComparator} interface needs it for specify personal order to the list.
 * It is necessary for {@link MyList#sort(MyComparator)}.
 * @author Nikita Egorov
 * @param <T> Type of elements of the list.
 */
@FunctionalInterface
public interface MyComparator<T> {
        /**
         * In this method place rules of how two elements have to be compared.
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return a negative integer, zero, or a positive integer as the
         *           first argument is less than, equal to, or greater than the
         *           second.
         */
        int compare(T o1,T o2 );

}
