import * as React from 'react';
import {CCard,CCardImage,CRow,CCol,CCardBody,CCardTitle,CCardText} from '@coreui/bootstrap-react';
import 'bootstrap/dist/css/bootstrap.min.css'
import QueryBuilderIcon from '@mui/icons-material/QueryBuilder';
import DeviceThermostatIcon from '@mui/icons-material/DeviceThermostat';
import CloudQueueIcon from '@mui/icons-material/CloudQueue';
import WaterDropOutlinedIcon from '@mui/icons-material/WaterDropOutlined';
import CompressOutlined from '@mui/icons-material/CompressOutlined';
import AirOutlinedIcon from '@mui/icons-material/AirOutlined';

export default function ImgMediaCard(props) {
  const item = props.item;
  const weatherCondition = item.weather[0];
  const main = props.item.main;

  const imageSrc = `https://openweathermap.org/img/wn/${weatherCondition.icon}@2x.png`;

  const tempColor = main.temp > 15 ? "red" : "darkblue";
  const date_text = props.item.dt_txt;

  const dateOriginal = new Date(date_text);
  const date = dateOriginal.toLocaleDateString('EN-gb', { weekday:"long", year:"numeric", month:"short", day:"numeric", hour:"2-digit",minute:'2-digit'});
  
  const splittedDate = date.split(',');

  const day = splittedDate[0];
  const monthDayYear = splittedDate[1];
  const hour = splittedDate[2];

  let hourColor;
  if(dateOriginal.getHours() <= 18 && dateOriginal.getHours() >= 6){
    hourColor = "DarkOrange"
  }
  else{
    hourColor = "DarkBlue"
  }

  const windSpeed = props.item.wind.speed;

  
  return (
    <React.StrictMode>
    <CCol md={3}>
          <CCard className="mb-3" style={{ maxWidth: '540px' }}>
  <CRow className="g-0">
    <CCol md={4}>
          <CCardImage style={{marginLeft:5}} src={imageSrc}/>
    </CCol>
    <CCol md={8}>
      <CCardBody>
        <CCardTitle>
        <b style={{color:"DarkCyan"}}>{day}</b>
        <br></br>
        <QueryBuilderIcon></QueryBuilderIcon> <b style={{color:hourColor}}>{hour}</b>
        <br></br>
        <span >{monthDayYear}</span>
        </CCardTitle>
        <br></br>
        <CCardText>
         <b><DeviceThermostatIcon></DeviceThermostatIcon>Temp: </b><b style={{color:tempColor}}>{main.temp}°C</b> 
        </CCardText>
        <CCardText>
        <p><CloudQueueIcon></CloudQueueIcon><b> Weather: </b>{weatherCondition.main}- {weatherCondition.description}</p>
        </CCardText>
        <CCardText>
        <b><WaterDropOutlinedIcon></WaterDropOutlinedIcon> Humidity: </b> {main.humidity}%
        </CCardText>
        <CCardText>
        <b><CompressOutlined></CompressOutlined>Pressure: </b> {main.pressure}mb
        </CCardText>
        <CCardText>
        <b><AirOutlinedIcon></AirOutlinedIcon> Wind: </b> {windSpeed}m/s
        </CCardText>
      </CCardBody>
    </CCol>
  </CRow>
</CCard>
          </CCol>
    </React.StrictMode>
  );
}