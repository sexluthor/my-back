package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.jooq.tables.pojos.OrderedDataset;

import java.util.List;

@Repository
public interface OrderedDatasetRepository extends JpaRepository<OrderedDataset, Long> {
	List<OrderedDataset> findAllByUsername(String username);
	Boolean existsByDatasetIdAndUsername(Long datasetId, String username);
}
