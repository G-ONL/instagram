import React from 'react';
import styled from 'styled-components';
import Post from "../components/Post";
import {connect} from "react-redux";
import {tryGetPostsAndDispatch_API} from "../actions/loadActions";
import PropTypes from 'prop-types';

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  min-height: 80vh;
`;

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
                    posts.map(post => (
                        <Post
                            key={post.id}
                            id={post.id}
                            location={post.location}
                            caption={post.caption}
                            user={post.user}
                            postPictures={post.postPictures}
                            likeCount={post.likeCount}
                            isLiked={post.isLiked}
                            comments={post.comments}
                            createdDate={post.createdDate}
                        />
                    ))
                }
            </Wrapper>
        );
    }
}

let mapStateToProps = (state) => ({
    posts: state.articleReducer.posts,
    authorization: state.loginReducer.authorization
});

let mapDispatchToProps = (dispatch) => ({
    tryGetPostsAndDispatch: (authorization) => {
        return tryGetPostsAndDispatch_API(dispatch, authorization);
    }
});

export default connect(mapStateToProps, mapDispatchToProps)(Feed);