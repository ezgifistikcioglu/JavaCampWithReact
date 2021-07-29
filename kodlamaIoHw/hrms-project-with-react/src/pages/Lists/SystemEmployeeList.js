import React, { useState, useEffect } from 'react'
import SystemEmployeeService from '../../services/systemEmployeeService';
import { Button, Icon } from 'semantic-ui-react';
import { NavLink } from "react-router-dom";
import { withStyles, makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
        fontWeight: "bold",
    },
    body: {
        fontSize: 12,
        fontWeight: "bold",
        textAlign: "center",
        fontFamily: "arial",
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

const useStyles = makeStyles({
    table: {
        WebkitBorderRadius : 4,
        textAlign : "center",
        marginTop: 1,
        minWidth: 300,        
    },
});

export default function SystemEmployeeList(){
    const classes = useStyles();

    const [systemEmployees, setSystemEmployees] = useState([]);

    useEffect(() => {
      let  systemEmployeeService = new SystemEmployeeService();
      systemEmployeeService.getSystemEmployees().then(result=>setSystemEmployees(result.data.data))
    }, [])
    return (
        <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="customized table">
            <TableHead>
                <TableRow>
                        <StyledTableCell>Email</StyledTableCell>
                        <StyledTableCell>First Name</StyledTableCell>
                        <StyledTableCell>Last Name</StyledTableCell>
                        <StyledTableCell align="center">Detail</StyledTableCell>
                    </TableRow>
                </TableHead>

                <TableBody>
                    {
                        systemEmployees.map(systemEmployee => (
                            <StyledTableRow key={systemEmployee.id}>
                                <StyledTableCell>{systemEmployee.email}</StyledTableCell>
                                <StyledTableCell>{systemEmployee.firstName}</StyledTableCell>
                                <StyledTableCell>{systemEmployee.lastName}</StyledTableCell>
                                <StyledTableCell >
                                    <Button animated as={NavLink} to={`/systemEmployeeDetails/${systemEmployee.userId}`} color="orange">
                                        <Button.Content visible>Show Detail</Button.Content>
                                        <Button.Content hidden>
                                            <Icon name="arrow right" />
                                        </Button.Content>
                                    </Button>
                                </StyledTableCell>
                            </StyledTableRow>
                        ))
                    }
                </TableBody>
            </Table>
        </TableContainer>
    )
}