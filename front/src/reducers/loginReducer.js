import * as types from '../actions/actionTypes';

let initialState = {
    authorization: ""
  };

export default (state = initialState, action) => {
    switch(action.type){
        case types.LOGIN_SUCCESS:{
            let newState = Object.assign({}, state, {
                authorization: action.data.authorization
            });
            return newState;
        }
        case types.LOGIN_FAIL:{
            let newState = Object.assign({}, state);
            return newState;
        }
        default:
            return state;
    }
}