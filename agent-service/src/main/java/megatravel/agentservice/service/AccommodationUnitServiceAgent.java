package megatravel.agentservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.agentservice.model.AccommodationUnitAgent;
import megatravel.agentservice.repository.AccommodationUnitRepositoryAgent;

@Service
public class AccommodationUnitServiceAgent {

	@Autowired
	private AccommodationUnitRepositoryAgent accommodationUnitRepository;
	
	//create
	public AccommodationUnitAgent create(AccommodationUnitAgent accommodationUnit) 
	{
		System.out.println("\n accommodationunit service: \n" + accommodationUnit + "\n");
		return accommodationUnitRepository.save(accommodationUnit);
	}
	
	//read one by ID
	public Optional<AccommodationUnitAgent> readById(Long id) 
	{
		return accommodationUnitRepository.findById(id);
	}
	
	//read all
	public List<AccommodationUnitAgent> readAll(int id) 
	{
		List<AccommodationUnitAgent> allAccommodations = accommodationUnitRepository.findAll();
		List<AccommodationUnitAgent> agentAccommodations = new ArrayList<AccommodationUnitAgent>();
		 
		for (AccommodationUnitAgent accommodationUnit : allAccommodations) {
			if(accommodationUnit.getHostId() == id)
			{
				agentAccommodations.add(accommodationUnit);
			}
		}
		
		return agentAccommodations;
	}
	
	//update - by ID
	public void update(AccommodationUnitAgent accommodationUnit, Long id) 
	{
		//get outdated acc by ID
		Optional<AccommodationUnitAgent> acc = accommodationUnitRepository.findById(id);
		if (acc.isPresent()) {
			//create new that will be filed with updated data & with the ID of the outdated 
			AccommodationUnitAgent temp = accommodationUnit;
			temp.setId(acc.get().getId());
			accommodationUnitRepository.save(temp);
		}
	}
	//delete - by ID
	public void delete(AccommodationUnitAgent accommodationUnit) {
		accommodationUnitRepository.delete(accommodationUnit);
	}
	
	public void confirmReservation(AccommodationUnitAgent accommodationUnit)
	{
		AccommodationUnitAgent temp = accommodationUnit;
		//temp.setReserved(true);
		accommodationUnitRepository.deleteById(accommodationUnit.getId());
		accommodationUnitRepository.save(temp);
	}
	
}
