import { Reservation } from "./reservation.model";
import { Deserializable } from "./deserializable.model";

export class User implements Deserializable {
	
	id: number;
	typeOfUser: string;
	firstName: string;
	lastName: string;
	username: string;
	password: string;
	address: string;
	pib: number;
	reservations: Reservation[];

	deserialize(input: any): this {
		Object.assign(this, input);
		if(input.reservations != null)
		{
			this.reservations = input.reservations.map((reservation) => new Reservation().deserialize(reservation));
		}
	    return this;
	}

	constructor() {

	}
}