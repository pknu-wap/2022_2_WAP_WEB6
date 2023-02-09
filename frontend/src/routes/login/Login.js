import "./Login.css";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import * as config from "../../config";

function Login() {
  let navigate = useNavigate();

  const [id, setId] = useState("");
  const [password, setPassword] = useState("");

  //오류 msg
  const [idMessage, setIdMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");

  //유효성 검사
  const [isId, setIsId] = useState(false);
  const [isPassword, setIsPassword] = useState(false);

  const onIdHandler = (e) => {
    const currentId = e.currentTarget.value;
    setId(currentId);
    const idRegExp = /^[a-zA-z0-9]{4,12}$/; //한글 제외

    if (!idRegExp.test(currentId)) {
      setIdMessage("4-12 사이 대소문자 또는 숫자만 입력하시오");
      setIsId(false);
    } else {
      setIdMessage("");
      setIsId(true);
    }
  };

  const onPasswordHandler = (e) => {
    const currentPassword = e.target.value;
    setPassword(currentPassword);
    const passwordRegExp =
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/; //최소 8자, 하나의 이상의 대소문자 및 하나의 숫자, 하나의 특수문자

    if (!passwordRegExp.test(currentPassword)) {
      setPasswordMessage("최소 8자, 대소문자, 숫자, 특수문자가 하나 이상 필요");
      setIsPassword(false);
    } else {
      setPasswordMessage("");
      setIsPassword(true);
    }
  };

  //login submit 활성화 비활성화
  const [active, setActive] = useState(false);
  //   const checkIdPassWord = () => {
  //     if (id == "admin" || id == "user" || (isId && isPassword)) {
  //       setActive(true);
  //       console.log("올바름");
  //     } else {
  //       setActive(false);
  //       console.log("올바르지 않음");
  //     }
  //   };

  useEffect(() => {
    if (id == "admin" || id == "user" || (isId && isPassword)) {
      setActive(true);
    } else {
      setActive(false);
    }
  });

  const handleSubmit = async (event) => {
    event.preventDefault();
    //checkIdPassWord();

    //console.log(active);
    if (!active) {
      setIdMessage("4-12 사이 대소문자 또는 숫자만 입력하시오");
      setPasswordMessage("최소 8자, 대소문자, 숫자, 특수문자가 하나 이상 필요");
      alert("올바르지 않은 입력");
    } else {
      console.log("서버 전송");
      try {
        await axios({
          method: "post",
          url: "http://" + config.URL + "/user/login",
          data: {
            userName: id,
            password: password,
          },
        }).then((data) => {
          if (data.status === 200) {
            // 성공시
            console.log(data);
            console.log("성공!!");
            localStorage.clear();
            localStorage.setItem("id", data.data.user.id);
            localStorage.setItem("username", data.data.user.username);
            localStorage.setItem("token", data.data.jwtToken);

            window.location.replace("/");
          } else if (data.data == "sameIdExist") {
            alert("중복된 ID 입니다. 다시 입력해주세요!!");
          } else {
            console.log("예상치 못한 오류!!");
          }
        });
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <div className="Login">
      <div>
        <div className="Login_Box">
          <div>
            <p id="id_text">🍞아이디</p>
            <input
              type="text"
              id="id_iput"
              value={id}
              onChange={onIdHandler}
              placeholder="아이디를 입력하세요"
            />
            <p id="notice">{idMessage}</p>
          </div>
          <div>
            <p id="pass_text">🍞비밀번호</p>
            <input
              type="password"
              id="password_iput"
              value={password}
              onChange={onPasswordHandler}
              placeholder="비밀번호를 입력하세요"
            ></input>
            <p id="notice">{passwordMessage}</p>
          </div>
        </div>
        <div>
          <button onClick={handleSubmit} className="lo_button">
            로그인
          </button>
          <button
            onClick={() => {
              navigate("/join");
            }}
            className="jo_button"
          >
            회원가입
          </button>
        </div>
      </div>
    </div>
  );
}

export default Login;
