import React from 'react'
import {Image, Dropdown, Menu } from 'semantic-ui-react'

export default function SignedIn({signOut}) {
    return (
        <div>
            <Menu.Item>
                <Image avatar spaced="right" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSNPSDB8nreBggf8eWxOfFofhCxseDuCHQAjQ&usqp=CAU"></Image>
            <Dropdown pointing="top left" text="Ezgi">
                <Dropdown.Menu>
                    <Dropdown.Item text = "My infos" icon = "info"></Dropdown.Item>
                    <Dropdown.Item onClick = {signOut} text = "Log out" icon = "sign-out"></Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
            </Menu.Item>
        </div>
    )
}
