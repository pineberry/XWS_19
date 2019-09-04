import { Deserializable } from "./deserializable.model";

export class Amenity implements Deserializable{
	
	id: number;
	amenity: string;

	deserialize(input: any): this {
		Object.assign(this, input);
	    return this;
	}

	constructor() {

	}
}