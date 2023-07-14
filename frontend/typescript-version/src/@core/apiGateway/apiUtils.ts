import { AuthCodeApiResponse } from "./apiInterfaces";
import axios from 'axios';

export const getAccountApi = async () : Promise<AuthCodeApiResponse> => {
    try {
      const data: AuthCodeApiResponse = await axios.get('http://localhost:9001/account', {method: 'GET', headers: {'Content-Type': 'application/json',
          'Access-Control-Allow-Origin': '*',
          'userId': '10',
        }})
      .then(function(response) {
        console.log(response.data);

        return response.data;
      });

      return data;
    } catch (error) {
      console.error('Error sending message:', error);
      throw error; // Rethrow the error to handle it in the component
    }
  };
