import React from 'react'
import { Menu } from 'semantic-ui-react'
import { Dropdown } from 'semantic-ui-react'

export default function Categories() {
    return (
        <div>
            <Menu secondary vertical>
                <Menu.Item
                    name='Home'
                />
                <Menu.Item
                    name='Advertisement'
                />
                <Menu.Item
                    name='Job Position'
                />
                <Menu.Item
                    name='CV'
                />
                <Menu.Item
                    name='Employer'
                />
                <Menu.Item
                    name='Job Seeker'
                />
                <Menu.Item
                    name='Settings'
                />
            </Menu>
        </div>
    )
}
