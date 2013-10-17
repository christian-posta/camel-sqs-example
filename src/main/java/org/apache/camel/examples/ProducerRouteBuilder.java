/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.examples;

import org.apache.camel.builder.RouteBuilder;

import java.util.Random;

/**
 * @author <a href="http://www.christianposta.com/blog">Christian Posta</a>
 */
public class ProducerRouteBuilder extends RouteBuilder {

    Random randomGenerator = new Random();
    private static final int MAX = 10;

    private static final String LOGIN_MESSAGE =
            "{" +
                    "\"type\": \"login\"," +
                    "\"payload\" : {" +
                    "\"name\": \"ceposta\", " +
                    "\"city\": \"phoenix\"" +
                    "}" +
            "}";

    private static final String LOGOUT_MESSAGE =
            "{" +
                    "\"type\": \"logout\"," +
                    "\"payload\" : {" +
                    "\"name\": \"ceposta\", " +
                    "\"city\": \"phoenix\"" +
                    "}" +
                    "}";

    @Override
    public void configure() throws Exception {

        // every two seconds, send a message to the "demo" queue in SQS
        from("timer:kickoff?period=5000")
                .setBody().method(this, "generateJsonString")
                .to("aws-sqs://demo?amazonSQSClient=#sqsClient&defaultVisibilityTimeout=2");

    }

    public Object generateJsonString() {
        int rand = randomGenerator.nextInt(MAX);
        if ((rand % 2) == 0) {
            return LOGIN_MESSAGE;
        } else {
            return LOGOUT_MESSAGE;
        }
    }
}
