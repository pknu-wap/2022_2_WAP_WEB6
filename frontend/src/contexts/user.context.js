import {createContext, useState, useEffect} from "react";


// actual value you want to access
export const UserContext = createContext({ //defaultValue
    currentUser: null,
    setCurrentUser: () => null,
    currentToken: null,
    setCurrentToken: () => null,
});

// .Provider 컴포넌트이다. 필요한 값이 있으면 해당 컴포넌트를 둘러싼다.
export const UserProvider = ({children}) => {
    const [userId, setUserId] = useState(null);

    const [currentUser, setCurrentUser] = useState(null);
    const [currentToken, setCurrentToken] = useState(null);

    const value = {
        userId,
        currentUser,
        setCurrentUser,
        currentToken,
        setCurrentToken
    }

    // useEffect(()=> {
    //     const unsubscribe = onAuthStateChangedListener((user) => {
    //         if (user) {
    //             createUserDocumentFromAuth(user)
    //         }
    //         setCurrentUser(user);
    //     });
    //     return unsubscribe //complete을 하기 위해서 // clean up // stop listening
    // },[]);

    return <UserContext.Provider value={value}>{children}</UserContext.Provider>
};
