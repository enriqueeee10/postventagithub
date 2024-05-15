
package Paginador;

import java.util.List;

public interface TotalRows<T> {
    int getTotalRowCount();
    List <T> getRows (int inicio, int fin);
}
