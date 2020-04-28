import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import PostPresenter from "./PostPresenter";
import { connect } from 'react-redux';
import serverapi from '../../api/serverapi'
import { CREATE_COMMENT_SUCCESS } from '../../actions/actionTypes';
import {createCommentAndDispatchPost} from '../../actions/loadActions';

class PostContainer extends React.Component {
  render() {
    const {
      id,
      user,
      postPictures,
      likeCount,
      isLiked,
      comments,
      createdDate,
      caption
    } = this.props;

    this.state = {
      isLikedS: isLiked,
      likeCountS: likeCount
    };
    const setIsLiked = (isLikedS) => { this.state.isLikedS = isLikedS; };
    const setLikeCount = (likeCountS) => { this.state.likeCountS = likeCountS; };
    const comment = "";
    const toggleLike = () => {
      if (this.state.isLikedS === true) {
        setIsLiked(false);
        setLikeCount(this.state.likeCountS - 1);
      } else {
        setIsLiked(true);
        setLikeCount(this.state.likeCountS + 1);
      }
    };
    const onKeyPress = async event => {
      const { which } = event;
      if (which === 13) {
        event.preventDefault();
        try {
        } catch {
        }
      }
    };
    console.log("postcontainer rendered");
    return (
      <PostPresenter
        user={user}
        postPictures={postPictures}
        likeCount={this.state.likeCountS}
        caption={caption}
        isLiked={this.state.isLikedS}
        comments={comments}
        createdDate={createdDate}
        setIsLiked={setIsLiked}
        setLikeCount={setLikeCount}
        toggleLike={toggleLike}
        onKeyPress={onKeyPress}
        sendComment={(comment) => {
          this.props.sendComment({
            authorization: this.props.authorization,
            postId: id,
            comment
          });
        }}
      />
    );
  }
}

PostContainer.propTypes = {
  id: PropTypes.string.isRequired,
  user: PropTypes.shape({
    id: PropTypes.string.isRequired,
    avatar: PropTypes.string,
    username: PropTypes.string.isRequired
  }).isRequired,
  files: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      url: PropTypes.string.isRequired
    })
  ).isRequired,
  likeCount: PropTypes.number.isRequired,
  isLiked: PropTypes.bool.isRequired,
  comments: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.string.isRequired,
      text: PropTypes.string.isRequired,
      user: PropTypes.shape({
        id: PropTypes.string.isRequired,
        username: PropTypes.string.isRequired
      }).isRequired
    })
  ).isRequired,
  caption: PropTypes.string.isRequired,
  location: PropTypes.string,
  createdAt: PropTypes.string.isRequired
};
const mapStateToProps = (state) => ({
  authorization: state.loginReducer.authorization
})
const mapDispatchToProps = (dispatch) => ({
  sendComment: ({authorization, postId, comment}) => {
    createCommentAndDispatchPost({ authorization, dispatch, postId, comment });
  }
});
export default connect(mapStateToProps, mapDispatchToProps)(PostContainer);
