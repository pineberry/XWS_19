package megatravel.agentservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.backend.model.AccommodationUnit;
import megatravel.agentservice.repository.AccommodationUnitRepositoryAgent;

@Service
public class AccommodationUnitServiceAgent {

	@Autowired
	private AccommodationUnitRepositoryAgent accommodationUnitRepository;
	
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
	
	//read all
	public List<AccommodationUnit> readAll() 
	{
		return accommodationUnitRepository.findAll();
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
	
	public void confirmReservation(AccommodationUnit accommodationUnit)
	{
		AccommodationUnit temp = accommodationUnit;
		temp.setReserved(true);
		accommodationUnitRepository.deleteById(accommodationUnit.getId());
		accommodationUnitRepository.save(temp);
	}
	
}
