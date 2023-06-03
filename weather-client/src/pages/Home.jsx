import styled from 'styled-components'
import ErrorMessage from '../components/ErrorMessage';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import { useState } from "react";
import axios from 'axios';
import ImgMediaCard from '../components/ImgMediaCard'
import {CContainer,CRow} from '@coreui/bootstrap-react';
import 'bootstrap/dist/css/bootstrap.min.css'
import NavbarComp from '../components/NavbarComp';


const Title = styled.h1`
    font-size: 24px;
    font-weight: 300;
    font-family: "Times New Roman", Times, serif;
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
  display: none;
`
const Button = styled.button`
    width: 100%;
    border: none;
    padding: 15px 20px;
    background-color: teal;
    color: white;
    cursor: pointer;
    margin-bottom: 10px;
    display: none;
`
const center = {
  
  textAlign: "center",
  marginBottom: 20
}
const Home = (props) => {
  const [type, setType] = useState('');
  const [data, setData] = useState({});
  const [city, setCity] = useState('');
  const [country, setCountry] = useState('');

  const handleSelectChange = (e) => {
    e.preventDefault();
    let lat = document.getElementById("lat");
    let lon = document.getElementById("lon");
    let city = document.getElementById("cityName");
    let btnGet = document.getElementById("btnGet");
    btnGet.style.display = "block";
    
    if(e.target.value === 2 || e.target.value === 3){
      city.style.display = "none";
      lat.style.display = "block";
      lon.style.display = "block";
    }
    if(e.target.value === 1 || e.target.value === 4){
      city.style.display = "block";
      lat.style.display = "none";
      lon.style.display = "none";
    }
    setType(e.target.value);
  }

  const handleGet = (e) => {
    e.preventDefault();

    let lat = document.getElementById("lat").value;
    let lon = document.getElementById("lon").value;
    let city = document.getElementById("cityName").value;

    let typeToPost = type;
    let BASE_URL = 'http://localhost:8080/api/v1/weather';
    if(typeToPost === 1){
      doGet(`${BASE_URL}/forecasts/city?city=${city}`);
    }
    else if(typeToPost === 2){
      doGet(`${BASE_URL}/forecasts/coordinates?lat=${lat}&lon=${lon}`);
    }
  }

  const doGet = (url) =>{
    // if(localStorage.getItem("jwtToken") === null){
    //   axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("Authorization");
    // }
    document.getElementById("errMessage").innerHTML = "";
    let token = localStorage.getItem("Authorization");
    axios.get(url
      ,{
      headers: {
        Authorization: 'Bearer ' + token 
      }
     }
     )
      .then( res =>
        {
            console.log(res);
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(data=> {
      
        setData(data);
        setCity(data.city.name);
        setCountry(data.city.country);
      })
      .catch(error =>{
        console.log(error);
        document.getElementById("errMessage").innerHTML = "Please provide a valid city name or latitute/longtitude.";
    });
  }


  return (
    <>
            <NavbarComp username={props.username} />
        <CContainer style={{marginTop: 25}}>

                <Title>Weather App Home</Title>
              <Form>
              <FormControl variant="standard" sx={{ m: 1, minWidth: 120 }}>
          <InputLabel id="slcWeather-label">Select</InputLabel>
          <Select
            labelId="slcWeather-label"
            id="slcWeather"
            label="Select"
            onChange={handleSelectChange}
          >
            <MenuItem id="fwcn" value={1}>5-Day Forecast With City Name</MenuItem>
            <MenuItem id="fwll" value={2}>5-Day Forecast With Latitude-Longitude</MenuItem>
          </Select>
          <Form>
                  <Input 
                  type='number'
                  id="lat"
                  name="lat"
                  placeholder="lat(90<>-90)" />

                  <Input
                  type='number'
                  id="lon"
                  name="lon"
                  placeholder="lon(180<>-180)" />

                  <Input
                  type='text'
                  id="cityName"
                  name="cityName"
                  placeholder="city" />
              </Form>
        </FormControl>
                  <Button id='btnGet' onClick={handleGet}>GET</Button>
                  <ErrorMessage />
              </Form>
              <div style={center}>
                <h1 style={{fontFamily: "Times New Roman"}}>{city}</h1><span>{country}</span>
              </div>
            <CRow>
            {data.list?.map((item) =>
              <ImgMediaCard key={item.dt} item={item} />
            )
            }
            </CRow>
      
      </CContainer>
    </>
  )
}

export default Home