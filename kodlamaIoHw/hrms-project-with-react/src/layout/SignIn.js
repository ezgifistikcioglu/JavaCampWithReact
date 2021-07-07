import React from 'react'
import { Menu,Image, Dropdown  } from 'semantic-ui-react'

export default function SignIn({signOut}) {
    return (
        <div>
            <Menu.Item>
            <Image avatar spaced="right" src="https://avatars.githubusercontent.com/u/18142453?v=4"></Image>
            <Dropdown pointing="top left" text="Ezgi">
                <Dropdown.Menu>
                    <Dropdown.Item  text = "My infos" icon = "info"/>
                    <Dropdown.Item onClick={signOut} text = "Log out" icon = "sign-out"></Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
            </Menu.Item>
        </div>
    )
}
