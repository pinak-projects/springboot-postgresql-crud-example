package com.pinakjakhr.controller;

import static com.pinakjakhr.constants.APIConstants.API_DELETE_COUNTRY_INFO;
import static com.pinakjakhr.constants.APIConstants.API_FETCH_COUNTRIES_INFO;
import static com.pinakjakhr.constants.APIConstants.API_FETCH_COUNTRY_INFO;
import static com.pinakjakhr.constants.APIConstants.API_FETCH_STATES_INFO;
import static com.pinakjakhr.constants.APIConstants.API_SAVE_COUNTRY_INFO;
import static com.pinakjakhr.constants.APIConstants.API_TAG_COUNTRY;
import static com.pinakjakhr.constants.APIConstants.API_UPDATE_COUNTRY_INFO;
import static com.pinakjakhr.constants.APIConstants.COUNTRY_DETAILS_DELETE_SUCCESS;
import static com.pinakjakhr.constants.APIConstants.COUNTRY_ID_PARAM;
import static com.pinakjakhr.constants.APIConstants.REST_COUNTRY_PATH;
import static com.pinakjakhr.constants.APIConstants.STATES_END_POINT;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pinakjakhr.constants.ResponseMessage;
import com.pinakjakhr.entity.Country;
import com.pinakjakhr.service.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = REST_COUNTRY_PATH)
@Api(tags = API_TAG_COUNTRY)
public class CountryController {

	@Autowired
	private CountryService countryService;

	@ApiOperation(value = API_SAVE_COUNTRY_INFO)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country saveCountry(@Valid @RequestBody Country country) {

		return countryService.saveCountryDetails(country);
	}

	@ApiOperation(value = API_UPDATE_COUNTRY_INFO)
	@PutMapping(value = COUNTRY_ID_PARAM, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Country updateCountry(@PathVariable UUID countryId, @Valid @RequestBody Country country) {

		country.setCountryId(countryId);
		return countryService.editCountryDetails(country);
	}

	@ApiOperation(value = API_FETCH_COUNTRY_INFO)
	@GetMapping(value = COUNTRY_ID_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public Country fetchCountry(@PathVariable UUID countryId) {

		return countryService.getCountryDetails(countryId);
	}

	@ApiOperation(value = API_FETCH_COUNTRIES_INFO)
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public List<Country> fetchAllCountries() {

		return countryService.getAllCountries();
	}

	@ApiOperation(value = API_DELETE_COUNTRY_INFO)
	@DeleteMapping(value = COUNTRY_ID_PARAM)
	public ResponseEntity<?> deleteCountry(@PathVariable UUID countryId) {

		countryService.deleteCountryDetails(countryId);
		return ResponseEntity.ok(new ResponseMessage(COUNTRY_DETAILS_DELETE_SUCCESS));
	}

	@ApiOperation(value = API_FETCH_STATES_INFO)
	@GetMapping(value = STATES_END_POINT, produces = MediaType.APPLICATION_JSON_VALUE)
	public Object fetchAllStates(@PathVariable UUID countryId) {

		return countryService.getStatesByCountryId(countryId);
	}
}
