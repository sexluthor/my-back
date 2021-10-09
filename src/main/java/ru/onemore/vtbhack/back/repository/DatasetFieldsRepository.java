package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetField;

import java.util.List;

@Repository
public interface DatasetFieldsRepository extends JpaRepository<DatasetField, Long> {
	List<DatasetField> getAllByDatasetIdIn(List<Long> datasetIds);
}
