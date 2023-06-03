import styled from 'styled-components';
import {mobile} from "../common/responsive";
import { useState } from "react";
import {doPostForLoginAndRegister} from '../common/auth';
import ErrorMessage from '../components/ErrorMessage';

const Container = styled.div`
    width: 100vw;
    height: 100vh;
    background: linear-gradient(
      rgba(255,255,255,0.5),
      rgba(255,255,255,0.5)),
      url(https://images.pexels.com/photos/691569/pexels-photo-691569.jpeg?auto=compress&cs=tinysrgb&w=1600) center;
      background-repeat: no-repeat;
      background-size:cover;
      display:flex;
      align-items:center;
      justify-content:center;
        
        
`
const Wrapper = styled.div`
        width: 40%;
        padding: 20px;
        background-color: white;
        border-radius: 1%;
        ${mobile(
            
            {width:"80%"}
            
            
            )}
`
const Title = styled.h1`
    font-size: 24px;
    font-weight: 300;
`
const Form = styled.form`
    display: flex;
    flex-wrap: wrap;
`

const Input = styled.input`
  flex: 1;
  min-width: 40%;
  margin: 20px 10px 0 0;
  padding: 10px;
  border-top: none;
  border-left: none;
  border-right: none;
`
const Button = styled.button`
    width: 100%;
    border: none;
    padding: 15px 20px;
    background-color: black;
    color: white;
    cursor: pointer;
    margin-top: 15px;
`


const Register = () => {
    const postUrl = 'http://localhost:8080/api/v1/auth/register';

    const [name, setName] = useState('');
    const [lastname, setLastname] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleChangeName = (event) => {
        setName(event.target.value);
    };
    const handleChangeLastName = (event) => {
        setLastname(event.target.value);
    };
    const handleChangePhoneNumber = (event) => {
        setPhoneNumber(event.target.value);
    };
    const handleChangeEmail = (event) => {
        setEmail(event.target.value);
    };
    const handleChangeUsername = (event) => {
        setUsername(event.target.value);
    };
    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleRegister = (e) => {
        e.preventDefault();
        document.getElementById('errMessage').innerHTML = '';

        let nameToPost = name;
        let lastnameToPost = lastname;
        let phoneNumberToPost = phoneNumber;
        let emailToPost = email;
        let usernameToPost = username;
        let passwordToPost = password;

        let object = {
            name: nameToPost,
            surname: lastnameToPost,
            phoneNumber: phoneNumberToPost,
            email: emailToPost,
            username: usernameToPost,
            password: passwordToPost
        }

        doPostForLoginAndRegister(postUrl,object);
    }  
  return (
    <Container>
        <Wrapper>
            <Title>CREATE AN ACCOUNT</Title>
            <Form>
                <Input id="name"
        name="name"
        onChange={handleChangeName} placeholder="name" />
                <Input id="lastname"
        name="lastname"
        onChange={handleChangeLastName} placeholder="last name" />
                <Input id="phoneNumber"
        name="phoneNumber"
        onChange={handleChangePhoneNumber} placeholder="phone number" />
                <Input id="email"
        name="email"
        onChange={handleChangeEmail} placeholder="email" />
                <Input id="username"
        name="username"
        onChange={handleChangeUsername}
         placeholder="username" />
                <Input
                type='password'
                id="password"
                name="password"
                onChange={handleChangePassword}
                
                placeholder="password" />
                <Button onClick={handleRegister}>CREATE</Button>
                <ErrorMessage id='errMessage'></ErrorMessage>

            </Form>
        </Wrapper>

    </Container>
  )
}

export default Register