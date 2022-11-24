import './Login.css';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'
import axios from "axios";
import {useState} from "react";

function Login() {
    let navigate = useNavigate()

    const [id, setId] = useState("");
    const [password, setPassword] = useState("");

    const onIdHandler = (event) => {
        setId(event.currentTarget.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value);
    };


    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log("fuck")
        try {
            await axios({
                    method: 'post',
                    url: 'http://localhost:8080/user/login',
                    data: {
                        "userName": id,
                        "password": password,
                    }
                }
            ).then((data) => {
                if (data.status === 200) { // 성공시
                    console.log(data)
                    console.log("성공!!")
                    localStorage.clear()
                    localStorage.setItem('id', data.data.user.id)
                    localStorage.setItem('username', data.data.user.username)
                    localStorage.setItem('token', data.data.jwtToken)

                    window.location.replace('/')
                } else if (data.data == "sameIdExist") {
                    alert("중복된 ID 입니다. 다시 입력해주세요!!")
                } else {
                    console.log("예상치 못한 오류!!");
                }
            });
        } catch (error) {
            console.log(error)

        }

    };

    return (
        <div className="Login">
            <div>
                <div className="Login_Box">
                    <div>
                        <p id="id_text">🍞아이디</p>
                        <input type="text" id="id_iput"
                               value={id} onChange={onIdHandler}
                               placeholder="아이디를 입력하세요"/>
                    </div>
                    <div>
                        <p id="pass_text">🍞비밀번호</p>
                        <input type="password" id="password_iput"
                               value={password} onChange={onPasswordHandler}
                               placeholder="비밀번호를 입력하세요"></input>
                    </div>
                </div>
                <div>
                    <button onClick={handleSubmit} className="lo_button">로그인</button>
                    <button onClick={() => {
                        navigate('/join')
                    }} className="jo_button">회원가입
                    </button>
                </div>
            </div>
        </div>
    )
}

export default Login;