import {Routes, Route} from "react-router-dom";

import HelloWorld from "./routes/helloworld.component";
import Login from "./routes/login.component";
import Test from "./routes/test.component";

function App() {
    return (
        <Routes>
            <Route path='/'>
                <Route path='helloworld' element={<HelloWorld/>}/> {/*react spring 통신 예제 */}
                <Route path='login' element={<Login/>}/> {/*login 페이지*/}
                <Route path='test' element={<Test/>}/> {/*login 페이지*/}

                {/*<Route index={true} element={<Home/>}/> /!* index=true이므로 기본*!/*/}


            </Route>


        </Routes>
    );
}
export default App;
