import React from "react";
import "./Modal.css";

function Date() {
  return (
    <div>
      <p>기간</p>
      <input type="date" name="userSetDate" />
    </div>
  );
}

const Modal = (props) => {
  const { open, close, type } = props;

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
          <main>
            <form>
              <div>
                <input type="text" name="topic" placeholder="주제" />
              </div>
              <div>
                <textarea
                  cols="55"
                  rows="10"
                  name="userExplan"
                  placeholder="설명"
                />
              </div>
              {type == "debate" && <Date />}
            </form>
          </main>
          <footer>
            <button>생성</button>
          </footer>
        </section>
      ) : null}
    </div>
  );
};

export default Modal;
