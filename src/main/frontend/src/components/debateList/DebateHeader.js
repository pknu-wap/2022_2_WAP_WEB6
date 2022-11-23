import React, { useState } from "react";
import "./DebateHeader.css";
import Modal from "./Modal";

function DebateHeader(props) {
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <>
      <header className="header">
        <a>
          <img src={"/image/logo-cut.png"}></img>
        </a>
        <span>{props.debate}</span>
      </header>
      <div className="HeaderBtn">
        <button onClick={openModal}>주제 생성</button>
      </div>
      {modalOpen && (
        <Modal open={modalOpen} close={closeModal} type={props.type} />
      )}
    </>
  );
}

export default DebateHeader;
