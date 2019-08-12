package com.pinakjakhr.entity;

import static com.pinakjakhr.constants.APIConstants.TABLE_COUNTRY;
import static com.pinakjakhr.constants.APIConstants.TYPE_JSONB;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = TABLE_COUNTRY)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TypeDef(name = TYPE_JSONB, typeClass = JsonBinaryType.class)
public class Country implements Serializable {

	@Id
	@GeneratedValue(generator = "uuid-gen")
	@Type(type = "pg-uuid")
	@ApiModelProperty(hidden = true)
	private UUID countryId;

	@NotBlank
	private String countryName;

	@NotBlank
	private String countryCode;

	@NotEmpty
	@Type(type = TYPE_JSONB)
	@Column(columnDefinition = TYPE_JSONB)
	private LinkedHashSet<State> states;

}
