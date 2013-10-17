This example demonstrates how to use Apache Camel's SWS-SQS support.

TO use this example, you should first replace the access key and security key
in the src/main/resources/default.properties file with those that belong to your AWS
account.

Next, you should log into your AWS account, navigate to the SQS panels and create
a new queue named 'demo'

Last, you can run the example with 'mvn camel:run'

You should see that one of the two routes produces JSON messages every 5 seconds 
to the 'demo' queue. You should also note that there are two types of messages:

a message with 'type' field == 'login' and another messages with 'type' == 'logout'

The second camel route will filter out messages and only allow 'type' == 'login' messages
to make it through. These will printed to the console as well as logged as files
to the file system under target/output.

Report any bugs to this git repo!
