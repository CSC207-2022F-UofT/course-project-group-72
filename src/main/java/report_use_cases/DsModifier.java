package report_use_cases;

import entities.Review;

public interface DsModifier<T> {
    void update(T object_to_update);
}
