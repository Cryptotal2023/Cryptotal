# Cryptotal
Simplifying, optimizing, and enhancing your cryptocurrency portfolio management.

Cryptotal is an all-encompassing platform that consolidates your digital asset management across a variety of exchanges and wallets. It not only simplifies asset oversight but also refines your trading tactics and maximizes your financial potential. With its integrated AI technology, Cryptotal also functions as an insightful trading journal, empowering you to scrutinize your trading patterns and glean significant insights for increased profitability.

## Technology we use

1. Backend: Java Spring, Hibernate, MyBatis, RabbitMQ, MicroService, Netﬂix Eureka, Spring Cloud Gateway, Spring Cloud OpenFeign, MySQL, AWS (EC2, S3, RDS)
2. Frontend: React, Material UI, TypeScript, ThirdWeb API
3. UIUX: Figma, Canva
## Problem we solved

1. Frustration and complexity in securely monitoring various exchanges and wallets:
   Solution: We provide a unified dashboard integrating all your accounts and supporting secure multi-chain wallets, simplifying asset oversight.
3. Miscalculations and inconsistencies in transaction statistics:
   Solution: Our platform automatically traces multi-chain and multi-wallet historical data, accurately calculating the coin cost, and precisely enabling investment performance analysis.
4. Underutilized trading journals limiting success:
   Solution: We automate the import of trade data and generate AI-driven trader reports, providing valuable insights and analysis to boost trading success.
5. Implemented walletless Onboarding concept: 
    Solution: Users can interact with our platform using Google or native login flow and add platform accounts without interacting with the blockchain itself.
    
6. Limited understanding of investment performance
    Solution: With AI-generated reports and personalized recommendations, we offer users to interpret their investment results effectively, promoting informed decisions and smarter investment strategies.

## Why people should use it
Streamline Digital Asset Management across Diverse Platforms: Given the global crypto ownership rate of 4.2%, amounting to over 420 million crypto users worldwide, the need for a proficient platform to manage assets across various interfaces is on the rise. Studies reveal that approximately 60% of cryptocurrency users store their digital assets on exchanges, and the average user employs 2.6 exchanges. This trend illustrates a trend with the potential to accommodate 250 million users.

Precise Earnings and Losses Calculation: The highly volatile nature of cryptocurrency prices, along with the diversity of cryptocurrencies and numerous transactions across exchanges and wallets. Our platform provides precise tracking of the average cost for each coin, enabling users to understand their financial gains or losses effectively.

Assistance for Crypto Novices: Many newcomers in the crypto realm lack a user-friendly platform to document their crypto journals and gain valuable insights. Our platform offers an intuitive interface to help them track their crypto journey and make informed decisions.

## How to use our project
In the future, our project will be deployed to AWS and able to use online via webApps. 
If you want to install on your local side, follow here.

Our product landing page: 

### Frontend
Note that you should provide your own Google API Key for OAuth to work. 
#### Install Local
```
cd typescript-version
npm install
npm run dev
```
Go to localhost:3000
### Database
#### Install Local DB
```
cd DBScripts
```
install MySQL and run all MySQL scripts
### Backend
Note that you should provide your own Google API Key for OAuth to work. 
#### Install Local Java Backend
```
cd backend
```
clone all files and add as maven project
##### Dependecy
```
java 8+
java sdk 1.8+
```
##### RabbitMQ
please install RabbitMQ to activate local messagequeue server


### Credit 
1. Binance Connector: https://github.com/binance/binance-connector-java
2. ThirdWeb Api: https://portal.thirdweb.com/
