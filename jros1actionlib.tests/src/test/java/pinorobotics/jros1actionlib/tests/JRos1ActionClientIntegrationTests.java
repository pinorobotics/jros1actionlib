/*
 * Copyright 2021 jrosactionlib project
 * 
 * Website: https://github.com/pinorobotics/jros1actionlib
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pinorobotics.jros1actionlib.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import id.jros1client.JRos1Client;
import id.jros1client.JRos1ClientFactory;
import id.jrosmessages.std_msgs.Int32Message;
import id.xfunction.ResourceUtils;
import id.xfunction.logging.XLogger;
import java.net.MalformedURLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pinorobotics.jros1actionlib.JRos1ActionClientFactory;
import pinorobotics.jros1actionlib.tests.actionlib_tutorials_msgs.FibonacciActionDefinition;
import pinorobotics.jros1actionlib.tests.actionlib_tutorials_msgs.FibonacciGoalMessage;
import pinorobotics.jros1actionlib.tests.actionlib_tutorials_msgs.FibonacciResultMessage;
import pinorobotics.jrosactionlib.JRosActionClient;

public class JRos1ActionClientIntegrationTests {

    private static final ResourceUtils resourceUtils = new ResourceUtils();
    private static JRos1Client client;
    private static JRos1ActionClientFactory factory = new JRos1ActionClientFactory();
    private JRosActionClient<FibonacciGoalMessage, FibonacciResultMessage> actionClient;

    @BeforeEach
    public void setup() throws MalformedURLException {
        XLogger.load("logging-test.properties");
        client = new JRos1ClientFactory().createClient("http://localhost:11311/");
        actionClient = factory.createClient(client, new FibonacciActionDefinition(), "/fibonacci");
    }

    @AfterEach
    public void clean() throws Exception {
        actionClient.close();
        client.close();
    }

    @Test
    public void test_sendGoal() throws Exception {
        var goal = new FibonacciGoalMessage().withOrder(new Int32Message().withData(5));
        var result = actionClient.sendGoalAsync(goal).get();
        System.out.println(result);
        assertEquals(resourceUtils.readResource("test_sendGoal"), result.toString());
    }
}
