import React, { useState, useEffect } from 'react'
import EmployerService from '../../services/employerService'
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

export default function EmployerList() {
    const classes = useStyles();

    const [employers, setEmployers] = useState([])

    useEffect(() => {
        let employerService = new EmployerService()
        employerService.getEmployers().then(result => setEmployers(result.data.data))
    }, [])

    return (
        <TableContainer component={Paper}>
        <Table className={classes.table} aria-label="customized table">
            <TableHead>
                <TableRow>
                        <StyledTableCell align="center">Email</StyledTableCell>
                        <StyledTableCell align="center">Company Name</StyledTableCell>
                        <StyledTableCell align="center">Telephone Number</StyledTableCell>
                        <StyledTableCell align="center">Web Address</StyledTableCell>
                        <StyledTableCell align="center">Detail</StyledTableCell>
                        </TableRow>
                </TableHead>
                <TableBody>
                    {
                        employers.map(employer => (
                            <StyledTableRow key={employer.id}>
                                <StyledTableCell>{employer.email}</StyledTableCell>
                                <StyledTableCell>{employer.companyName}</StyledTableCell>
                                <StyledTableCell>{employer.telephoneNumber}</StyledTableCell>
                                <StyledTableCell>{employer.webAddress}</StyledTableCell>
                                <StyledTableCell >
                                    <Button animated as={NavLink} to={`/employerDetails/${employer.id}`} color="orange">
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