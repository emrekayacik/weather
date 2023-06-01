import styled from 'styled-components'
import {mobile} from "../common/responsive"
import { useState } from "react";
import {doPostForLoginAndRegister} from '../common/auth';

const Container = styled.div`
    width: 100vw;
    height: 100vh;
    background: linear-gradient(
        rgba(255,255,255,0.5),
        rgba(255,255,255,0.5)),
        url(https://images.pexels.com/photos/1317258/pexels-photo-1317258.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1) center;
        background-repeat: no-repeat;
        background-size:cover;
        display:flex;
        align-items:center;
        justify-content:center;
`
const Wrapper = styled.div`
        width: 25%;
        padding: 20px;
        background-color: white;
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
    flex-direction:column;
`

const Input = styled.input`
  flex: 1;
  min-width: 40%;
  margin: 10px 0;
  padding: 10px;
`
const Button = styled.button`
    width: 100%;
    border: none;
    padding: 15px 20px;
    background-color: teal;
    color: white;
    cursor: pointer;
    margin-bottom: 10px;
`
const ErrorMessage = styled.p`
    color: red;
`

const Login = () => {
    const postUrl = 'http://localhost:8080/api/v1/auth/authenticate';

    const [username, setUsername] = useState('');

    const [password, setPassword] = useState('');

    const handleChangeUsername = (event) => {
        setUsername(event.target.value);
    };
    const handleChangePassword = (event) => {
        setPassword(event.target.value);
    };

    const handleLogin = (e) => {
        e.preventDefault();
        document.getElementById('errMessage').innerHTML = '';

        
        let usernameToPost = username;
        let passwordToPost = password;
        
        let object = {
            username: usernameToPost,
            password: passwordToPost
        }

        doPostForLoginAndRegister(postUrl,object);
    }

  return (
    <Container>
        <Wrapper>
            <Title>SIGN IN</Title>
            <Form>
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
                <Button onClick={handleLogin}>LOGIN</Button>
                <ErrorMessage id='errMessage'></ErrorMessage>
            </Form>
        </Wrapper>
    </Container>
  )
}

export default Login