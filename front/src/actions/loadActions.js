import serverapi from '../api/serverapi';
import * as types from './actionTypes';

export const tryLoginAndDispatch = (dispatch) => (id, password) => {
    return serverapi.tryLogin(id, password)
        .then(response => {
            // 로그인 성공이면, authorization을 store에 넣어준다
            let authorization = response.headers.get("authorization");
            let message = response.json()
                .then(data => {
                    console.log(data);
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
export const tryGetPostsAndDispatch = (dispatch) => (authorization) => {
    return serverapi.getPosts(authorization)
        .then(response => {
            if (response.message == 'Success') {
                dispatch({
                    type: types.LOAD_GETPOSTS_SUCCESS,
                    data: {
                        posts: response.data.posts,
                    }
                });
            } else {
                dispatch({
                    type: types.LOAD_GETPOSTS_FAIL
                });
            }
        });
};

export function getArticles() {
    return function (dispatch) {
        return serverapi.getArticles().then(cats => {
            dispatch(getArticlesSuccess(cats));
        }).catch(error => {
            throw (error);
        });
    };
}

export function getArticlesSuccess(articles) {
    return { type: types.LOAD_GETPOSTS_SUCCESS, articles };
}

export function tryLogin(id, password) {
    return function (dispatch) {
        return fetch('http://localhost:8080/login', { method: 'POST' })
            .then((response) => response.text())
            .then(data => data);
    }
}