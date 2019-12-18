import { User } from './user.model';

export class Review {
    constructor(
        public reviewId: number = 0,
        public flag: number = -1,
        public comment: string = '',
        public reviewer: User = null
    ) {}
}