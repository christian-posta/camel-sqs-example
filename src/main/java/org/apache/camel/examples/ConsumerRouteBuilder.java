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

/**
 * @author <a href="http://www.christianposta.com/blog">Christian Posta</a>
 */
public class ConsumerRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("aws-sqs://demo?amazonSQSClient=#sqsClient&deleteIfFiltered=false")
                .setHeader("identity").jsonpath("$['type']")
                .filter(simple("${header.identity} == 'login'"))
                .log("We have a message! ${body}")
                .to("file:target/output?fileName=login-message-${date:now:MMDDyy-HHmmss}.json");
    }
}
