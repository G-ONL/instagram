import * as types from '../actions/actionTypes';
import produce from 'immer';

let initialState = {
  posts: [],
  response: ""
};

const transformDateFormat = (date) => {
  const dateData = new Date(date);
  return `${dateData.getFullYear()}.${dateData.getMonth()}.${dateData.getDate()} ${dateData.getHours()}:${dateData.getMinutes()}`;
};

export default function articleReducer(state = initialState, action) {
  switch (action.type) {
    case types.LOAD_GETPOST_SUCCESS: {
      const newState = produce(state, newnewState => {
        const foundPostIndex = newnewState.posts.findIndex(post => post.id == action.data.id);
        newnewState.posts[foundPostIndex] = action.data;

        // date format 바꾸기
        const createdDate = newnewState.posts[foundPostIndex].createdDate;
        newnewState.posts[foundPostIndex].createdDate = transformDateFormat(createdDate);
      });
      return newState;
    }
    case types.LOAD_GETPOSTS_SUCCESS: {
      const nextState = produce(state, draft => {
        draft.posts = action.data.posts;

        // date format 바꾸기
        draft.posts.map((post) => {
          const createdDate = post.createdDate;
          post.createdDate = transformDateFormat(createdDate);

          return post;
        });
      });

      return nextState;
    }
    case types.CREATE_COMMENT_SUCCESS: {
      const { postId, comment } = action.data;

      const nextState = produce(state, draft => {
        const commentedPost = draft.posts.find((post) => post.id == postId);
        commentedPost.comments.push({ comment, id: (commentedPost.comments.length + 2) });
      });
      return nextState;
    }
    default:
      return state;
  }
}