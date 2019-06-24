package megatravel.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.backend.model.AccommodationUnit;
import megatravel.userservice.dto.SearchParametersDTO;
import megatravel.userservice.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	public ResponseEntity<List<AccommodationUnit>> getAvailableAccommodation(@RequestBody SearchParametersDTO parameter)
	{
		return new ResponseEntity<List<AccommodationUnit>>(searchService.available(parameter), HttpStatus.OK);
	} 
	
	/*@RequestMapping("/more")
	public ResponseEntity<List<AccommodationUnit>> getAvailableQuestAccommodation(@RequestBody SearchParametersDTO parameter)
	{
		return new ResponseEntity<List<AccommodationUnit>>(searchService.avblAddt(parameter), HttpStatus.OK);
	} */
}
