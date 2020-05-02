import React from 'react';
import { HashRouter as Router, Route, Switch } from "react-router-dom";
import styled, { ThemeProvider } from "styled-components";
import Theme from "../Styles/Theme";
import Feed from "../routes/Feed";
import Header from "./Header";
import Footer from "./Footer";
import GlobalStyles from "../Styles/GlobalStyles";
import { connect } from "react-redux";
import Login from "../routes/Login";
import Signup from "../routes/signup";
import upload from "../routes/upload";
import profile from "../routes/profile";

const Wrapper = styled.div`
  margin: 0 auto;
  max-width: ${props => props.theme.maxWidth};
  width: 100%;
  min-height: 700px;
  height: 100%;
`;

// 로그인이 되어 있지 않으면 로그인페이지로,
// 로그인이 되어 있다면 Feed 페이지로 갈 것
class App extends React.Component {
  render() {
    // 로그인이 되어있다면, Feed로 보내기
    if (this.props.authorization) {
      return (
        <ThemeProvider theme={Theme}>
          <>
            <GlobalStyles />
            <Router>
              <>
                <Header/>
                <Wrapper>
                  <Switch>
                    <Route exact path="/" component={Feed} />
                    <Route exact path="/upload" component={upload} />
                    <Route exact path="/profile/:userName" component={profile} />
                    <Route path="*" component={Feed} />
                  </Switch>
                <Footer />
                </Wrapper>
              </>
            </Router>
          </>
        </ThemeProvider>
      );
    }
    // 안 되어 있다면, 로그인페이지로 보내기
    return (
      <ThemeProvider theme={Theme}>
        <>
          <GlobalStyles />
          <Router>
            <Wrapper>
              <Switch>
                <Route exact path="/login" component={Login} />
                <Route exact path="/signup" component={Signup} />
                <Route path="*" component={Login} />
              </Switch>
            </Wrapper>
          </Router>
        </>
      </ThemeProvider>
    );
  }
}
const mapStateToProps = (state) => ({
  authorization: state.loginReducer.authorization
});
export default connect(mapStateToProps)(App);