import React from 'react';
import {connect} from 'react-redux';
import serverapi from '../api/serverapi';
import InputRef from '../util/InputRef'

class upload extends React.Component {
    render() {
        // mapstatetoprops로 authorization을 가져온다
        // 나중에이건 헤더에 넣을 것임
        let inputRef = new InputRef();
        let sendData = () => {
            var data = new FormData();

            var input = document.querySelector('input[type="file"]')
            data.append('file', input.files[0]);
            const { caption } = inputRef.getInput();
            console.log(caption);
            data.append('caption', caption);
            
            serverapi.createPost(this.props.authorization, data);
        };
        return (
            <>
                <input type="file"></input>
                <input type="text" name="caption" ref={inputRef.getRef("caption")}></input>
                <button onClick={sendData}>보내기</button>
            </>
        );
    }
}
let mapStateToProps = (state) => ({
    authorization: state.loginReducer.authorization
});

export default connect(mapStateToProps)(upload);
