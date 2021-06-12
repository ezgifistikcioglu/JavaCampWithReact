import React from 'react'
import { Button, Container, Menu } from 'semantic-ui-react'
import Employer from './Employer'


export default function Navi() {
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

                        <Menu.Item>
                            <Button primary>Sign Up</Button>
                        </Menu.Item>
                    </Menu.Menu>
                </Container>
            </Menu>
        </div>
    )
}
