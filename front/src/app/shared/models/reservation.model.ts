import { Deserializable } from "./deserializable.model";
import { AccommodationUnit } from "./accommodation-unit.model"


export class Reservation implements Deserializable{
	
	id: number;
	accommodationUnit: AccommodationUnit;
	checkInDate: Date;
	checkOutDate: Date;
	totalPrice: number;
	guestId: number;
	status: string;
	confirmed: boolean;

	deserialize(input: any): this {
		Object.assign(this, input);
	    return this;
	}

	constructor() {

	}
}