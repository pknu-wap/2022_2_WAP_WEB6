// import './Login.css';
import {Outlet} from "react-router-dom";
import {Fragment} from "react";
import {NavigationContainer, NavLinks, NavLink, LogoContainer, imgContainer, ImgContainer} from "./navigation.styles";
import ToastLogo from "../../assets/Toast_logo.png";
import {useNavigate} from 'react-router-dom'
import Logo from '../../assets/Toast_logo.png';

const Navigation = () => {
    // const {currentUser} = useContext(UserContext);
    let navigate = useNavigate()
    const signOutUser = () => {
        localStorage.clear()
        window.location.replace('/')

    };

    return (
        <Fragment>

            <NavigationContainer>

                <LogoContainer to='/'>
                    <ImgContainer src={Logo}/>
                </LogoContainer>
                <NavLinks>
                    <NavLink to='/allDebate'>
                        모든토론
                    </NavLink>
                    {
                        localStorage.getItem('username') ? (
                            <span className='nav-link' onClick={signOutUser} >로그아웃</span>
                        ): (
                            <NavLink to='/login'>
                            로그인
                        </NavLink>)
                    }
                    {/*<CartIcon/>*/}
                </NavLinks>
            </NavigationContainer>
            <Outlet/>
        </Fragment>
    );
};

export default Navigation