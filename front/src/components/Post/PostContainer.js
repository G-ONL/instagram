import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";
import PostPresenter from "./PostPresenter";
import { connect } from 'react-redux';
import serverapi from '../../api/serverapi'
import { CREATE_COMMENT_SUCCESS } from '../../actions/actionTypes';
import {createCommentAndDispatchPost, toggleLikeButton} from '../../actions/loadActions';

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

    const onKeyPress = async event => {
      const { which } = event;
      if (which === 13) {
        event.preventDefault();
        try {
        } catch {
        }
      }
    };
    return (
      <PostPresenter
        user={user}
        postPictures={postPictures}
        likeCount={likeCount}
        caption={caption}
        isLiked={isLiked}
        comments={comments}
        createdDate={createdDate}
        toggleLike={()=>{
          this.props.toggleLikeButton({
            authorization: this.props.authorization,
            postId: id,
          });
        }}
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
  },
  toggleLikeButton: ({ authorization, postId }) => {
    toggleLikeButton({ authorization, dispatch, postId });
  }
});
export default connect(mapStateToProps, mapDispatchToProps)(PostContainer);
