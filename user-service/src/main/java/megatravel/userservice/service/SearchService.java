package megatravel.userservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.model.Amenity;
import megatravel.userservice.dto.SearchParametersAddtDTO;
import megatravel.userservice.dto.SearchParametersDTO;
import megatravel.userservice.repository.AccommodationRepository;

@Service
public class SearchService {
	//with provied searching parameters, method needs to return a list of available accommodation
	
	@Autowired
	private AccommodationRepository accommodationRepository;
	
	
	
	//regular search: location, check-in & check-out, number of guests
	public List<AccommodationUnit> available(SearchParametersDTO parameter)
	{
		List<AccommodationUnit> available = new ArrayList<AccommodationUnit>();
		//parameter.getLocation().getCity().equals(accommodationUnit.getLocation().getCity()) &&
		for (AccommodationUnit accommodationUnit : accommodationRepository.findAll()) {
			if (parameter.getLocation().getCity().equals(accommodationUnit.getLocation().getCity()))
			{
				for (Pair<Date, Date> datePairs : accommodationUnit.getBookedDates()) {
					if(parameter.getCheckin().compareTo(datePairs.getFirst()) != 0) { //different checkin dates, if dates are the same, then its not available
						if(parameter.getCheckin().compareTo(datePairs.getSecond()) >= 0) //if requested checkin is the same day or after a checkout of some other reservation
						{
							if (parameter.getNumOfGuests() <= accommodationUnit.getUnitCapacity()) { //if unit is for the requested number of people or more
								available.add(accommodationUnit);
							}
						}
					}
				}
			}
		}
		return available;
	}
	
	//advanced search: regular search parameters + accommodation type & category, 0
	public List<AccommodationUnit> availableAddt(SearchParametersAddtDTO parameter)
	{
		SearchParametersDTO baseParameters = new SearchParametersDTO(parameter.getLocation(), parameter.getCheckin(), parameter.getCheckout(), parameter.getNumOfGuests());
		List<AccommodationUnit> available = available(baseParameters); //returns list of available accommodation filtered by given parameters
					
		
		for (AccommodationUnit accommodationUnit : available) {
			//if user asks for specific category (isEmpty=false), than accommodation in the filtering loop of available accommodations needs to be of that specific category
			//if it's not than we need to remove it from the list: line of code in the if block
			if(!parameter.getCategory().isEmpty() && !parameter.getCategory().equals(accommodationUnit.getCategory()))
			{
				available.remove(accommodationUnit);
				break;
			}
			if(!parameter.getType().isEmpty() && !parameter.getType().equals(accommodationUnit.getType()))
			{
				available.remove(accommodationUnit);
				break;
			}
			if(!parameter.getAmenities().isEmpty())
			{
				for (Amenity amenity: parameter.getAmenities()) {
					if(!accommodationUnit.getAmenities().contains(amenity))
					{
						available.remove(accommodationUnit);
						break;
					}
				}
			}
		}
		
		return available;
	}
}
