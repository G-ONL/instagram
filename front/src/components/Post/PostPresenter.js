import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";
import TextareaAutosize from "react-autosize-textarea";
import FatText from "../FatText";
import Avatar from "../Avatar";
import { HeartFull, HeartEmpty, Comment as CommentIcon } from "../Icons";
import InputRef from '../../util/InputRef';

const Post = styled.div`
  ${props => props.theme.whiteBox};
  width: 100%;
  max-width: 600px;
  user-select: none;
  margin-bottom: 25px;
  a {
    color: inherit;
  }
`;

const Header = styled.header`
  padding: 15px;
  display: flex;
  align-items: center;
`;

const UserColumn = styled.div`
  margin-left: 10px;
`;

const Location = styled.span`
  display: block;
  margin-top: 5px;
  font-size: 12px;
  opacity: 0.8;
`;

const Pictures = styled.div`
  position: relative;
  padding-bottom: 100%;
  display: flex;
  flex-direction: column;
  align-items: stretch;
  flex-shrink: 0;
`;

const File = styled.div`
  max-width: 100%;
  width: 100%;
  height: 600px;
  position: absolute;
  top: 0;
  background-image: url(${props => props.src});
  background-size: cover;
  background-position: center;
  opacity: ${props => (props.showing ? 1 : 0)};
  transition: opacity 0.5s linear;
`;

const Button = styled.span`
  cursor: pointer;
`;

const Meta = styled.div`
  padding: 15px;
`;

const Buttons = styled.div`
  ${Button} {
    &:first-child {
      margin-right: 10px;
    }
  }
  margin-bottom: 10px;
`;

const Timestamp = styled.span`
  font-weight: 400;
  text-transform: uppercase;
  opacity: 0.5;
  display: block;
  font-size: 12px;
  margin: 10px 0px;
  padding-bottom: 10px;
  border-bottom: ${props => props.theme.lightGreyColor} 1px solid;
`;

const Textarea = styled.input`
  border: none;
  width: 100%;
  resize: none;
  font-size: 14px;
  &:focus {
    outline: none;
  }
`;

const Comments = styled.ul`
  margin-top: 10px;
`;

const Comment = styled.li`
  margin-bottom: 7px;
  span {
    margin-right: 5px;
  }
`;

const Caption = styled.div`
  margin: 10px 0px;
`;

const Picture = styled.div`
  max-width: 100%;
  width: 100%;
  height: 600px;
  background-image: url(${props => props.src});
  background-size: cover;
  background-position: center;
  border-top: ${props => props.theme.boxBorder};
  border-bottom: ${props => props.theme.boxBorder};
`;
class PostPresenter extends React.Component{
  render(){
    const {
      user: { userName, avatarUrl, location },
      postPictures,
      isLiked, // no
      likeCount,
      createdDate,
      toggleLike,
      comments,
      caption,
      sendComment
    } = this.props;
    
    let inputRef = new InputRef();
    const getCommentAndSend = () => {
      const { comment } = inputRef.getInput("comment");
      if(comment == undefined || comment == '' || comment == null)
        return;
      sendComment(comment);
    };
    return(
      <Post>
        <Header>
          <Avatar size="sm" url={avatarUrl} />
          <UserColumn>
            <Link to={`/${userName}`}>
              <FatText text={userName} />
            </Link>
            <Location>{location}</Location>
          </UserColumn>
        </Header>
        <Picture src={postPictures[0].pictureUrl}/>
        <Meta>
          <Buttons>
            <Button onClick={toggleLike}>
              {isLiked ? <HeartFull /> : <HeartEmpty />}
            </Button>
            <Button>
              <CommentIcon />
            </Button>
          </Buttons>
          <FatText text={likeCount === 1 ? "1 like" : `${likeCount} likes`} />
          <Caption>
            <FatText text={userName} /> {caption}
          </Caption>
          {comments && (
            <Comments>
              {comments.map((comment) => (
                <Comment key={comment.id}>
                  <FatText text={comment.userName} />
                  {comment.comment}
                </Comment>
              ))}
            </Comments>
          )}
          <Timestamp>{createdDate}</Timestamp>
          <Textarea
            type="text"
            onKeyPress={async (event) => {
              const {which} = event;
              if (which === 13) {
                event.preventDefault();
                try {
                  getCommentAndSend();
                } catch {
                }
              }
            }}
            ref={inputRef.getRef()}
            name="comment"
            placeholder={"Add a comment..."}
          />
        </Meta>
      </Post>
    );
  }
}
export default PostPresenter;
