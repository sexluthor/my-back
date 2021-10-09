package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.entity.DatasetEntity;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<DatasetEntity, Long>, DatasetRepositoryCustom {
	List<DatasetEntity> findAllBy();

	@Query(value = "select ds from ru.onemore.vtbhack.back.entity.DatasetEntity ds\n" +
			"left join ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag dt on dt.datasetId = ds.id\n" +
			"where ds.lastUpdated between :dateFrom and :dateTo and\n" +
			"dt.id in (coalesce(:tagIds, dt.id)) and\n" +
			"ds.price between :priceFrom and :priceTo")
	List<DatasetEntity> findFiltered(
		@Param("dateFrom") LocalDateTime dateFrom,
		@Param("dateTo") LocalDateTime dateTo,
		@Param("tagIds") List<Long> tagIds,
		@Param("priceFrom") Integer priceFrom,
		@Param("priceTo") Integer priceTo
	);

//	List<DatasetEntity> getAllByLastUpdatedBetweenAndTagsInAndPriceBetween(
//			LocalDateTime dateFrom,
//			LocalDateTime dateTo,
//			List<DatasetTag> tags,
//			Integer priceFrom,
//			Integer priceTo
//	);
}
