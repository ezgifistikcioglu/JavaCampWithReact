import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'

export default function Categories() {
    return (
        <div>
            <Menu secondary vertical>
                <Menu.Item as = {NavLink} to="/"
                    name='Advertisement'
                />
                <Menu.Item as = {NavLink} to="/positions"
                    name='Job Position'
                />
                <Menu.Item as = {NavLink} to="/cvs"
                    name='CV'
                />
                <Menu.Item  as = {NavLink} to="/employers"
                    name='Employer'
                />
                <Menu.Item  as = {NavLink} to="/jobSeekers"
                    name='Job Seeker'
                />
                <Menu.Item as = {NavLink} to="/settings"
                    name='Settings'
                />
            </Menu>
        </div>
    )
}
