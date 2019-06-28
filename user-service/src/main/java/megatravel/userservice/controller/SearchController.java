package megatravel.userservice.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.dto.SearchParametersAddtDTO;
import megatravel.backend.dto.SearchParametersDTO;
import megatravel.backend.model.AccommodationUnit;
import megatravel.userservice.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAvailableAccommodation(@RequestBody SearchParametersDTO parameter) throws ParseException
	{
		return new ResponseEntity<AccommodationUnitListDTO>(searchService.available(parameter), HttpStatus.OK);
	} 
	
	@RequestMapping("/more")
	public ResponseEntity<List<AccommodationUnit>> getAvailableQuestAccommodation(@RequestBody SearchParametersAddtDTO parameter) throws ParseException
	{
		return new ResponseEntity<List<AccommodationUnit>>(searchService.availableAddt(parameter), HttpStatus.OK);
	}
}
