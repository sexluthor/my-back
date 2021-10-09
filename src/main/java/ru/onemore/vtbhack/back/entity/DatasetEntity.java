package ru.onemore.vtbhack.back.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetTag;
import ru.onemore.vtbhack.back.jooq.tables.pojos.DatasetField;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "dataset_catalog")
public class DatasetEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private Integer price;

	@Column(name = "last_updated")
	private LocalDateTime lastUpdated;


	@Column(name = "fields")
	@OneToMany(targetEntity = DatasetField.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dataset_id")
	private List<DatasetField> fields;


	@Column(name = "tags")
	@OneToMany(targetEntity = DatasetTag.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "dataset_id")
	private List<DatasetTag> tags;

}
