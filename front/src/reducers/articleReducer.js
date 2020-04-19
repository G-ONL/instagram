import * as types from '../actions/actionTypes';

let initialState = {
  posts: [],
  response: ""
};

export default function articleReducer(state = initialState, action) {
  switch(action.type) {
    case types.LOAD_GETPOSTS_SUCCESS:
      return {...state, posts: action.data.posts};
    default: 
      return state;
  }
}