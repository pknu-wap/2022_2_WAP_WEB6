// import './Login.css';
import { Outlet, useLocation } from "react-router-dom";
import { Fragment, useEffect } from "react";
import {
  NavigationContainer,
  NavLinks,
  NavLink,
  LogoContainer,
  imgContainer,
  ImgContainer,
} from "./navigation.styles";
import ToastLogo from "../../assets/Toast_logo.png";
import { useNavigate } from "react-router-dom";
import Logo from "../../assets/Toast_logo.png";

const Navigation = () => {
  // const {currentUser} = useContext(UserContext);
  let navigate = useNavigate();
  const signOutUser = () => {
    localStorage.clear();
    window.location.replace("/");
  };

  const currentPath = "/";
  let location = useLocation();
  const logoHandler = () => {
    if (currentPath == location.pathname) window.location.reload();
    else navigate("/");
  };
  //console.log(currentPath == location.pathname);

  return (
    <Fragment>
      <NavigationContainer>
        <LogoContainer onClick={logoHandler}>
          <ImgContainer src={Logo} />
        </LogoContainer>
        <NavLinks>
          <NavLink to="/allDebate">모든토론</NavLink>
          <NavLink to="/mypage">마이페이지</NavLink>
          {localStorage.getItem("username") ? (
            <span className="nav-link" onClick={signOutUser}>
              로그아웃
            </span>
          ) : (
            <NavLink to="/login">로그인</NavLink>
          )}
          {/*<CartIcon/>*/}
        </NavLinks>
      </NavigationContainer>
      <Outlet />
    </Fragment>
  );
};

export default Navigation;
