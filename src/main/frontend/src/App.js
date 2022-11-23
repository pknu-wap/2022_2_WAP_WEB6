import './App.css';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'
import Login from './routes/login/Login.js';
import Join from './routes/join/Join.js';
import Navigation from "./routes/navigation/navigation";
import Home from "./routes/home/home";

function App() {  
  // let navigate = useNavigate()

  return (
    <div className="App">
      {/*<div>*/}
      {/*  <img onClick={()=>{navigate('/')}} className="Toast_logo" src="./Toast_logo.png"/>*/}
      {/*</div>*/}

        <Routes>
            <Route path='/' element={<Navigation/>}>
                <Route index={true} element={<Home/>}/> {/* index=true이므로 기본*/}
                <Route path="login" element={ <Login/>} />
                <Route path="join" element={ <Join/>} />
            </Route>


        </Routes>


    </div>
  );
}

export default App;