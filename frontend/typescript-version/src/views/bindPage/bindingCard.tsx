// ** React Imports
import React, {ReactElement, SyntheticEvent, useEffect, useState} from 'react'

// ** MUI Imports
import Box from '@mui/material/Box'
import Grid from '@mui/material/Grid'
import Card from '@mui/material/Card'
import Avatar from '@mui/material/Avatar'
import CardHeader from '@mui/material/CardHeader'
import IconButton from '@mui/material/IconButton'
import Typography from '@mui/material/Typography'
import CardContent from '@mui/material/CardContent'
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';

// ** Icons Imports
import TrendingUp from 'mdi-material-ui/TrendingUp'
import CurrencyUsd from 'mdi-material-ui/CurrencyUsd'
import DotsVertical from 'mdi-material-ui/DotsVertical'
import CellphoneLink from 'mdi-material-ui/CellphoneLink'
import AccountOutline from 'mdi-material-ui/AccountOutline'
// ** Types
import { ThemeColor } from 'src/@core/layouts/types'

import axios  from "axios";
import {Accordion, AccordionDetails, AccordionSummary} from "@mui/material";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputAdornment from "@mui/material/InputAdornment";
import EyeOutline from "mdi-material-ui/EyeOutline";
import EyeOffOutline from "mdi-material-ui/EyeOffOutline";
import Button from "@mui/material/Button";

interface ExchangeIcon {
  name: string,
  title: string,
  icon: ReactElement,
}

interface apiKeyRequest {
  platformId: number,
  accountName: string,
  apiKey: string,
  secretKey: string,
}

const bindAccount = async (destination: string) => {
  console.log("We are binding the " + destination + " account!");
  const bodyMessage: apiKeyRequest = {
    platformId: 1,
    accountName: "my binance",
    apiKey: destination,
    secretKey: destination,
  }

  /*const logInOptions = {
    headers: {'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    },
    data: {email: 'test1234@gmail.com', password: '1234'}
  };

  //LogIn option
  await axios.post('http://localhost:8080/auth/login', logInOptions)
    .then(response => response.data)
    .then(data => console.log(JSON.stringify(data)))*/

    let data = JSON.stringify({
      "email": "admin@example.com",
      "password": "123"
    });

    let config = {
      method: 'post',
      maxBodyLength: Infinity,
      url: 'http://localhost:9000/auth/login',
      headers: { 
        'Content-Type': 'application/json'
      },
      data : data
    };

    axios.request(config)
    .then((response) => {
      console.log(JSON.stringify(response.data));
    })
    .catch((error) => {
      console.log(error);
    });

  const requestOptions = {
    headers: {'Content-Type': 'application/json',
              'Access-Control-Allow-Origin': '*',
              'userId': '10',
    },
    body: JSON.stringify(bodyMessage),
  };



  /*await axios.post('http://localhost:9001/account/apikey', requestOptions)
      .then(response => response.data)
      .then(data => console.log(JSON.stringify(data)))*/

}
const salesData: ExchangeIcon[] = [
  {
    name: 'Binance',
    title: 'Exchange',
    icon: <img style={{ width: 80, height: 80 }}
               src={'/images/logos/binance-logo-icon.png'}
               className="Binance-logo"
               alt ="Binance"
    />,
  },
  {
    name: 'Coinbase',
    title: 'Exchange',
    icon: <img style={{ width: 80, height: 80 }}
               src={'/images/logos/coinbase-logo-icon.png'}
               className="Coinbase-logo"
               alt ="Coinbase"
    />
  },
  {
    name: 'Crypto.com',
    title: 'Exchange',
    icon: <img style={{ width: 80, height: 80 }}
               src={'/images/logos/crypto-com-icon.png'}
               className="Crypto-logo"
               alt = "Crypto.com" />
  },
  {
    name: 'Metatmask',
    title: 'Wallet',
    icon: <img style={{ width: 80, height: 80 }}
               src={'/images/logos/metamask-icon.png'}
               className = "Metamask-logo"
               alt = "Metatmask"/>
  }
]



const BindingCard = () => {
  const [numOfBindedAccount, setnumOfBindedAccount] = useState(-1);
  const [expanded, setExpanded] = useState<string | false>(false);
  const [platform, setPlatform] = useState<string>("")

  useEffect(() => {
    const token: string | null = localStorage.getItem('token')
    if (token !==null) {
      setnumOfBindedAccount(1);
      console.log(`You've already sign in and token is ${token}`);
    }
    else {
      setnumOfBindedAccount(0);
      console.log("You haven't sign in!");
    }

  }, [])

  const handleExpandFormChange =
    (panel: string) => (event: SyntheticEvent, isExpanded: boolean) => {
      setExpanded(isExpanded ? panel : false);
    };
  const renderStats = () => {
    return salesData.map((item: ExchangeIcon, index: number) => (
      <Grid item xs={12} sm={3} key={index}>
        <Box key={index} sx={{ display: 'flex', alignItems: 'center' }}>
          <IconButton component='a' onClick={() =>{ setPlatform(item.name); bindAccount(item.name)}}>
            {item.icon}
          </IconButton>
          <Box sx={{ display: 'flex', flexDirection: 'column' }}>
            <Typography variant='caption'>{item.title}</Typography>
            <Typography variant='h6'>{item.name}</Typography>
          </Box>
        </Box>
      </Grid>
    ))
  }

  return (
    <Card>
      <CardHeader

        title="Bind your Account 🚈🚈🚈"
        action={
          <IconButton size='small' aria-label='settings' className='card-more-options' sx={{ color: 'text.secondary' }}>
            <DotsVertical />
          </IconButton>
        }
        subheader={
          <Typography variant='h6'>
            <Box component='span' sx={{ fontWeight: 600, color: 'text.primary' }}>
              Total Binded Accounts: {numOfBindedAccount}
            </Box>{' '}
          </Typography>
        }
        titleTypographyProps={{
          sx: {
            mb: 2.5,
            lineHeight: '2rem !important',
            letterSpacing: '0.15px !important'
          }
        }}
      />
      <CardContent sx={{ pt: theme => `${theme.spacing(3)} !important` }}>
        <Accordion expanded={expanded === 'p1'} onChange={handleExpandFormChange('p1')}>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1bh-content"
            id="panel1bh-header"
          >
            <Grid container spacing={[5, 0]}>
              {renderStats()}
            </Grid>
          </AccordionSummary>
          <AccordionDetails>
            <form noValidate autoComplete='off' onSubmit={e => e.preventDefault()}>
              <FormControl fullWidth>
                <Typography variant='h6'>You are now choosing to bind account at {platform}</Typography>
                <TextField autoFocus fullWidth id='apiKey' label='ApiKey*' sx={{ marginBottom: 4 }} />
                <TextField autoFocus fullWidth id='secretKey' label='SecretKey*' sx={{ marginBottom: 4 }} />
                <TextField autoFocus fullWidth id='accountName' label='AccountName' sx={{ marginBottom: 4 }} />
              </FormControl>
              <Button
                fullWidth
                size='large'
                variant='contained'
                sx={{ marginBottom: 7 }}
                onClick={() => console.log("SUCCESSFUL!")}

              >
                Bind Account
              </Button>
            </form>
          </AccordionDetails>

        </Accordion>


      </CardContent>
    </Card>
  )
}

export default BindingCard
