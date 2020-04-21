import React from 'react';
import styled from 'styled-components';
import InputRef from '../../src/util/InputRef';
import { connect } from 'react-redux';

const Wrapper = styled.div`
        min-height: 80vh;
        display: flex;
        align-items: center;
        flex-direction: column;
    `;
const Box = styled.div`
    margin-top: 150px;
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
    span{
        font-size: 25px;
        color: gray;
        margin-bottom: 19px;
    }
    `;
class Signup extends React.Component {
    render() {
        let inputRef = new InputRef();
        let sendData = () => {
            this.props.tryLogin(inputRef.getInput().id, inputRef.getInput().password)
                .then(data => {
                    if(data.message == 'Success'){
                        this.props.history.push('/login');
                    }
                });
        }
        return (
            <Wrapper>
                <Box>
                    <span> 가입하기 </span>
                    <input type="text" placeholder="id" name="id" ref={inputRef.getRef("id")}></input>
                    <input type="password" placeholder="password" name="password" ref={inputRef.getRef("password")}></input>
                    <button onClick={sendData}>Sign Up</button>
                </Box>
            </Wrapper>
        );
    }
}
let mapDispatchToProps = (dispatch) => ({
    tryLogin: (id, password) => {
        return fetch('http://ec2-54-180-153-232.ap-northeast-2.compute.amazonaws.com:8080/user/join', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                userName:id, 
                password
            })
        })
            .then(response => response.json())
            .then(data => {
                console.log(data);
                return data;
            });
    }
})
export default connect(undefined, mapDispatchToProps)(Signup);