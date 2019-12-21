import { User } from './user.model';

export interface Issue{
    issueId?:number,
    issue?:string,
    answer?:string,
    isAnswered?:boolean,
    user?:User
}