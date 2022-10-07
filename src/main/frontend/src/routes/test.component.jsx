import React, { useState, useEffect } from 'react';
import axios from 'axios'

function Test() {

    return (
        <div>
            {/*post 방식으로 /test/create 에게 전달*/}
            <form action="/test/create" method="post">
                <input name="title" type="text"/>
                    <textarea name="content"></textarea>
                    <button type="submit" >제출</button>
            </form>
        </div>
    );
}

export default Test;

