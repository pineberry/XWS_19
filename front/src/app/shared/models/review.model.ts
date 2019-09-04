import { Deserializable } from "./deserializable.model";

export class Review implements Deserializable{
	
	id: number;
	reviewContent: string;
    mark: number;

	deserialize(input: any): this {
		Object.assign(this, input);
	    return this;
	}

	constructor() {

	}
}