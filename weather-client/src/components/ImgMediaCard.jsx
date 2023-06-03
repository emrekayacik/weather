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
  let item = props.item;
  let weatherCondition = item.weather[0];
  let main = props.item.main;

  let imageSrc = `https://openweathermap.org/img/wn/${weatherCondition.icon}@2x.png`;

  let tempColor = main.temp > 15 ? "red" : "darkblue";
  let date_text = props.item.dt_txt;

  let date = date_text.split(' ')[0];
  let hour = date_text.split(' ')[1];
  
  let windSpeed = props.item.wind.speed;

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
        <CCardTitle><b>{date}</b> <QueryBuilderIcon ></QueryBuilderIcon>{hour}</CCardTitle>
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