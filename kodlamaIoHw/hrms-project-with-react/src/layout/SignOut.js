import React from 'react'
import { Button, Menu } from 'semantic-ui-react'

export default function SignOut({signIn}) {
    return (
        <div>
            <Menu.Item>
            <Button onClick={signIn} success>Sign In</Button>
            <Button danger style={{marginLeft:"1em"}}>Sign Out</Button>
            </Menu.Item>
        </div>
    )
}
