import React from "react";
import styled from "styled-components";
import PropTypes from "prop-types";

const getSize = size => {
  let number;
  if (size === "sm") {
    number = 30;
  } else if (size === "md") {
    number = 50;
  } else if (size === "lg") {
    number = 150;
  }
  return `
        width:${number}px;
        height:${number}px;
        `;
};

const Container = styled.div`
  ${props => getSize(props.size)}
  background-image:url(${props => props.url});
  background-size:cover;
  border-radius:50%;
`;

const NoImageContainer = styled.div`
  ${props => getSize(props.size)}
  background-image:url(${props => props.url});
  background-color: blue;
  border-radius:50%;
`;

const Avatar = ({ size = "sm", url, className }) => {
  if (url == undefined){
    return <NoImageContainer className={className} size={size} url="https://lh3.googleusercontent.com/proxy/hBrfUy2AWbAz8bq9j_hopEKUHkSEcRj3ggtPBdGd9wQDJHg31M1Tj7T9pFZ01mgGvVz8hDKIJqB_HiFSDLomkRAm5Yxi13exavkjwPxLsbcGzBR-WJ3hVpetBja9fNzKa1KdOKV0bLWfND4FuaLpztEwUdjn-i2y1gpHmLu_lA"/>;
  }
  return <Container className={className} size={size} url={url} />;
};

Avatar.propTypes = {
  size: PropTypes.oneOf(["sm", "md", "lg"]),
  url: PropTypes.string.isRequired
};

export default Avatar;
