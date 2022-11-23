import './Login.css';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'

function Login() {
    let navigate = useNavigate()

    return (
        <div className="Login">
            <div>
                <div className="Login_Box">
                    <div>
                        <p id="id_text">🍞아이디</p>
                        <input type="text" id="id_iput"
                               placeholder="아이디를 입력하세요"/>
                    </div>
                    <div>
                        <p id="pass_text">🍞비밀번호</p>
                        <input type="password" id="password_iput"
                               placeholder="비밀번호를 입력하세요"></input>
                    </div>
                </div>
                <div>
                    <button className="lo_button">로그인</button>
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