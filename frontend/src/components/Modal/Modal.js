import React, { useState, useRef, forwardRef } from "react";
import "./Modal.css";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { getId } from "../../userInfo/userInfo";
import * as config from "../../config";

import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { ko } from "date-fns/esm/locale";
import { FaCalendar } from "react-icons/fa";

/*Error => due-date: null*/
// function Calender(props) {
//   const [startDate, setStartDate] = useState(new Date());
//   let in2Weeks = new Date();
//   in2Weeks.setDate(in2Weeks.getDate() + 14);

//   return (
//     <>
//       <p>기간</p>
//       <div className="WrapDatePicker">
//         <DatePicker
//           name="date"
//           selected={startDate}
//           onChange={(date) => setStartDate(date)}
//           locale={ko}
//           dateFormat="- yyyy/MM/dd"
//           minDate={new Date()}
//           maxDate={in2Weeks}
//           ref={props.dateref}
//         />
//         {/* <FaCalendar /> */}
//       </div>
//     </>
//   );
// }

function Calender(props) {
  return (
    <div>
      <p>기간</p>
      <input type="date" name="userSetDate" ref={props.dateref} />
    </div>
  );
}

const Modal = (props) => {
  const { booknum } = useParams();
  const history = useNavigate();

  const { open, close, type } = props;

  // const topicRef = useRef(null);
  // const explanRef = useRef(null);
  // const dateRef = useRef(null);

  // const debateId = useRef(5);
  // console.log(debateId.current);

  const handleSubmit = async (event) => {
    if (getId() == null) {
      alert("로그인을 해주세요!");
      window.location.replace("/login");
    } else {
      try {
        await axios({
          method: "post",
          url: "http://" + config.URL + "/api/userId/1/bookId/" + booknum,
          data: {
            id: 0, //dataId.current
            pro_con: type == "debate" ? true : false,
            topic: inutRef.current[0].value,
            due_date: inutRef.current[2].value,
            reason: inutRef.current[1].value, //explan == reason
          },
        }).then((data) => {
          if (data.status === 200) {
            // 성공시
            //window.location.replace("/");
            //alert("생성 완료");
            window.location.reload();
            // history(
            //   type === "free"
            //     ? `/detailfree/${props.title}/${topicRef.current.value}`
            //     : `/detaildebate/${props.title}/${topicRef.current.value}/debateId/${debateId.current}/book/${booknum}`
            // );
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

  function onSubmit(e) {
    e.preventDefault();
    handleSubmit();
  }

  const inutRef = useRef([]);

  const [inputs, setInputs] = useState({
    topic: "",
    explan: "",
    date: "",
  });
  const { topic, explan, date } = inputs;

  const validTopic = topic.length <= 20;
  const validExpln = explan.length <= 100;

  const handleChange = (e) => {
    setInputs({
      ...inputs,
      [e.target.name]: e.target.value,
    });
  };

  const handleClick = () => {
    if (!validTopic) {
      alert("유효하지 않은 주제"); //수정하기
      setInputs({
        ...inputs,
        topic: "",
      });
      inutRef.current[0].focus();
    } else if (!validExpln) {
      alert("유효하지 않은 설명");
      setInputs({
        ...inputs,
        explan: "",
      });
    }
  };

  const [content, setContent] = useState("");
  //console.log({ inutRef });
  console.log(content.length);

  return (
    //모달 열렸으면 openModal
    <div className={open ? "openModal modal" : "modal"}>
      {open ? (
        <section>
          <header>
            <button className="close" onClick={close}>
              &times;
            </button>
          </header>
          <form onSubmit={onSubmit}>
            <div>
              <input
                value={topic}
                onChange={handleChange}
                type="text"
                name="topic"
                placeholder="주제(20자 이하)"
                //ref={topicRef}
                ref={(el) => (inutRef.current[0] = el)}
              />
              {validTopic ? null : <span>유효하지 않은 id 입니다.</span>}
            </div>
            <div>
              <textarea
                value={content}
                onChange={(e) => {
                  handleChange(e);
                  setContent(e.target.value);
                }}
                cols="55"
                rows="10"
                name="userExplan"
                placeholder="설명(100자 이하)"
                //ref={explanRef}
                ref={(el) => (inutRef.current[1] = el)}
              />
              {validExpln ? null : <span>유효하지 않은 id 입니다.</span>}
            </div>

            {type == "debate" && (
              <Calender
                dateref={(el) => (inutRef.current[2] = el)}
                //dateref={dateRef}
              />
            )}
            <div className="formbtn">
              <button
                onClick={handleClick}
                disabled={topic.length < 1 || content.length < 1}
              >
                생성
              </button>
            </div>
          </form>
        </section>
      ) : null}
    </div>
  );
};

export default Modal;
