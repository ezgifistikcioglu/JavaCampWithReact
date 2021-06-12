import React from 'react'
import { Dropdown } from 'semantic-ui-react'

export default function Employer() {
    return (
        <div>
            <Dropdown item text='Employer'>
                <Dropdown.Menu>
                    <Dropdown.Item>company name</Dropdown.Item>
                    <Dropdown.Item>telephone no</Dropdown.Item>
                    <Dropdown.Item>web address</Dropdown.Item>
                    <Dropdown.Divider />
                    <Dropdown.Item> Go to Cart</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </div>
    )
}
