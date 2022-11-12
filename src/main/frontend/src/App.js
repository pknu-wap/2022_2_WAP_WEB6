import './App.css';
import {Routes, Route, Link, useNavigate} from 'react-router-dom'
import Login from './Login.js';
import Join from './Join.js';

function App() {  
  let navigate = useNavigate()

  return (
    <div className="App">
      <div>
        <img onClick={()=>{navigate('/')}} className="Toast_logo" src="./Toast_logo.png"/>
      </div>
      <Routes>
        <Route path="/login" element={ <Login/>} />
      </Routes>

      <Routes>
        <Route path="/join" element={ <Join/>} />
      </Routes>
    </div>
  );
}

export default App;