import {combineReducers} from 'redux';
import articleReducer from './articleReducer';
import loginReducer from './loginReducer';

const rootReducer = combineReducers({
  articleReducer,
  loginReducer
})

export default rootReducer;