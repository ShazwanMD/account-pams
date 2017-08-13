**PAMS Account Project**

# Setup Manual for PAMS Account Project
> Maklumat berkaitan konfigurasi pelayan/app/db/integration etc


### 1.0 Run a PAMS Account app 
> To run one PAMS app per host, do the following:

    1.1 Install Postgres
    1.2 Install Java
    1.3 Install PAMS app war file from /account-back/target/ (Example: account-0.1.0.war)
    1.4 Copy PAMS app application.properties file and put in the same dir as war file
        1.4.1 Remember to change application.properties values to fit your Postgres intallation 
        1.4.2 For integration with other PAMS apps via ActiveMQ broker, see PAMS Connector README.  
    1.5 Run PAMS app war file (Example: java -jar account-0.1.0.war)
  
