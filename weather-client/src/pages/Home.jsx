import styled from 'styled-components'
import ErrorMessage from '../components/ErrorMessage';
import MenuItem from '@mui/material/MenuItem';
import Select from '@mui/material/Select';
import FormControl from '@mui/material/FormControl';
import InputLabel from '@mui/material/InputLabel';
import { useState, useEffect } from "react";
import axios from 'axios';
import ImgMediaCard from '../components/ImgMediaCard'
import {CContainer,CRow} from '@coreui/bootstrap-react';
import 'bootstrap/dist/css/bootstrap.min.css'
import NavbarComp from '../components/NavbarComp';
import FavoriteBorderOutlinedIcon from '@mui/icons-material/FavoriteBorderOutlined';
import {mobile} from '../common/responsive'
import { MagnifyingGlass } from  'react-loader-spinner'


const Title = styled.h1`
    font-size: 24px;
    font-weight: 300;
    font-family: "Times New Roman", Times, serif;
`
const Form = styled.form`
    display: flex;
    flex-direction:column;
`
const SelectComp = styled.select`
    padding: 10px;
    margin-right: 20px;
    margin-top: 10px;
    ${mobile(
            
        {margin:"10px 0px"}
        
        
        )}
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
  const [userFavorites, setUserFavorites] = useState({});
  const [favList, setFavList] = useState();
  const [isLoading, setIsLoading] = useState(false);

  const handleMouseEnter = () => {
    setIsHovering(true);
  };

  const handleMouseLeave = () => {
    setIsHovering(false);
  };
  useEffect(() => {
    const dataFetch = async () => {
      const data = await getUsersFavCities();
      setUserFavorites(data);
    };

    dataFetch();
  }, []);

  const getUsersFavCities = async () => {
    try{
      let url = 'http://localhost:8080/api/v1/userWeathers/users/own';
      const response = await axios.get(url);
      return response.data.data;
    }catch(error){
      console.log(error);
    }
  }

  const handleSelectChange = (e) => {
    e.preventDefault();
    let lat = document.getElementById("lat");
    let lon = document.getElementById("lon");
    let city = document.getElementById("cityName");
    let fav = document.getElementById("favList");
    let btnGet = document.getElementById("btnGet");
    
    
    if(e.target.value === 2){
      city.style.display = "none";
      lat.style.display = "block";
      lon.style.display = "block";
      fav.style.display = "none";
      btnGet.style.display = "block";
    }
    if(e.target.value === 1){
      city.style.display = "block";
      lat.style.display = "none";
      lon.style.display = "none";
      fav.style.display = "none";
      btnGet.style.display = "block";
    }
    if(e.target.value === 3){
      
      city.style.display = "none";
      lat.style.display = "none";
      lon.style.display = "none";
      
      btnGet.style.display = "none";
      fav.style.display = "block";
      arrangeFavList();

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
      if(city === undefined || city === null || city === ""){
        document.getElementById("errMessage").innerHTML = "City name cannot be blank.";
        return;
      }
      doGet(`${BASE_URL}/forecasts/city?city=${city}`);
    }
    else if(typeToPost === 2){
      if(lat === undefined || lat === null || lat === "" ||lon === undefined || lon === null || lon === "" ){
        document.getElementById("errMessage").innerHTML = "Latitude and Longitude cannot be blank.";
        return;
      }
      doGet(`${BASE_URL}/forecasts/coordinates?lat=${lat}&lon=${lon}`);
    }
  }

  const doGet = (url) =>{
    setIsLoading(true);

    document.getElementById("errMessage").innerHTML = "";
    
    axios.get(url
     )
      .then( res =>
        {
            console.log(res);
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(async (data)=> {
      
        setData(data);
        setCity(data.city?.name);
        setCountry(data.city?.country);
        if(userFavorites){
          const isUserFavoritedCurrentCity = userFavorites.map(a=>a.cityName).includes(data.city.name);
          if(isUserFavoritedCurrentCity){
            handleRenderingUserFavoritedCity(true);
          }else{
            handleRenderingUserFavoritedCity(false);
          }
        }else{
          handleRenderingUserFavoritedCity(false);
        }
        document.getElementById("favButton").style.display = "inline-block";
        setIsLoading(false);

      })
      .catch(error =>{
        console.log(error);
        document.getElementById("errMessage").innerHTML = error.response?.data?.data?.message;

        const isUserFavoritedCurrentCity = userFavorites?.map(a=>a.cityName)?.includes(data.city?.name);

        handleRenderingUserFavoritedCity(isUserFavoritedCurrentCity);
        setIsLoading(false);
    });
  }

  const handleRenderingUserFavoritedCity = (fav) =>{

    document.getElementById("favButton").style.color = fav ? "red" : "black";
  }
  
  const handleFavorite = (e) =>{
    e.preventDefault();
    
    const isUserFavoritedCurrentCity = userFavorites.map(a=>a.cityName).includes(city);
    if(isUserFavoritedCurrentCity){
      handleUnFavorite(e);
      return;
    }


    document.getElementById("errMessage").innerHTML = "";
    let url = 'http://localhost:8080/api/v1/userWeathers';

    let cityToPost = city;
    let usernameToPost = props.username;
    if(cityToPost === "" || cityToPost === undefined || cityToPost === null){
      document.getElementById("errMessage").innerHTML = "No city found in this coordinates. This coordinates must be in the ocean. Please correct your lat/lon values to target a valid city.";
      return;
    }
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
        handleRenderingUserFavoritedCity(true);
        let userFavCopy = userFavorites;
        userFavCopy.push({
          id: data.id,
          cityName: data.cityName
        });
        setUserFavorites(userFavCopy)
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
    e.preventDefault();

    document.getElementById("errMessage").innerHTML = "";
    const id = userFavorites.find((e)=> e.cityName === city).id;
    let url = `http://localhost:8080/api/v1/userWeathers/${id}`;

    axios.delete(url)
      .then( res =>
        {
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(data=> {
        setUserFavorites(userFavorites.filter(a=>a.id !== id))
        handleRenderingUserFavoritedCity(false);
      })
      .catch(error =>{
        console.log(error);
    });
  }

  const arrangeFavList = () => {
    const favListRendered = userFavorites.length > 0 ? userFavorites.map(a=>{
      return <option style={{fontFamily: "Times New Roman"}} id={a.id} value={a.cityName}>{a.cityName}</option>
    }) : <option disabled style={{fontFamily: "Times New Roman"}} >No Favorites found</option>

    setFavList(favListRendered)
  }
  const handleSelectFavoriteChange = (e) => {
    e.preventDefault();
    let BASE_URL = 'http://localhost:8080/api/v1/weathers';
    doGet(`${BASE_URL}/forecasts/city?city=${e.target.value}`);
  }

  return (
    <>
            <NavbarComp username={props.username} />
        <CContainer style={{marginTop: 25}}>

                <Title>Weather App Home</Title>
              <Form className='center'>
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
            <MenuItem id="fwll" value={3}>My Favorite Cities</MenuItem>
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
                <SelectComp onClick={arrangeFavList} style={{display:"none"}} onChange={handleSelectFavoriteChange} id="favList">
                    <option disabled selected></option>
                    {
                      favList
                    }
                </SelectComp>

              </Form>
        </FormControl>
                  <Button id='btnGet' onClick={handleGet}>GET</Button>
                  <div style={{width:"100%"}}>

                    <p style={{textAlign: "center"}}>
                    <MagnifyingGlass
                    id="loadingSpinner"
                    visible={isLoading}
                    height="80"
                    width="80"
                    ariaLabel="MagnifyingGlass-loading"
                    wrapperStyle={{center}}
                    wrapperClass="MagnifyingGlass-wrapper"
                    glassColor = '#c0efff'
                    color = '#e15b64'
                  />
                    </p>
                  </div>
                 
                
                  <ErrorMessage />
              </Form>
              <div style={center}>
                <h1 style={{fontFamily: "Times New Roman"}}>{city}</h1><span>{country} </span>
                <p>
                
                  <FavoriteBorderOutlinedIcon
                onMouseEnter={handleMouseEnter}
                onMouseLeave={handleMouseLeave}
                onClick={handleFavorite}
                style={{
                  color: "red",
                  cursor: "pointer",
                  display: "none"
                }}
                id="favButton" ></FavoriteBorderOutlinedIcon></p>
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