import React, { useState } from "react";
import "./DebateHeader.css";
import Modal from "../Modal/Modal";
import Logo from "../../assets/Toast_logo.png";

function DebateHeader(props) {
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };
  console.log(props.title);

  return (
    <>
      <header className="header">
        <a>
          <img src={Logo} />
        </a>
        <span>{props.debate}</span>
      </header>
      <div className="HeaderBtn">
        <button onClick={openModal}>주제 생성</button>
      </div>
      {modalOpen && (
        <Modal
          open={modalOpen}
          close={closeModal}
          type={props.type}
          title={props.title}
        />
      )}
    </>
  );
}

export default DebateHeader;
