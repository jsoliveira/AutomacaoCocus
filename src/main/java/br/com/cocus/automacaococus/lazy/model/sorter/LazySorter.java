package br.com.cocus.automacaococus.lazy.model.sorter;

/**
 *
 * @author jsoliveira
 */
import java.util.Comparator;
import org.primefaces.model.SortOrder;

public class LazySorter<I> implements Comparator<I> {

    private final Class<I> type;

    private final String sortField;

    private final SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder, Class<I> type) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
        this.type = type;
    }

    @Override
    public int compare(I v1, I v2) {
        try {
            Object value1 = type.getField(this.sortField).get(v1);
            Object value2 = type.getField(this.sortField).get(v2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}
