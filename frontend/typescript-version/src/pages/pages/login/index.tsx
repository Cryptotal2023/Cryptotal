// ** React Imports
import { ChangeEvent, MouseEvent, ReactNode, useState, useEffect } from 'react'
// ** Next Imports
import Link from 'next/link'
import { useRouter } from 'next/router'
import Image from 'next/image';
// ** MUI Components
import Box from '@mui/material/Box'
import Button from '@mui/material/Button'
import Divider from '@mui/material/Divider'
import Checkbox from '@mui/material/Checkbox'
import TextField from '@mui/material/TextField'
import InputLabel from '@mui/material/InputLabel'
import Typography from '@mui/material/Typography'
import IconButton from '@mui/material/IconButton'
import CardContent from '@mui/material/CardContent'
import FormControl from '@mui/material/FormControl'
import OutlinedInput from '@mui/material/OutlinedInput'
import { styled, useTheme } from '@mui/material/styles'
import MuiCard, { CardProps } from '@mui/material/Card'
import InputAdornment from '@mui/material/InputAdornment'
import MuiFormControlLabel, { FormControlLabelProps } from '@mui/material/FormControlLabel'

// ** Icons Imports
import EyeOutline from 'mdi-material-ui/EyeOutline'
import EyeOffOutline from 'mdi-material-ui/EyeOffOutline'

// ** Utils import
import { useGoogleLogin, GoogleLogin } from '@react-oauth/google';

// ** Configs
import themeConfig from 'src/configs/themeConfig'

// ** Layout Import
import BlankLayout from 'src/@core/layouts/BlankLayout'

// ** Demo Imports
//import FooterIllustrationsV1 from 'src/views/pages/auth/FooterIllustration'

// ** import axios


 //import interfaces
 import { getAccountApi } from 'src/@core/apiGateway/apiUtils';
 import { AuthCodeApiResponse } from 'src/@core/apiGateway/apiInterfaces';




interface State {
  password: string
  showPassword: boolean
}

// ** Styled Components
const Card = styled(MuiCard)<CardProps>(({ theme }) => ({
  [theme.breakpoints.up('sm')]: { width: '28rem' }
}))

const LinkStyled = styled('a')(({ theme }) => ({
  fontSize: '0.875rem',
  textDecoration: 'none',
  color: theme.palette.primary.main
}))

const FormControlLabel = styled(MuiFormControlLabel)<FormControlLabelProps>(({ theme }) => ({
  '& .MuiFormControlLabel-label': {
    fontSize: '0.875rem',
    color: theme.palette.text.secondary
  }
}))

const LoginPage = () => {
  // ** State
  const [values, setValues] = useState<State>({
    password: '',
    showPassword: false
  })

  // ** Hook
  const theme = useTheme()
  const router = useRouter()

  // ** useEffect
  useEffect(() => {
    const token: string | null = localStorage.getItem('token')
    if (token !==null) {
      console.log(`You've already sign in and token is ${token}`);
    }
    else console.log("You haven't sign in!");

  }, [])
  const handleChange = (prop: keyof State) => (event: ChangeEvent<HTMLInputElement>) => {
    setValues({ ...values, [prop]: event.target.value })
  }

  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword })
  }

  const handleMouseDownPassword = (event: MouseEvent<HTMLButtonElement>) => {
    event.preventDefault()
  }

  const handleSendMessage = async () => {
    try {
      const authCodeApiResponse: AuthCodeApiResponse = await getAccountApi();
      console.log(authCodeApiResponse.body); // Handle the response data as needed
    } catch (error) {
      console.log(error);
      throw error;
    }
  };

  const handleRedirct =  (href: string) => {
    window.location.href = href;
  };

  return (

    <Box className='content-center' sx = {{background:'#FFF'}}>
      <Card sx={{ zIndex: 1 }}>
        <CardContent  sx={{background:'#FFF', padding: theme => `${theme.spacing(12, 9, 7)} !important` }}>
          <Box sx={{ mb: 8, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
            <svg
              width={35}
              height={29}
              version='1.1'
              viewBox='0 0 30 23'
              xmlns='http://www.w3.org/2000/svg'
              xmlnsXlink='http://www.w3.org/1999/xlink'
            >
              <g stroke='none' strokeWidth='1' fill='none' fillRule='evenodd'>
                <g id='Artboard' transform='translate(-95.000000, -51.000000)'>
                  <g id='logo' transform='translate(95.000000, 50.000000)'>
                    <path
                      id='Combined-Shape'
                      fill={theme.palette.primary.main}
                      d='M30,21.3918362 C30,21.7535219 29.9019196,22.1084381 29.7162004,22.4188007 C29.1490236,23.366632 27.9208668,23.6752135 26.9730355,23.1080366 L26.9730355,23.1080366 L23.714971,21.1584295 C23.1114106,20.7972624 22.7419355,20.1455972 22.7419355,19.4422291 L22.7419355,19.4422291 L22.741,12.7425689 L15,17.1774194 L7.258,12.7425689 L7.25806452,19.4422291 C7.25806452,20.1455972 6.88858935,20.7972624 6.28502902,21.1584295 L3.0269645,23.1080366 C2.07913318,23.6752135 0.850976404,23.366632 0.283799571,22.4188007 C0.0980803893,22.1084381 2.0190442e-15,21.7535219 0,21.3918362 L0,3.58469444 L0.00548573643,3.43543209 L0.00548573643,3.43543209 L0,3.5715689 C3.0881846e-16,2.4669994 0.8954305,1.5715689 2,1.5715689 C2.36889529,1.5715689 2.73060353,1.67359571 3.04512412,1.86636639 L15,9.19354839 L26.9548759,1.86636639 C27.2693965,1.67359571 27.6311047,1.5715689 28,1.5715689 C29.1045695,1.5715689 30,2.4669994 30,3.5715689 L30,3.5715689 Z'
                    />
                    <polygon
                      id='Rectangle'
                      opacity='0.077704'
                      fill={theme.palette.common.black}
                      points='0 8.58870968 7.25806452 12.7505183 7.25806452 16.8305646'
                    />
                    <polygon
                      id='Rectangle'
                      opacity='0.077704'
                      fill={theme.palette.common.black}
                      points='0 8.58870968 7.25806452 12.6445567 7.25806452 15.1370162'
                    />
                    <polygon
                      id='Rectangle'
                      opacity='0.077704'
                      fill={theme.palette.common.black}
                      points='22.7419355 8.58870968 30 12.7417372 30 16.9537453'
                      transform='translate(26.370968, 12.771227) scale(-1, 1) translate(-26.370968, -12.771227) '
                    />
                    <polygon
                      id='Rectangle'
                      opacity='0.077704'
                      fill={theme.palette.common.black}
                      points='22.7419355 8.58870968 30 12.6409734 30 15.2601969'
                      transform='translate(26.370968, 11.924453) scale(-1, 1) translate(-26.370968, -11.924453) '
                    />
                    <path
                      id='Rectangle'
                      fillOpacity='0.15'
                      fill={theme.palette.common.white}
                      d='M3.04512412,1.86636639 L15,9.19354839 L15,9.19354839 L15,17.1774194 L0,8.58649679 L0,3.5715689 C3.0881846e-16,2.4669994 0.8954305,1.5715689 2,1.5715689 C2.36889529,1.5715689 2.73060353,1.67359571 3.04512412,1.86636639 Z'
                    />
                    <path
                      id='Rectangle'
                      fillOpacity='0.35'
                      fill={theme.palette.common.white}
                      transform='translate(22.500000, 8.588710) scale(-1, 1) translate(-22.500000, -8.588710) '
                      d='M18.0451241,1.86636639 L30,9.19354839 L30,9.19354839 L30,17.1774194 L15,8.58649679 L15,3.5715689 C15,2.4669994 15.8954305,1.5715689 17,1.5715689 C17.3688953,1.5715689 17.7306035,1.67359571 18.0451241,1.86636639 Z'
                    />
                  </g>
                </g>
              </g>
            </svg>
            <Typography
              variant='h6'
              sx={{
                ml: 3,
                lineHeight: 1,
                fontWeight: 600,
                textTransform: 'uppercase',
                fontSize: '1.5rem !important'
              }}
            >
              {themeConfig.templateName}
            </Typography>
          </Box>
          <Typography variant='h5' sx={{ fontWeight: 600, marginBottom: 3, } } >
              Log in to your account
            </Typography>
          <form noValidate autoComplete='off' onSubmit={e => e.preventDefault()}>
            <TextField autoFocus fullWidth id='email' label='Email' sx={{ marginBottom: 4 }} />
            <FormControl fullWidth>
              <InputLabel htmlFor='auth-login-password'>Password</InputLabel>
              <OutlinedInput
                label='Password'
                value={values.password}
                id='auth-login-password'
                onChange={handleChange('password')}
                type={values.showPassword ? 'text' : 'password'}
                endAdornment={
                  <InputAdornment position='end'>
                    <IconButton
                      edge='end'
                      onClick={handleClickShowPassword}
                      onMouseDown={handleMouseDownPassword}
                      aria-label='toggle password visibility'
                    >
                      {values.showPassword ? <EyeOutline /> : <EyeOffOutline />}
                    </IconButton>
                  </InputAdornment>
                }
              />
            </FormControl>
            <Box
              sx={{ mb: 4, display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'space-between' }}
            >
              <FormControlLabel control={<Checkbox />} label='Remember Me' />
              <Link passHref href='/'>
                <LinkStyled onClick={e => e.preventDefault()}>Forgot Password?</LinkStyled>
              </Link>
            </Box>
            <Button
              fullWidth
              size='large'
              variant='contained'
              sx={{ marginBottom: 7 }}
              onClick={() => router.push('/')}
            >
              Login
            </Button>
            <Box sx={{ display: 'flex', flexWrap: 'wrap'}}>
              <Typography variant='body2' sx={{ marginRight: 2 }}>
                New on our platform?
              </Typography>
              <Typography variant='body2'>
                <Link passHref href='/pages/register'>
                  <LinkStyled>Create an account</LinkStyled>
                </Link>
              </Typography>
            </Box>
            <Box sx={{ display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center' }}>
              <Typography variant='body2' sx={{ marginRight: 2, marginTop:3}}>
                This site is protected by reCAPTCHA and the Google Privacy Policy and Terms of Service apply.
              </Typography>
            </Box>

          </form>
          <Divider sx={{ my: 5 }}>or</Divider>
            <Box sx={{ display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center' }}>
              <Button
                fullWidth
                size='small'
                variant='contained'

                //onClick={() => handleRedirct("http://localhost:8080/oauth2/authorize/google?redirect_uri=http://localhost:3000/oauth2/redirect")}

                //function to call backend api
                onClick={() => {handleSendMessage();}
                }
                sx={{backgroundColor: '#FFF', color: 'blue', border: '1px solid'}}
              >
                <Link href='/' passHref>
                  <IconButton component='a' onClick={(e: MouseEvent<HTMLElement>) => e.preventDefault()}>
                    <Image src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACMAAAAkCAYAAAAD3IPhAAAACXBIWXMAABYlAAAWJQFJUiTwAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAARGSURBVHgB7ZhraFxFFMf/Z3a32aTN+0GaJjGlUQSFliQUsUXXQFu02GoxiYkv4gP0kxSEahASCkIMaC2C/SZqCpsEwdTa4gdtglBNKQ39IiK26SYaN0mbd7Nmk71zPLuadHfvTbKPNCD4g733zsyZmf+dc+ZxF/gfawhJUHmkfavhVFsQ4AKyO2yKaBI64L3ibriFBIhbTEV9VzVD1ytgn1S/x8qGgV+I6BJzoLPf3fAtYiRmMZX1Ha8SUxMTtiM+PPJrueKu+3wtwzXF7HrWXaZIfSaGjyIJZLR6NOuXr3bUe5CImIrazgNkpw4wZ2F9+JNBr/S7ay1dp1aq5dmU91yhf/z8OgoJUkTMn7S0tKiYxQxvyr3faTM+bb14QhX4xk3lTDQl15MwUO1nXTo9NepM9xY4Ag4qJs1PgHUwPiZM9eQdDehqEaOt+jW56QY2F6alpvwkJWXB9GhaLo7tOYoxuTOLDuADY9733tXuximsQmVNVyns+g3p4u0lIRIzj8UVM15n9iml6PXwvKCgpoffnPojLfegNPYj4mBn3en9drWp1WDjyGpCTGJGnJnbiWzXJTda5PiCEXioxD9zDXcRe3iClK0ZFqMl3mm620JC/YcnRlKzJ2TlzA7PY43vCucn9mEDWJ5NXmeWK1pISIwKfIQNIsxNymVR7t3qmzmHRGgvd0FrV0y2SvXihWu9d8QQ7zCFC6MPiRIUoqg5JlvW+XLtXXaTbP950TYGMISNgKgopGE5zeyMtrERprEhcEaEGFmfTSsqM+dgQ7TQQvBmv5OmaYstPBeJQvJ+zOY9iMhiP+TrEWIk4AYkqqPq0eNIlBcHjsv1eETe+fIU3OLfRWh+pDGFYnO5dxvogkWTWaMpmeu34I2jxixEMLgnQszQ/OQlzWyKG1KqCesF8zFznhxLGwcuR4ipAhblFPaFyZbINeLMeQ3J0r7jLRmVBy1Kzi49RARJmraflBnkj7aWUGq76cisQIJsOV3yiLxWm7lETkiG8aGlmCz/2IAN/HF0Fco3stJOzZ7zfY29iJO5r/BkX7r3TKkKmCcrq1Y0ejzL/USXD2ZmZjsXVL9MpbKQ2nwDqc2zcv9nlmrCCZ8PbQW1GMEqTHchx+HEu9LD0VC72o4Dt4swpB3/CpFYUZM78fzEzIpiggyLSxwO9T3l66xwIWH8JQPcxQpuWa2Gs4fxK0qgxL/F2o9dsmAcloafkqNmesSLLgkyHFMyg3ZL4P4WXr7ip8rN8vRDznfmum0FOqlP4GhuaPvMA3PFdWjwmD5XVu3IdwZ7JKo65XEb1odBGdGazYdw2apQrVYz7TAuUgB7NeMHJM8Facu1kpAgMbvg9jd4SelQQJYjPvoCAbyf8TS61zKMOx5mz8JlA56R0dqvCPdaNsr4mYMjYcOXqQdjH9WkgrOnBfaq3bjPzshbXJR/aQhjGdswSFVYxH+dvwHWXX4t3we1RAAAAABJRU5ErkJggg==" alt="Google Icon" width={24} height={24} />
                  </IconButton>
                </Link>
                Sign in with Google
              </Button>
            </Box>
        </CardContent>
      </Card>
    </Box>

    )
}


LoginPage.getLayout = (page: ReactNode) => <BlankLayout>{page}</BlankLayout>

export default LoginPage
