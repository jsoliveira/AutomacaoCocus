package br.com.cocus.automacaococus.lazy.model;

import br.com.cocus.automacaococus.lazy.model.sorter.LazySorter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Id;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author jsoliveira
 * @param <I> Classe
 * @param <T> Tipo do Objeto da Chave Primaria
 */
public class LazyDataModelGeneric<I, T> extends LazyDataModel<I> {

    private final List<I> datasource;
    public Class<I> type;
    private Field idField;
    private final Field[] fields;
    public String[] columns;

    public LazyDataModelGeneric(List<I> datasource, Class<I> type) {
        this.datasource = datasource;
        this.type = type;
        this.fields = this.type.getDeclaredFields();

        getNameField();
    }

    @Override
    public I getRowData(String rowKey) {
        for (I obj : datasource) {
            try {
                if (idField.get(obj).equals((T) rowKey)) {
                    return obj;
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return null;

    }

    @Override
    public Object getRowKey(I obj) {
        try {
            return idField.get(obj);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            return null;
        }
    }

    @Override
    public List<I> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<I> data = new ArrayList<>();

        //filter
        for (I o : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {

                        //Field [] f = Marca.class.getDeclaredFields() ;
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);

                        Field f = type.getDeclaredField(filterProperty);
                        f.setAccessible(true);
                        String fieldValue = String.valueOf(f.get(o));
                        //String fieldValue = String.valueOf(marca.getClass().getDeclaredField(filterProperty).get(marca));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(o);
            }
        }

        //sort
        if (sortField != null) {
            Collections.sort(data, new LazySorter(sortField, sortOrder, type));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

    private void getNameField() {

        this.columns = new String[fields.length];
        int contador = 0;
        for (Field f : fields) {

            f.setAccessible(true);

            if (f.isAnnotationPresent(Id.class)) {

                this.idField = f;
                this.idField.setAccessible(true);
            }

            this.columns[contador] = f.getName();

            contador++;

        }

    }

}
