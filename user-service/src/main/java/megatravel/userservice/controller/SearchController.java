package megatravel.userservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<AccommodationUnitListDTO> getAvailableAccommodation(@RequestParam(name = "location") String location, @RequestParam(name = "checkin") String checkin_,
			@RequestParam(name = "checkout") String checkout_, @RequestParam(name = "guests") String numOfGuests) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date checkin = sdf.parse(checkin_);
		Date checkout = sdf.parse(checkout_);
		SearchParametersDTO parameter = new SearchParametersDTO(location, checkin, checkout, Integer.parseInt(numOfGuests));
		AccommodationUnitListDTO accommodations = searchService.available(parameter);
		return new ResponseEntity<AccommodationUnitListDTO>(accommodations, HttpStatus.OK);
	} 
	
	@RequestMapping("/more")
	public ResponseEntity<List<AccommodationUnit>> getAvailableQuestAccommodation(@RequestBody SearchParametersAddtDTO parameter) throws ParseException
	{
		return new ResponseEntity<List<AccommodationUnit>>(searchService.availableAddt(parameter), HttpStatus.OK);
	}
}
