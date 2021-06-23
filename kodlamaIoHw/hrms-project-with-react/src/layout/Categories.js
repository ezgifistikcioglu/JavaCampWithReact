import React from 'react'
import { NavLink } from 'react-router-dom'
import { Menu } from 'semantic-ui-react'
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Divider from '@material-ui/core/Divider';
import { orange } from '@material-ui/core/colors';
import { TextFormat } from '@material-ui/icons';
import { Typography } from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 360,
        backgroundColor: theme.palette.background.paper,
    },
}));

export default function Categories() {
    const classes = useStyles();
    return (
        <div>
            <List component="nav" className={classes.root} aria-label="categories">
                <ListItem button component={NavLink} to="/advertisementLists">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00'}}>Advertisement</Typography>} />
                </ListItem>
                <Divider/>
                <ListItem button component={NavLink} to="/positions">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00' }}>Job Position</Typography>} />
                </ListItem>
                <Divider/>
                <ListItem button component={NavLink} to="/cvs">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00' }}>CV</Typography>} />
                </ListItem>
                <Divider/>
                <ListItem button component={NavLink} to="/employers">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00' }}>Employer</Typography>} />
                </ListItem>
                <Divider/>
                <ListItem button component={NavLink} to="/jobSeekers">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00' }}>Job Seeker</Typography>} />
                </ListItem>
                <Divider/>
                <ListItem button component={NavLink} to="/settings">
                    <ListItemText primary={<Typography type="subtitle1" style={{ color: '#ff6f00' }}>Settings</Typography>} />
                </ListItem>
            </List>
        </div>
    )
}
