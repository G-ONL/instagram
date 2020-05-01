import serverapi from '../api/serverapi';
import * as types from './actionTypes';

export const tryLoginAndDispatch = (dispatch) => (id, password) => {
    return serverapi.tryLogin(id, password)
        .then(response => {
            // 로그인 성공이면, authorization을 store에 넣어준다
            let authorization = response.headers.get("authorization");
            let message = response.json()
                .then(data => {
                    if (data.message == 'Success') {
                        dispatch({
                            type: types.LOGIN_SUCCESS,
                            data: {
                                authorization
                            }
                        });
                    }
                });
        });
};
export const tryGetPostsAndDispatch_API = (dispatch, authorization) => {
    return serverapi.getPosts(authorization)    // 서버에 posts데이터를 요청하는 함수.
        .then(response => {
            if (response.message == 'Success') {
                dispatch({
                    type: types.LOAD_GETPOSTS_SUCCESS,
                    data: {
                        posts: response.data,
                    }
                });
            } else {
                dispatch({
                    type: types.LOAD_GETPOSTS_FAIL
                });
            }
        });
};
export const createCommentAndDispatchPost = ({dispatch, authorization, postId, comment}) => {
    // 서버에 comment 생성한다
    // ok면
    // 서버에 post 요청한다
    // ok면
    // dispatch 한다


    // comment생성 시도
    // 성공시 post를 get해서 dispatch한다
    serverapi.createComment({authorization, postId, comment})
        .then(response => {
            if(response.message == 'Success'){
                tryGetPostsAndDispatch_API(dispatch, authorization);
            }
        })
};

function getAddedNumber(data){
    return data.a + data.b;
}
let k = {
    a: 3,
    b: 4
};
let result = getAddedNumber(k);