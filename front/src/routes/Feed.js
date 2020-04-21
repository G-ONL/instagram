import React from 'react';
import styled from 'styled-components';
import Post from "../components/Post";
import {connect} from "react-redux";
import {tryGetPostsAndDispatch} from "../actions/loadActions";
import PropTypes from 'prop-types';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 80vh;
`;
const data = [
    {
        id: "kibaek",
        location: "seoul",
        caption: "꼭봐... 이태원클라쓰..",
        user: {
            id: "okokofds",
            avatar: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
            username: "psykibaek"
        },
        files: [{
            id: "ok",
            url: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
        }],
        likeCount: 1,
        isLiked: 2,
        comments: [{
            id: "ookkoo",
            text: "우와 박새로이다",
            user: {
                id: "kaisdf",
                username: "희범"
            },
        },
        {
            id: "ookkoo",
            text: "아직 이태원클라쓰 안봤는데",
            user: {
                id: "kaisdf",
                username: "제인"
            },
        }],
        createdAt: "2020.09.15"
    },
    {
        id: "kibaek",
        location: "seoul",
        caption: "okok",
        user: {
            id: "okokofds",
            avatar: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
            username: "username"
        },
        files: [{
            id: "ok",
            url: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
        }],
        likeCount: 1,
        isLiked: 2,
        comments: [{
            id: "ookkoo",
            text: "textme baby",
            user: {
                id: "okok",
                username: "okok"
            },
        }],
        createdAt: "2020.09.15"
    },
    {
        id: "kibaek",
        location: "seoul",
        caption: "okok",
        user: {
            id: "okokofds",
            avatar: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
            username: "username"
        },
        files: [{
            id: "ok",
            url: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
        }],
        likeCount: 1,
        isLiked: 2,
        comments: [{
            id: "ookkoo",
            text: "textme baby",
            user: {
                id: "okok",
                username: "okok"
            },
        }],
        createdAt: "2020.09.15"
    },
    {
        id: "kibaek",
        location: "seoul",
        caption: "okok",
        user: {
            id: "okokofds",
            avatar: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
            username: "username"
        },
        files: [{
            id: "ok",
            url: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
        }],
        likeCount: 1,
        isLiked: 2,
        comments: [{
            id: "ookkoo",
            text: "textme baby",
            user: {
                id: "okok",
                username: "okok"
            },
        }],
        createdAt: "2020.09.15"
    },
    {
        id: "kibaek",
        location: "seoul",
        caption: "okok",
        user: {
            id: "okokofds",
            avatar: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
            username: "username"
        },
        files: [{
            id: "ok",
            url: "https://image.ytn.co.kr/osen/2019/10/57e16c08-df0c-48db-8594-c53d549c7800.jpg",
        }],
        likeCount: 1,
        isLiked: 2,
        comments: [{
            id: "ookkoo",
            text: "textme baby",
            user: {
                id: "okok",
                username: "okok"
            },
        }],
        createdAt: "2020.09.15"
    }
];

class Feed extends React.Component {
    componentDidMount(){
        let { tryGetPostsAndDispatch, authorization } = this.props;
        tryGetPostsAndDispatch(authorization);
    }

    render() {
        let { posts } = this.props;

        return (
            <Wrapper>
                {
                    data.map(post => (
                        <Post
                            key={post.id}
                            id={post.id}
                            location={post.location}
                            caption={post.caption}
                            user={post.user}
                            files={post.files}
                            likeCount={post.likeCount}
                            isLiked={post.isLiked}
                            comments={post.comments}
                            createdAt={post.createdAt}
                        />
                    ))
                }
            </Wrapper>
        );
    }
}

let mapStateToProps = (state, ownProps) => ({
    posts: state.articleReducer.posts,
    authorization: state.loginReducer.authorization
});
let mapDispatchToProps = (dispatch) => ({
    tryGetPostsAndDispatch: (authorization) => {
        return tryGetPostsAndDispatch(dispatch)(authorization);
    }
});
export default connect(mapStateToProps, mapDispatchToProps)(Feed);