package megatravel.userservice.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.dto.AccommodationUnitListDTO;
import megatravel.backend.dto.SearchParametersAddtDTO;
import megatravel.backend.dto.SearchParametersDTO;
import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.repository.AccommodationUnitRepository;

@Service
public class SearchService {
	//with provied searching parameters, method needs to return a list of available accommodation

	@Autowired
	private AccommodationUnitRepository accommodationRepository;	

	//regular search: location, check-in & check-out, number of guests
	public AccommodationUnitListDTO available(SearchParametersDTO parameter) throws ParseException
	{
		List<AccommodationUnit> temp = new ArrayList<AccommodationUnit>();
		AccommodationUnitListDTO available = new AccommodationUnitListDTO();
		//parameter.getLocation().getCity().equals(accommodationUnit.getLocation().getCity()) &&
		for (AccommodationUnit accommodationUnit : accommodationRepository.findAll()) {
			if (parameter.getCity().equals(accommodationUnit.getLocation().getCity()))
			{
				if (!accommodationUnit.getBookedDates().isEmpty()) {
					for (String datePairs : accommodationUnit.getBookedDates()) { // datum-datum, datum-datum

						String[] date = datePairs.split("-");
						DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
						Date checkin = dateFormat.parse(date[0]);
						Date checkout = dateFormat.parse(date[1]);
						if (parameter.getNumOfGuests() <= accommodationUnit.getUnitCapacity()) { //if unit is for the requested number  of people or more
							if (parameter.getCheckin().compareTo(checkin) != 0) { //different  checkin dates, if dates are the same, then its not available
								if (parameter.getCheckin().compareTo(checkout) >= 0) //if requested checkin is the same day or after a checkout of some other reservation 
								{
									temp.add(accommodationUnit);
								}
							}
						}
					} 
				} else if (parameter.getNumOfGuests() <= accommodationUnit.getUnitCapacity()) {
					temp.add(accommodationUnit);
				}


			}
		}

		available.setAccommodationUnits(temp);
		return available;
	}

	//advanced search: regular search parameters + accommodation type & category, 0
	public AccommodationUnitListDTO availableAddt(SearchParametersAddtDTO parameter) throws ParseException
	{
		SearchParametersDTO baseParameters = new SearchParametersDTO(parameter.getCity(), parameter.getCheckin(), parameter.getCheckout(), parameter.getNumOfGuests());
		AccommodationUnitListDTO available = available(baseParameters); //returns list of available accommodation filtered by given parameters
		List<AccommodationUnit> toRemove = new ArrayList<AccommodationUnit>();

		for (int i = 0; i < available.getAccommodationUnits().size(); i++) {
			AccommodationUnit accommodationUnit = available.getAccommodationUnits().get(i);
			if(!parameter.getCategory().equals(accommodationUnit.getCategory()))
			{
				toRemove.add(accommodationUnit);
				//available.getAccommodationUnits().remove(accommodationUnit);
				break;
			}
			if(!parameter.getType().equals(accommodationUnit.getType()))
			{
				toRemove.add(accommodationUnit);
				//available.getAccommodationUnits().remove(accommodationUnit);
				break;
			}

			if(!parameter.getAmenities().isEmpty()) { 
				Map<String, Boolean> amenities = new HashMap<String, Boolean>();
				amenities.put("wifi", parameter.getAmenities().get(0));
				amenities.put("parking", parameter.getAmenities().get(1));
				amenities.put("fen", parameter.getAmenities().get(2));
				amenities.put("kuhinja", parameter.getAmenities().get(3));
				amenities.put("ljubimci", parameter.getAmenities().get(4));
				amenities.put("kada", parameter.getAmenities().get(5));
				amenities.put("bazen", parameter.getAmenities().get(6));
				amenities.put("terasa", parameter.getAmenities().get(7));
				amenities.put("pogled", parameter.getAmenities().get(8));
				amenities.put("kafeaparat", parameter.getAmenities().get(9));
				amenities.put("vesmasina", parameter.getAmenities().get(10));
				amenities.put("izolacija", parameter.getAmenities().get(11));

				for (Map.Entry<String, Boolean> entry : amenities.entrySet()) {
					//if entry value == true but accommodation amenity value false
					if(entry.getValue() && !accommodationUnit.getAmenities().get(entry.getKey())) {
						toRemove.add(accommodationUnit);
						//available.getAccommodationUnits().remove(accommodationUnit); 
						break; 
					} 
				}
			}

		}

		/*
		 * for (AccommodationUnit accommodationUnit : available.getAccommodationUnits())
		 * { //if user asks for specific category (isEmpty=false), than accommodation in
		 * the filtering loop of available accommodations needs to be of that specific
		 * category //if it's not than we need to remove it from the list: line of code
		 * in the if block }
		 */
		available.getAccommodationUnits().removeAll(toRemove);
		System.out.println("\n"+available+"\n");

		return available;
	}
}
