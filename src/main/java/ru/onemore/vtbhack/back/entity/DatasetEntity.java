package ru.onemore.vtbhack.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetField;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dataset_catalog")
public class DatasetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;

	@OneToMany(targetEntity = DatasetField.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dataset_id")
	private List<DatasetField> fields;

	@OneToMany(targetEntity = DatasetTag.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dataset_id")
	private List<DatasetTag> tags;

}
