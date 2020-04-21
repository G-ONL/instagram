
import { amazonurl, localurl } from '../common/serverurl';

class serverapi {
    static tryLogin(id, password) {
        return fetch(amazonurl + '/user/login', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                userName: id,
                password
            })
        });
    }
    static getPosts(authorization) {
        return fetch(amazonurl + '/api/v1/posts', {
            method: 'GET',
            headers: {
                authorization
            }
        })
            .then(response => response.json());
    }
    static createPost(authorization, data) {
        return fetch(amazonurl + '/api/v1/posts', {
            method: 'POST',
            headers: {
                'authorization': authorization,
                'Accept': 'application/json, application/xml, text/plain, text/html, *.*'
            },
            body: data
        })
            .then(response => response.json());
    }
    static updateArticle() {
    }
    static deleteArticle() {
    }
}
export default serverapi;