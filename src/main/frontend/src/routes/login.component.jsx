import React, { useState, useEffect } from 'react';
import axios from 'axios'

function Login() {

    return (
        <div>
            <form action="" method="post">


                    <div>ID</div>
                    <input type="text" placeholder="Enter Username" name="uname" required/>
                    <div>PW</div>

                    <input type="password" placeholder="Enter Password" name="psw" required/>

                    <button type="submit">Login</button>
                    <button type="submit">회원가입</button>



            </form>
        </div>
    );
}

export default Login;