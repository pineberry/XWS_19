package megatravel.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.AccommodationUnit;
import megatravel.backend.repository.AccommodationUnitRepository;

@Service
public class AccommodationUnitService {

	@Autowired
	private AccommodationUnitRepository accommodationUnitRepository;
	
	//create
	public AccommodationUnit create(AccommodationUnit accommodationUnit) 
	{
		return accommodationUnitRepository.save(accommodationUnit);
	}
	
	//read one by ID
	public Optional<AccommodationUnit> readById(Long id) 
	{
		return accommodationUnitRepository.findById(id);
	}
	
	//read all from one agent
	public List<AccommodationUnit> readAll(int id) 
	{
		List<AccommodationUnit> allAccommodations = accommodationUnitRepository.findAll();
		List<AccommodationUnit> agentAccommodations = new ArrayList<AccommodationUnit>();
		 
		for (AccommodationUnit accommodationUnit : allAccommodations) {
			if(accommodationUnit.getHostId() == id)
			{
				agentAccommodations.add(accommodationUnit);
			}
		}
		
		return agentAccommodations;
	}
	
	//update - by ID
	public void update(AccommodationUnit accommodationUnit, Long id) 
	{
		//get outdated acc by ID
		Optional<AccommodationUnit> acc = accommodationUnitRepository.findById(id);
		if (acc.isPresent()) {
			//create new that will be filed with updated data & with the ID of the outdated 
			AccommodationUnit temp = accommodationUnit;
			temp.setId(acc.get().getId());
			accommodationUnitRepository.save(temp);
		}
	}
	//delete - by ID
	public void delete(AccommodationUnit accommodationUnit) {
		accommodationUnitRepository.delete(accommodationUnit);
	}
	
}
