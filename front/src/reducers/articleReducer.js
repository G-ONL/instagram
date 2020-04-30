import * as types from '../actions/actionTypes';
import produce from 'immer';  

let initialState = {
  posts: [],
  response: ""
};


export default function articleReducer(state = initialState, action) {
  switch(action.type) {
    case types.LOAD_GETPOST_SUCCESS:{
      // action.data안에 post가 있다.
      // 그 post의 id를 확인한다
      // 내 posts안에 그 id랑 같은놈을 찾는다.
      // 갈아 끼운다.
      const newState = produce(state, newnewState => {
        const foundPostIndex = newnewState.posts.findIndex(post=>post.id == action.data[0].id);
        newnewState.posts[foundPostIndex] = action.data[0];
      });
      return newState;
    }
    case types.LOAD_GETPOSTS_SUCCESS:{
      const nextState = produce(state, draft =>{
        draft.posts = action.data.posts;
      });
      return nextState;
    }
    case types.CREATE_COMMENT_SUCCESS:{
      const {postId, comment} = action.data;
      
      const nextState = produce(state, draft => {
        const commentedPost = draft.posts.find((post)=>post.id == postId);
        commentedPost.comments.push({comment, id:(commentedPost.comments.length+2)});
      });
      return nextState;
    }
    default: 
      return state;
  }
}