package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;

import java.util.List;

@Repository
public interface DatasetTagsRepository extends JpaRepository<DatasetTag, Long> {
	List<DatasetTag> getAllByIdIn(List<Long> ids);
	@Query(value = "select distinct name from dataset_tag", nativeQuery = true)
	List<String> getDistinctName();
}
