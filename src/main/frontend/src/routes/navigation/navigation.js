// import './Login.css';
import {Outlet, Link} from "react-router-dom";
import {Fragment} from "react";


const Navigation = () => {

    return (
        <Fragment>
            <div>
                Navbar

            </div>
            <Outlet/>
        </Fragment>
    );
};

export default Navigation