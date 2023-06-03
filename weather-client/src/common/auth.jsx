import axios from 'axios';
import {setAuthToken} from './setAuthToken';

export const doPostForLoginAndRegister = (url,object) =>{
    axios.post(url, object)
      .then( res =>
        {
            console.log(res);
            return res.data.success ? res.data.data : res.data.messages
        }
      )
      .then(data=> {
        if(typeof data === 'string'){
            document.getElementById('errMessage').innerHTML = `Error: ${data}`;
            return;
        }
        if(data.token){
            localStorage.setItem('Authorization', data.token);
            setAuthToken(data.token);
            window.location.href = '/';
        }
      })
      .catch(error =>{
        let errorMessage;
        let finalErrorMsg;
        if(error.response.data.data == null){
            errorMessage = error.response.data.detail + " Please correct your information with appropriate ones.";
        }
        else{
            errorMessage = error.response.data.data.message;
        }
        finalErrorMsg = `Error: ${errorMessage}`;
        document.getElementById('errMessage').innerHTML = finalErrorMsg;
    });
}
