import { Deserializable } from "./deserializable.model";

export class Image implements Deserializable{
	
	id: number;
	src: string;

	deserialize(input: any): this {
		Object.assign(this, input);
	    return this;
	}

	constructor() {

	}
}