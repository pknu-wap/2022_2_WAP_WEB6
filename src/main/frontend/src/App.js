import './App.css';
import {Routes, Route} from 'react-router-dom'
import Login from './routes/login/Login.js';
import Join from './routes/join/Join.js';
import Navigation from "./routes/navigation/navigation";
import Home from "./routes/home/home";

function App() {  

  return (
    <div className="App">

        <Routes>
            <Route path='/' element={<Navigation/>}>
                <Route index={true} element={<Home/>}/>
                <Route path="login" element={ <Login/>} />
                <Route path="join" element={ <Join/>} />
            </Route>


        </Routes>


    </div>
  );
}

export default App;