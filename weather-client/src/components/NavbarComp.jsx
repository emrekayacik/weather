import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';

const NavbarComp = (props) => {
    const logout = () => {
        localStorage.removeItem('Authorization');
        window.location.reload();
    };
    return(
        <Navbar bg="dark" variant="dark">
            <Container>
            <Navbar.Brand href="#home">
                <img
                alt=""
                src="https://cdn-icons-png.flaticon.com/512/6324/6324501.png"
                width="30"
                height="30"
                className="d-inline-block align-top"
                />{' '}
                Weather App
            </Navbar.Brand>
                <Navbar.Toggle />
            <Navbar.Collapse className="justify-content-end">
            <Navbar.Text>
                Signed in as: <b style={{color: "white"}}>{props.username}</b> | <a href="#" style={{cursor: "pointer"}} onClick={logout}>Logout</a>
            </Navbar.Text>
            </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}
export default NavbarComp