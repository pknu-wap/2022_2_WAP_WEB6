import "./Join.css";
import { useState } from "react";
import { Routes, Route, Link, useNavigate } from "react-router-dom";
import axios from "axios";
import * as config from "../../config";

function Join() {
  let navigate = useNavigate();

  //초기값 세팅
  const [id, setId] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  //const [email, setEmail] = useState("");

  //오류메세지 저장
  const [idMessage, setIdMessage] = useState("");
  const [passwordMessage, setPasswordMessage] = useState("");
  const [passwordConfirmMsg, setPasswordConfirmMsg] = useState("");

  //유효성 검사
  const [isId, setIsId] = useState(false);
  const [isPassword, setIsPassword] = useState(false);
  const [isPasswordConfirm, setIsPasswordConfirm] = useState(false);

  const onIdHandler = (e) => {
    const currentId = e.target.value;
    setId(currentId);
    const idRegExp = /^[a-zA-z0-9]{4,12}$/; //한글 제외

    if (!idRegExp.test(currentId)) {
      setIdMessage("4-12 사이 대소문자 또는 숫자만 입력하시오");
      setIsId(false);
    } else {
      setIdMessage("사용 가능 아이디입니다.");
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

  const onConfirmPasswordHandler = (e) => {
    const curPasswordConfirm = e.target.value;
    setConfirmPassword(curPasswordConfirm);

    if (password !== curPasswordConfirm) {
      setPasswordConfirmMsg("비밀번호가 일치하지 않습니다.");
      setIsPasswordConfirm(false);
    } else {
      setPasswordConfirmMsg("");
      setIsPasswordConfirm(true);
    }
  };

  const printErr = () => {
    alert("올바르지 않은 입력입니다.");
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      await axios({
        method: "post",
        url: "http://" + config.URL + "/user/register",
        data: {
          userName: id,
          password: password,
          // "email":email
        },
      }).then((data) => {
        if (data.status === 200 && data.data != "sameIdExist") {
          // 성공시
          console.log("성공!!");
          alert("회원가입 성공 로그인 해주세요");
          window.location.replace("/login");
        } else if (data.data == "sameIdExist") {
          alert("중복된 ID 입니다. 다시 입력해주세요!!");
        } else {
          console.log("예상치 못한 오류!!");
        }
      });
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <div className="Join">
      <div className="Join_Box">
        <div>
          <p id="id_text">🍞 아이디</p>
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
          <p id="pass_text">🍞 비밀번호</p>
          <input
            type="password"
            id="password_iput"
            value={password}
            onChange={onPasswordHandler}
            placeholder="비밀번호를 입력하세요"
          ></input>
          <p id="notice">{passwordMessage}</p>
        </div>
        <div>
          <p id="pass_text">🍞 비밀번호 확인</p>
          <input
            type="password"
            id="password_iput"
            value={confirmPassword}
            onChange={onConfirmPasswordHandler}
            placeholder="비밀번호를 재 입력하세요"
          ></input>
          {password !== confirmPassword ? (
            <p id="notice">{passwordConfirmMsg}</p>
          ) : null}
        </div>
        <div>
          {password == confirmPassword && isId && isPassword ? (
            <button className="jo_button2" onClick={handleSubmit}>
              회원가입
            </button>
          ) : (
            <button className="jo_button2" onClick={printErr}>
              모든 칸을 올바르게 입력해주세요
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

export default Join;
