import './Join.css';
import { useState } from 'react';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'

function Join() {
    let navigate = useNavigate()

    const [ID, setID] = useState("");
    const [Password, setPassword] = useState("");
    const [ConfirmPassword, setConfirmPassword] = useState("");
    const [Name, setName] = useState("");

    const onIDHandler = (event) => {
        setID(event.currentTarget.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    };
    
    const onConfirmPasswordHandler = (event) => {
        setConfirmPassword(event.currentTarget.value);
    };

    const onNameHandler = (event) => {
        setName(event.currentTarget.value);
    };
    
    return (
      <div className="Join">
        <div className="Join_Box">
            <div>
                <p id="id_text" >🍞아이디</p>
                <input type="text" id="id_iput"
                value={ID} onChange={onIDHandler}
                placeholder="아이디를 입력하세요" />
            </div>
            <div>
                <p id="pass_text">🍞비밀번호</p>
                <input type="password" id="password_iput2"
                value={Password} onChange={onPasswordHandler}
                placeholder="비밀번호를 입력하세요"></input>
            </div>
            <div>
                <p id="pass_text">🍞비밀번호 확인</p>
                <input type="password" id="password_iput3"
                value={ConfirmPassword} onChange={onConfirmPasswordHandler}
                placeholder="비밀번호를 재 입력하세요"></input>
                {Password !== ConfirmPassword ?
                <p id="notice">* 비밀번호가 일치하지 않습니다.</p>
                :null
                }
            </div>
            <div>
                <p id="pass_text">🍞이름</p>
                <input type="text" id="name_iput"
                value={Name} onChange={onNameHandler}
                placeholder="이름을 입력하세요" />
            </div>
            <div>
                <button className="jo_button2">회원가입</button>
            </div>
        </div>
      </div>
    );
  }
  
  export default Join;
  