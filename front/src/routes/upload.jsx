import React from 'react';
import { connect } from 'react-redux';
import serverapi from '../api/serverapi';
import InputRef from '../util/InputRef'
import styled from 'styled-components';

const Background = styled.div`
    width: 100%;
    height: 100%;
    display:flex;
    justify-content: center;
    align-items: center;
`;
const Bigbox = styled.div`
    width: 700px;
    border: ${props => props.theme.boxBorder};
    background-color: white;
    display: flex;
    flex-direction: column;
    justify-content: left;
    align-items: center;
    input[type="file"]{
        position: absolute; 
        width: 1px; 
        height: 1px; 
        padding: 0; 
        margin: -1px; 
        overflow: hidden; 
        clip:rect(0,0,0,0); 
        border: 0;
    }
    button{
        border-radius: 10px;
        width: 30%;
        height: 30px;
        border: ${props => props.theme.boxBorder};
        margin-top: 10px;
        margin-bottom: 20px;
    }
`;
const CaptionInput = styled.div`
    display: flex;
    justify-content: center;
    margin-top: 10px;
    margin-bottom: 10px;
    width: 100%;
    input{
        width: 25%;
        margin-left: 10px;
        border-radius: 5px;
        border: ${props => props.theme.boxBorder};
    }
    span{

    }
`;
const ImageWhenNotUploaded = styled.div`
    width: 100%;
    height: 500px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-align: center;

    label{
        width: 100%;
        height: 100%;
        background-position: center center;
        background-size: cover;

        display:flex;
        justify-content: center;
        align-items: center;
    }

    i{
        cursor: pointer;
        font-size: 70px;
    }
`;

class upload extends React.Component {
    constructor() {
        super();
        this.state = {
            imageNotYetRegistered: true,
            registeredImageUrl: null
        };
    }
    render() {
        let inputRef = new InputRef();
        let sendData = () => {
            // file, caption 데이터 가져오기
            var input = document.querySelector('input[type="file"]');
            const { caption } = inputRef.getInput();
            // 데이터 담기
            var data = new FormData();
            data.append('file', input.files[0]);
            data.append('caption', caption);
            // 보내기
            // 성공시, 초기화
            serverapi.createPost(this.props.authorization, data)
                .then(response => {
                    if (response.message == 'Success') {
                        this.setState({
                            imageNotYetRegistered: true,
                            registeredImageUrl: null
                        });
                    }
                });
        };
        let onFileChange = () => {
            let uploadedFile = document.querySelector('input[type="file"]').files[0];
            if (uploadedFile == undefined) {
                return;
            }

            this.setState({
                imageNotYetRegistered: false,
                registeredImageUrl: uploadedFile
            });
        };


        const { imageNotYetRegistered, registeredImageUrl } = this.state;
        if (imageNotYetRegistered) {
            // 올려주세요 띄우기
            if(document.querySelector('label'))
                document.querySelector('label').style.backgroundImage = `url()`
        } else {
            // 등록된 이미지 띄우기
            let uploadedFile = document.querySelector('input[type="file"]').files[0];
            const fileReader = new FileReader();
            fileReader.onload = () => {
                document.querySelector('label').style.backgroundImage = `url(${fileReader.result})`;
            };
            fileReader.readAsDataURL(uploadedFile);
        }

        return (
            <Background>
                <Bigbox>
                    <ImageWhenNotUploaded>
                    <label for="imageUpload">
                        { imageNotYetRegistered ? <i class="far fa-image"></i> : null}
                    </label>
                    </ImageWhenNotUploaded>
                    <input type="file" id="imageUpload" onChange={onFileChange}></input>
                    <CaptionInput>
                        <span>Caption: </span>
                        <input type="text" name="caption" ref={inputRef.getRef("caption")}></input>
                    </CaptionInput>
                    <button onClick={sendData}>보내기</button>
                </Bigbox>
            </Background>
        );
    }
}
let mapStateToProps = (state) => ({
    authorization: state.loginReducer.authorization
});

export default connect(mapStateToProps)(upload);
