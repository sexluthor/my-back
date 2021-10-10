package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.entity.DatasetEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<DatasetEntity, Long>, DatasetRepositoryCustom {
	List<DatasetEntity> findAllBy();
	List<DatasetEntity> findAllByIdIn(List<Long> ids);

	@Query(value = "select distinct ds from ru.onemore.vtbhack.back.entity.DatasetEntity ds\n" +
			"left join ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag dt on dt.datasetId = ds.id\n" +
			"where ds.lastUpdated between :dateFrom and :dateTo and\n" +
			"dt.name in (:tagNames) and\n" +
			"ds.price between :priceFrom and :priceTo")
	List<DatasetEntity> findFiltered(
		@Param("dateFrom") LocalDateTime dateFrom,
		@Param("dateTo") LocalDateTime dateTo,
		@Param("tagNames") List<String> tagNames,
		@Param("priceFrom") Integer priceFrom,
		@Param("priceTo") Integer priceTo
	);

	List<DatasetEntity> findAllByLastUpdatedBetweenAndPriceBetween(
		LocalDateTime dateFrom,
		LocalDateTime dateTo,
		Integer priceFrom,
		Integer priceTo
	);

}
