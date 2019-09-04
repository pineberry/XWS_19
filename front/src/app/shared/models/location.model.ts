import { Deserializable } from "./deserializable.model";

export class Location implements Deserializable{
	
	id: number;
	state: string;
    city: string;
    address: string;
    latitude: number;
    longitude: number;
	
	deserialize(input: any): this {
		Object.assign(this, input);
	    return this;
	}

	constructor() {

	}
}