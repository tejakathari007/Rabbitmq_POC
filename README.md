Welcome to the Rabbitmq_POC !

          In this I am just sending two queues Bravo and Charlie with a parameter each. Just using a default exchange mechanism. 
          
Pre-Requisites:

1)Download and install Erlang from "https://www.erlang.org/downloads" suited for your OS. 

2)Same as above install Rabbitmq from "https://www.rabbitmq.com/download.html"
 
 
Steps:

1)Go to the RabbitMQ installation path,run the RabbitMQ command prompt
 -->rabbitmq-server start 
   (to start rabbitmq)
   
   Note: If you are running it in any organisation, may be rabbitmq cluster is always up. You'll receive a message stating 
         that it is already up&running, Eventhough its your first time.
         
 -->rabbitmq-plugins.bat enable rabbitmq_management
   (Install the RabbitMQ plugin which will give use the RabbitMQ Management Console that is accessible from a browser)


2)Now move on to localhost:15672. We will see the RabbitMQ console. The default username and password is guest.

