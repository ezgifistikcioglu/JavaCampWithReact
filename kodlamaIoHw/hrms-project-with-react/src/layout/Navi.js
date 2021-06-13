import React, { useState } from 'react'
import { Button, Container, Menu } from 'semantic-ui-react'
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
