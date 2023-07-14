import { useRouter } from 'next/router';
import React, { ReactNode, useEffect } from 'react';
import BlankLayout from 'src/@core/layouts/BlankLayout';

import { styled } from '@mui/material/styles'
import Typography from '@mui/material/Typography'
import Box, { BoxProps } from '@mui/material/Box'
import FooterIllustrations from 'src/views/pages/misc/FooterIllustrations'

// ** Styled Components
const BoxWrapper = styled(Box)<BoxProps>(({ theme }) => ({
  [theme.breakpoints.down('md')]: {
    width: '90vw'
  }
}))



const RedirectPage = () => {
  const router = useRouter();
  useEffect(() => {
    const urlParams = new URLSearchParams(window.location.search);
    // Extract search parameters from the redirected URL
    const token: string | null = urlParams.get('token');
    // Use the extracted parameter in your logic
    console.log(token);
    if (token === null) {
      //TODO: Change to authentication Error
      router.push('/pages/error');
    }
    else {
      localStorage.setItem('token', token);
      router.push('/');
    }
    
  }, []);

  return (
    <Box className='content-center'>
    <Box sx={{ p: 5, display: 'flex', flexDirection: 'column', alignItems: 'center', textAlign: 'center' }}>
      <BoxWrapper>
        <Typography variant='h1'>Redirecting!</Typography>
        <Typography variant='h5' sx={{ mb: 1, fontSize: '1.5rem !important' }}>
          Please wait for a few seconds ⚠️
        </Typography>
      </BoxWrapper>
    </Box>
  </Box>)
};
RedirectPage.getLayout = (page: ReactNode) => <BlankLayout>{page}</BlankLayout>

export default RedirectPage;