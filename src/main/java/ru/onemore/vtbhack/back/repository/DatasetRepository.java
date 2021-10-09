package ru.onemore.vtbhack.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.onemore.vtbhack.back.entity.DatasetEntity;

import java.util.List;

@Repository
public interface DatasetRepository extends JpaRepository<DatasetEntity, Long> {
	List<DatasetEntity> findAllBy();
}
