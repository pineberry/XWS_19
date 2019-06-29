export class Location {
	state: string;
    city: string;
    address: string;
    latitude: number;
    longitude: number;

    constructor(state: string, city: string, address: string)
    {
    	this.state = state;
    	this.city = city;
    	this.address = address;
    	this.longitude = 0;
    	this.latitude = 0;
    }
}