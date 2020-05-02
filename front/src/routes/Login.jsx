import React from 'react';
import { Redirect } from 'react-router-dom';
import styled from 'styled-components';
import InputRef from '../../src/util/InputRef';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { tryLoginAndDispatch } from '../actions/loadActions';

const Wrapper = styled.div`
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
    `;
const Box = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: left;
    align-items: center;
    border-radius: 0px;
    width: 100%;
    max-width: 350px;
    border: 1px solid #e6e6e6;
    border-radius: 4px;
    background-color: white;
    padding: 40px;
    padding-top: 20px;
    padding-bottom: 30px;
    margin-bottom: 15px;

    input{
        padding: 8px;
        border: 1px solid #e6e6e6;
        border-radius:3px;
        background-color: #f1f1f1;
        width: 100%;
        margin-bottom: 10px;
        font-weight: 100;	
        opacity: 0.6;
    }
    input:nth-child(3){
        margin-bottom: 20px;
    }
    button{
        width: 100%;
        border: 0;
        border-radius:3px;
        color: white;
        font-weight: 600;
        background-color: rgb(204, 217, 253);
        text-align: center;
        padding: 7px 0px;
        font-size: 14px;
        cursor: pointer;
    }
    img{
        width: 250px;
        margin-bottom: 15px;
    }
    `;
class Login extends React.Component {
    render() {
        const { authorization } = this.props;
        
        // 이미 로그인이 되어있다면, 라우터 다시 타게 하기
        if (authorization) {
            return (<Redirect to="/"></Redirect>);
        }

        let inputRef = new InputRef();
        let sendData = () => {
            const { id, password } = inputRef.getInput();
            this.props.tryLoginAndDispatch(id, password);
        };
        return (
            <Wrapper>
                <Box>
                    <img src="http://snsmanual.com/upload/image/4-%EC%9D%B8%EC%8A%A4%ED%83%80%EA%B7%B8%EB%9E%A8-%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%80-%ED%9D%91%EB%B0%B1-%EB%A1%9C%EA%B3%A0_1547000966.png"></img>
                    <input type="text" placeholder="id" name="id" ref={inputRef.getRef("id")}></input>
                    <input type="password" placeholder="password" name="password" ref={inputRef.getRef("password")}></input>
                    <button onClick={sendData}>로그인</button>
                </Box>
                <Link to="/signup">signup</Link>
            </Wrapper>
        );
    }
}
let mapStateToProps = (state) => ({
    authorization: state.loginReducer.authorization
});
let mapDispatchToProps = (dispatch) => ({
    tryLoginAndDispatch: tryLoginAndDispatch(dispatch)
});

export default connect(mapStateToProps, mapDispatchToProps)(Login);