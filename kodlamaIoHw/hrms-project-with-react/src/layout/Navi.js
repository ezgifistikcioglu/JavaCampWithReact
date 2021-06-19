import React, { useState } from 'react'
import { Container, Menu } from 'semantic-ui-react'
import { NavLink } from "react-router-dom";
import Employer from './Employer'
import SignIn from './SignIn'
import SignOut from './SignOut'


export default function Navi() {
    const [isAuthenticated, setIsAuthenticated] = useState(true)

    function handleSignOut() {
        setIsAuthenticated(false)
    }

    function handleSignIn() {
        setIsAuthenticated(true)
    }

    return (
        <div>
            <Menu inverted fixed="top" size='massive'>
                <Container>
                    <Menu.Item
                     as = {NavLink} to="/"
                        name='home'
                    />
                    <Menu.Item
                        name='messages'
                    />

                    <Menu.Menu position='right'>
                        <Employer />
                        {isAuthenticated ? <SignIn signOut= {handleSignOut}/> : <SignOut signIn= {handleSignIn} />}

                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}
