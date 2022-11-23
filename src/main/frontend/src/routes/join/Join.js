import './Join.css';
import {useState} from 'react';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'
import axios from "axios";

function Join() {
    let navigate = useNavigate()

    const [id, setId] = useState("");
    const [password, setPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");
    const [email, setEmail] = useState("");

    const onIDHandler = (event) => {

        setId(event.currentTarget.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    };

    const onConfirmPasswordHandler = (event) => {
        setConfirmPassword(event.currentTarget.value);
    };

    const onNameHandler = (event) => {
        setEmail(event.currentTarget.value);
    };


    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            await axios({
                    method: 'post',
                    url: 'http://localhost:8080/user/register',
                    data: {
                        "userName": id,
                        "password": password,
                        "email":email
                    }
                }
            ).then((data) => {
                if(data.status===200){ // 성공시
                    console.log("성공!!")
                    // props.setUser(response.data);
                    // props.history.push('/dashboard');
                } else{
                    console.log("예상치 못한 오류!!")
                }
            });
        } catch (error) {
            console.log(error)

        }

    };


    return (
        <div className="Join">
            <div className="Join_Box">
                <div>
                    <p id="id_text">🍞아이디</p>
                    <input type="text" id="id_iput"
                           value={id} onChange={onIDHandler}
                           placeholder="아이디를 입력하세요"/>
                </div>
                <div>
                    <p id="pass_text">🍞비밀번호</p>
                    <input type="password" id="password_iput2"
                           value={password} onChange={onPasswordHandler}
                           placeholder="비밀번호를 입력하세요"></input>
                </div>
                <div>
                    <p id="pass_text">🍞비밀번호 확인</p>
                    <input type="password" id="password_iput3"
                           value={confirmPassword} onChange={onConfirmPasswordHandler}
                           placeholder="비밀번호를 재 입력하세요"></input>
                    {password !== confirmPassword ?
                        <p id="notice">* 비밀번호가 일치하지 않습니다.</p>
                        : null
                    }
                </div>
                <div>
                    <p id="pass_text">🍞이메일</p>
                    <input type="email" id="name_iput"
                           value={email} onChange={onNameHandler}
                           placeholder="이름을 입력하세요"/>
                </div>
                <div>
                    <button className="jo_button2" onClick={handleSubmit}>회원가입</button>
                </div>
            </div>
        </div>
    );
}

export default Join;
  