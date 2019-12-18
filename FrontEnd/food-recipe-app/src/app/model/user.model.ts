import { Dish } from './dish.model';
import { Role } from './role.enum';

export class User {
    constructor(
        public userId: number = 0,
        public emailId: string = '',
        public password: string = '',
        public roles: Role[] = [],
        public name: string = '',
        public dateOfBirth: Date = null,
        public gender: number = -1,
        public city: string = '',
        public about: string = '',
        public dishes: Dish[] = [],
    ) {}
}