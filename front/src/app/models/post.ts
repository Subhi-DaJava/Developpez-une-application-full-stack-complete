import { Comment } from './comment';
export interface Post {
  id: number;
  title: string;
  content: string;
  createdAt: Date;
  authorUsername: string;
  topicName: string;
  comments: Comment[]
}
