import { Deserializable } from "./deserializable.model";
import { Location } from "./location.model";
import { Image } from "./image.model";
import { Amenity } from "./amenity.model";
import { Review } from "./review.model";

export class AccommodationUnit implements Deserializable{
	
	id: number;
	hostId: number;
	location: Location;
	type: string;
	category: string;
	description: string;
	unitCapacity: number;
	images: Image[];
	amenities: [];
	cancelationPeriod: number;
	pricePlan: Map<string, number>;
	reviews: Review[];
	bookedDates: string[];

	deserialize(input: any): this {
		Object.assign(this, input);
		this.location = new Location().deserialize(input.location);
	    return this;
	}

	constructor() {

	}
}