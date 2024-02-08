import {Topic} from "./topic";

export interface User {
  username: string;
  email: string;
  topics: Topic[];
}
