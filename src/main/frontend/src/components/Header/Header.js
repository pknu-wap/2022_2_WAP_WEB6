import React, { useState } from "react";
import "./Header.css";
import Modal from "../Modal/Modal";

function Header(props) {
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
                <span>{props.debate}</span>
            </header>
        </>
    );
}

export default Header;
