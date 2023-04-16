import  React,{useState} from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import Divider from "@mui/material/Divider";
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import Typography from "@mui/material/Typography";
import { useFirebase } from '../context/firebase';
import Stack from "@mui/material/Stack";
import {
  collection,
  getDocs,
  deleteDoc,
  doc,
 
} from "firebase/firestore";
import Swal from "sweetalert2";

export default function LocationforAdmin() {
    const firebase =useFirebase();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [rows, setRows] = useState([]);
  const CollectionRef = collection(firebase.firebasestore, "Garbage");

  const getUsers = async () => {
    const data = await getDocs(CollectionRef);
    setRows(data.docs.map((doc) => ({ ...doc.data(), id: doc.id  })));
    
  };
useState(() => {
    getUsers();
  },[getUsers]);
  
  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };
  const deleteUser = (id) => {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.value) {
        deleteApi(id);
      }
    });
  };
  const deleteApi = async (id) => {
    const userDoc = doc(firebase.firebasestore, "Garbage", id);
    await deleteDoc(userDoc);
    Swal.fire("Deleted!", "Your file has been deleted.", "success");
    getUsers();
  };

  return (
    <Paper sx={{ width: '100%' }}>
         <Typography
            gutterBottom
            variant="h5"
            component="div"
            sx={{ padding: "20px" }}
          >
            Users Location
          </Typography>
          <Divider />
      <TableContainer sx={{ maxHeight: 440 }}>
        <Table stickyHeader aria-label="sticky table">
          <TableHead>
            
            <TableRow>
            <TableCell
                  align="left"
                  style={{minWidth: "100px"}}
                >
                    Type
                </TableCell>
                <TableCell
                  align="left"
                  style={{minWidth: "100px"}}
                >
                    latitude
                </TableCell>
                <TableCell
                  align="left"
                  style={{minWidth: "100px"}}
                >
                    longitude
                </TableCell>
                <TableCell
                  align="left"
                  style={{minWidth: "100px"}}
                >
                    Action
                </TableCell>
     
            </TableRow>
          </TableHead>
          <TableBody>
            {rows
              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
              .map((row) => {
                return (
                  <TableRow hover role="checkbox" tabIndex={-1}>
                    <TableCell key={row.id} align="left">
                            {row.type}
                        </TableCell>
                        <TableCell key={row.id} align="left">
                            {row.latitude}
                        </TableCell>
                        <TableCell key={row.id} align="left">
                            {row.longitude}
                        </TableCell>
                        <TableCell align="left">
                          <Stack spacing={2} direction="row">
                            <button 
                              style={{
                                height:"15px",
                                width:"15px",
                                fontSize: "20px",
                                color: "darkred",
                                cursor: "pointer",
                              }}
                              onClick={() => {
                                deleteUser(row.id);
                              }}
                            />
                          </Stack>
                        </TableCell>
                  </TableRow>
                );
              })}
          </TableBody>
        </Table>
      </TableContainer>
      <TablePagination
        rowsPerPageOptions={[10, 25, 100]}
        component="div"
        count={rows.length}
        rowsPerPage={rowsPerPage}
        page={page}
        onPageChange={handleChangePage}
        onRowsPerPageChange={handleChangeRowsPerPage}
      />
    </Paper>
  );
}