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
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';


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
  const [isHovering, setIsHovering] = useState(false);

  const handleMouseEnter = () => {
    setIsHovering(true);
  };

  const handleMouseLeave = () => {
    setIsHovering(false);
  };

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
    let BASE_URL = 'http://localhost:8080/api/v1/weathers';
    if(typeToPost === 1){
      doGet(`${BASE_URL}/forecasts/city?city=${city}`);
    }
    else if(typeToPost === 2){
      doGet(`${BASE_URL}/forecasts/coordinates?lat=${lat}&lon=${lon}`);
    }
  }

  const doGet = (url) =>{
    document.getElementById("errMessage").innerHTML = "";
    axios.get(url
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
        document.getElementById("favButton").style.display = "inline-block";
        //TODO: get users liked cities. if user liked the current city then favButtonOk should appear, if not favButton should appear 
      })
      .catch(error =>{
        console.log(error);
        document.getElementById("errMessage").innerHTML = error.response?.data?.data?.message;
        document.getElementById("favButton").style.display = "none";
    });
  }
  
  const handleFavorite = (e) =>{
    e.preventDefault();

    document.getElementById("errMessage").innerHTML = "";
    let url = 'http://localhost:8080/api/v1/userWeathers';

    let cityToPost = city;
    let usernameToPost = props.username;

    let obj = {
      city: cityToPost,
      username: usernameToPost
    }

    axios.post(url,obj)
      .then( res =>
        {
            console.log(res);
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(data=> {
        document.getElementById("favButton").style.display = "none";
        document.getElementById("favButtonOk").style.display = "inline-block";
      })
      .catch(error =>{
        if(error.response?.data?.data?.message.includes("already exists")){
          document.getElementById("errMessage").innerHTML = "This user is already favorited this city.";
        }
        else{
          document.getElementById("errMessage").innerHTML = error.response?.data?.data?.message;
        }
        
    });
  }
  
  const handleUnFavorite = (e) =>{
    //TODO: delete record if user unfavorited
    e.preventDefault();

    document.getElementById("errMessage").innerHTML = "";
    let url = 'http://localhost:8080/api/v1/userWeathers';

    let cityToPost = city;
    let usernameToPost = props.username;

    let obj = {
      city: cityToPost,
      username: usernameToPost
    }

    axios.delete(url,obj)
      .then( res =>
        {
            console.log(res);
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(data=> {
        document.getElementById("favButton").style.display = "none";
        document.getElementById("favButtonOk").style.display = "inline-block";
      })
      .catch(error =>{
        if(error.response?.data?.data?.message.includes("already exists")){
          document.getElementById("errMessage").innerHTML = "This user is already favorited this city.";
        }
        else{
          document.getElementById("errMessage").innerHTML = error.response?.data?.data?.message;
        }
        
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
                <h1 style={{fontFamily: "Times New Roman"}}>{city}</h1><span>{country} </span>
                <p><FavoriteBorderOutlinedIcon
                onMouseEnter={handleMouseEnter}
                onMouseLeave={handleMouseLeave}
                onClick={handleFavorite}
                style={{
                  color: isHovering ? 'red' : 'black',
                  cursor: "pointer",
                  display: "none"
                }}
                id="favButton"></FavoriteBorderOutlinedIcon></p>
                <p><FavoriteBorderOutlinedIcon
                onMouseEnter={handleMouseEnter}
                onMouseLeave={handleMouseLeave}
                onClick={handleUnFavorite}
                style={{
                  color: isHovering ? '#8B0000' : 'red',
                  cursor: "pointer",
                  display: "none"
                }}
                id="favButtonOk"></FavoriteBorderOutlinedIcon></p>
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